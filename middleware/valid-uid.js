module.exports = (req, res, next) => {
  if (req.query.uid && !/^[0-9a-fA-F-]{4,40}$/.test(req.query.uid))
    return res.sendStatus(400)

  next()
}
