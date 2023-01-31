module.exports = (req, res, next) => {
  if (req.token !== process.env.ACCESS_TOKEN)
    return res.sendStatus(401)

  next()
}
