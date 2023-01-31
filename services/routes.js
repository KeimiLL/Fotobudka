const auth = require('../middleware/auth'),
  multer = require('multer'),
  os = require('os'),
  path = require('path'),
  upload = multer({ dest: path.join(os.tmpdir(), 'fotobudka') }),
  storage = require('../services/storage')

module.exports = app => {
  app.use(auth)

  app.get('/', (req, res, next) => {
    res.sendStatus(404)
  })

  app.get('/settings/:uid', async (req, res, next) =>
    storage.readSettings(req.params.uid).then(data => {
      res.json(data)
    }).catch(err => {
      res.sendStatus(err.code === 'ENOENT' ? 404 : 500)
      if (err.code === 'ENOENT') return
      console.error('Error reading settings:', err)
    }))

  app.post('/settings/:uid', (req, res, next) => {
    console.log(req.body)

    storage.writeSettings(req.params.uid, req.body).then(() => {
      res.sendStatus(200)
    }).catch(err => {
      res.sendStatus(500)
      console.error('Error writing settings:', err)
    })
  })

  app.post('/photo/:uid', upload.single('file'), (req, res, next) => {
    storage.addPhoto(req.params.uid, req.file, req.body).then(() => {
      res.sendStatus(200)
    }).catch(err => {
      res.sendStatus(500)
      console.error('Error adding photo:', err)
    })
  })

  app.get('/status/:uid', (req, res, next) => {
    storage.readSettings(req.params.uid).then(settings => {
      const data = { settings }

      storage.listPhotos(req.params.uid).then(photos => {
        data.photos = photos.filter(f => f.endsWith('.jpg'))
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
}
