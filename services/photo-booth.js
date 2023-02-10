const path = require('path'),
  fs = require('fs'),
  hb = require('handlebars'),
  html2pdf = require('./html2pdf')

const cards = ['noir.svg', 'birthday.svg', 'holiday.svg', 'winter.svg']

exports.generate = (photos, card, text, output) => new Promise((resolve, reject) => {

  fs.readFile(path.join(__dirname, '../views/template.html'), 'utf-8', (err, html) => {
    if (err) return reject(err)

    const options = { width: 2480, height: 3508, path: output, printBackground: true }
    const data = { card: `../views/cards/${cards[card]}`, text, photos }
    const out = path.join(__dirname, '../views/tmp.html')

    html = hb.compile(html, { strict: true })(data)

    resolve(new Promise((resolve, reject) =>
      fs.writeFile(out, html, err => {
        if (err) reject(err)
        else resolve(html2pdf(out, data, options))
      })
    ).finally(() =>
      fs.rm(out, () => {})
    ))
  })

})
