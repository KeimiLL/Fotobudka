const auth = require('../middleware/auth'),
  multer = require('multer'),
  os = require('os'),
  path = require('path'),
  upload = multer({ dest: path.join(os.tmpdir(), 'fotobudka') }),
  storage = require('../services/storage'),
  validUid = require('../middleware/valid-uid'),
  pb = require('../services/photo-booth')

module.exports = app => {
  app.use(auth)

  app.get('/', (req, res, next) => {
    res.sendStatus(404)
  })

  app.get('/settings/:uid', validUid, async (req, res, next) =>
    storage.readSettings(req.params.uid).then(data => {
      res.json(data)
    }).catch(err => {
      res.sendStatus(err.code === 'ENOENT' ? 404 : 500)
      if (err.code === 'ENOENT') return
      console.error('Error reading settings:', err)
    }))

  app.post('/settings/:uid', validUid, (req, res, next) => {
    console.log(req.body)

    storage.writeSettings(req.params.uid, req.body).then(() => {
      res.sendStatus(200)
    }).catch(err => {
      res.sendStatus(500)
      console.error('Error writing settings:', err)
    })
  })

  app.post('/photo/:uid', validUid, upload.single('file'), (req, res, next) => {
    storage.addPhoto(req.params.uid, req.file, req.body).then(() => {
      res.sendStatus(200)
    }).catch(err => {
      res.sendStatus(500)
      console.error('Error adding photo:', err)
    })
  })

  app.get('/status/:uid', validUid, (req, res, next) => {
    storage.readSettings(req.params.uid).then(settings => {
      const data = { settings }

      storage.listPhotos(req.params.uid).then(photos => {
        data.photos = photos
        res.json(data)

      }).catch(err => {
        if (err.code === 'ENOENT')
          return res.json(data)

        res.sendStatus(500)
        console.error('Error getting photos:', err)
      })

    }).catch(err => {
      res.sendStatus(err.code === 'ENOENT' ? 404 : 500)
      if (err.code === 'ENOENT') return
      console.error('Error getting status:', err)
    })
  })

  app.get('/result/:uid', validUid, (req, res, next) => {
    const { uid } = req.params

    Promise.all([storage.readSettings(uid), storage.listPhotos(uid)])
      .then(([settings, photos]) => {
        const resultFile = storage.resultFile(uid)
        const photosDir = storage.photosDir(uid)
        photos = photos.map(photo => path.join(photosDir, photo))

        return pb.generate(photos, settings.cardId || 0, settings.cardText || '', resultFile)
      })
      .then(() =>
        res.json({ 'pdf': `${req.protocol}://${req.get('host')}/pdf/${uid}` })
      )
      .catch(err => {
        res.sendStatus(err.code === 'ENOENT' ? 404 : 500)
        if (err.code === 'ENOENT') return
        console.error('Error getting result:', err)
      })
  })
}
