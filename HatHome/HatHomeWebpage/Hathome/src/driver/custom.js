module.exports = {
  request: function (req, token) {
    req.headers.set('Authorization', 'Bearer ' + token)
  },
  response: function (res) {
    return res.data.access_token
  }
}