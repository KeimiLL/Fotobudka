const path = require('path'),
  fs = require('fs'),
  FileType = require('file-type')

const filesDir = path.join(__dirname, '..', process.env.FILES_DIR)

if (!fs.existsSync(filesDir))
  fs.mkdirSync(filesDir, { recursive: true })

exports.readSettings = uid => new Promise((resolve, reject) => {
  const path = sessionDir(uid) + '/settings.json'

  fs.readFile(path, (err, data) => {
    if (err) return reject(err)
    resolve(JSON.parse(data.toString('utf8')))
  })
})

exports.writeSettings = (uid, data) => new Promise((resolve, reject) => {
  const dir = sessionDir(uid)

  if (!fs.existsSync(dir))
    fs.mkdirSync(dir, { recursive: true })

  fs.writeFile(`${dir}/settings.json`, JSON.stringify(data), err => {
    if (err) return reject(err)
    resolve()
  })
})

exports.addPhoto = (uid, file, data) => new Promise(async (resolve, reject) => {
  const dir = photosDir(uid)

  const type = await FileType.fromFile(file.path).catch(reject)
  if (!type) return

  if (!type.mime.startsWith('image/'))
    return reject(new Error('File is not an image'))

  if (!fs.existsSync(dir))
    fs.mkdirSync(dir, { recursive: true })

  exports.listPhotos(uid).then(photos => {

    const id = photos.length

    fs.copyFile(file.path, path.join(dir, `${id}.${type.ext}`), err => {
      if (err) return reject(err)
      fs.rmSync(file.path)

      if (!data) return resolve()

      fs.writeFile(path.join(dir, `${id}.json`), JSON.stringify(data), err => {
        if (err) return reject(err)
        resolve()
      })
    })

  }).catch(reject)
})

exports.listPhotos = uid => new Promise((resolve, reject) => {
  const dir = photosDir(uid)

  fs.readdir(dir, (err, files) => {
    if (err) return reject(err)
    resolve(filterPhotos(files))
  })
})

const excludedExt = ['.json', '.pdf', '.html']

function filterPhotos(files) {
  return files.filter(file => !excludedExt.some(ext => file.endsWith(ext)))
}

function sessionDir(uid) {
  return path.join(filesDir, uid)
}

function photosDir(uid) {
  return sessionDir(uid) + '/photos'
}
