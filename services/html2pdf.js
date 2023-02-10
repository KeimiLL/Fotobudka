const puppeteer = require('puppeteer')

module.exports = async function (file, data, options) {
  const browser = await puppeteer.launch({
    args: [
      '--no-sandbox',
      '--disable-setuid-sandbox',
      '--allow-file-access-from-files',
      '--enable-local-file-accesses'
    ],
    headless: true
  })

  const page = await browser.newPage()
  await page.goto(`file://${file}`, { waitUntil: 'networkidle0' })

  return page.pdf(options).finally(() => browser.close())
}
