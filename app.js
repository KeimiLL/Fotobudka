const dotenv = require('dotenv'),
  http = require('http'),
  express = require('express'),
  compression = require('compression'),
  errorHandler = require('errorhandler'),
  logger = require('morgan')

dotenv.config({ path: '.env' })
dotenv.config({ path: '.env.example' })

const app = express()

app.set('env', process.env.NODE_ENV || 'development')
app.set('host', process.env.EXPRESS_HOST || '0.0.0.0')
app.set('port', process.env.EXPRESS_PORT || 8080)
app.set('trust proxy', process.env.EXPRESS_PROXY)

app.use(compression())
app.use(logger('dev'))
app.use(express.urlencoded({ extended: true }))
app.use(express.json())

app.disable('x-powered-by')

require('./services/routes')(app)

if (process.env.NODE_ENV === 'development')
  app.use(errorHandler({ log: true }))
else
  app.use((err, req, res, next) => {
    res.status(500).header('Content-Type', 'text/plain').send('Server Error')
    console.error(err)
  })

app.use((req, res) => {
  res.status(404).send('Not Found')
})

http.createServer(app).listen(app.get('port'), app.get('host'), () => {
  console.info(`[Express] Listening on ${app.get('host')}:${app.get('port')}, ${app.get('env')} mode`)
})
