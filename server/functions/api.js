const express = require("express"),
  http = require("http"),
  app = express(),
  server = http.createServer(app),
  io = require("socket.io").listen(server);
app.get("/", (req, res) => {
  res.send("Taxi driver server is running on port 3000");
});

io.on("connection", socket => {
  console.log("user connected");

  socket.on("join", function(userNickname) {
    console.log(userNickname + " : has joined the chat ");

    socket.broadcast.emit(
      "userjoinedthechat",
      userNickname + " : has joined the chat "
    );
  });
});

server.listen(3000, () => {
  console.log("Node app is running on port 3000");
});

module.exports = app;
