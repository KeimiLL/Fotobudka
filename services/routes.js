const auth = require('../middleware/auth'),
  multer = require('multer'),
  os = require('os'),
  path = require('path'),
  upload = multer({ dest: path.join(os.tmpdir(), 'fotobudka') })

module.exports = app => {
  app.use(auth)

  app.get('/', (req, res, next) => {
    res.sendStatus(404)
  })

  app.get('/settings/:uid', async (req, res, next) => {

  })

  app.post('/settings/:uid', (req, res, next) => {
    console.log(req.body)

  })

  app.post('/photo/:uid', upload.single('file'), (req, res, next) => {

  })

  app.get('/status/:uid', (req, res, next) => {

  })
}

