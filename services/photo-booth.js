const path = require('path'),
  fs = require('fs').promises,
  hb = require('handlebars'),
  crypto = require('crypto'),
  html2pdf = require('./html2pdf'),
  compressPDF = require('./compress-pdf')

const cards = ['noir.svg', 'birthday.svg', 'holiday.svg', 'winter.svg']

exports.generate = (photos, card, text, output) =>
  fs.readFile(path.join(__dirname, '../views/template.html'), 'utf-8').then(html => {
    const id = crypto.randomBytes(4).readUInt32LE(0)
    const out = path.join(__dirname, `../views/${id}.html`)
    const pdf = path.join(__dirname, `../views/${id}.pdf`)

    const options = { width: 2480, height: 3508, path: pdf, printBackground: true }
    const data = { card: `../views/cards/${cards[card]}`, text, photos }

    html = hb.compile(html, { strict: true })(data)

    return fs.writeFile(out, html)
      .then(() => html2pdf(out, data, options))
      .then(() => compressPDF(pdf, output, { quiet: true }))
      .finally(() =>
        Promise.all([
          fs.rm(out),
          fs.rm(pdf),
        ]).catch(() => {})
      )
  })
