module.exports = app => {

  app.get('/', (req, res, next) => {
    res.sendStatus(404)
  })

}
