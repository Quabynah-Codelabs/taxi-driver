const express = require("express"),
  http = require("http"),
  app = express(),
  server = http.createServer(app),
  io = require("socket.io").listen(server);
app.get("/", (req, res) => {
  res.send("Taxi driver server is running on port 5001");
});

io.on("connection", socket => {
  console.log("user connected");

  socket.on("calculateFare", () => {
      console.log("Calculating fare");

  });

  socket.on("disconnect", function() {
    console.log("user has left ");
    socket.broadcast.emit("userdisconnect", " user has left");
  });
});

server.listen(5001, () => {
  console.log("Node app is running on port 5001");
});

module.exports = app;
