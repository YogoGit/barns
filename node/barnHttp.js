var BarnAppraiser = require('./barnAppraiser.js');
var http = require('http');
var agent = new http.Agent();
agent.maxSockets = 1;
// request is an instance of http.IncomingMessage
// response is an instance of http.ServerResponse
var server = http.createServer(function(request, response) {
  console.log("Node request recieved ", new Date().toUTCString());
  if (request.method === "POST") {
    var data = '';

    // collect the posted data one chunk at a time.
    request.on('data', function(chunk) {
      data += chunk;
    });

    var barnsArray;
    // When the request is ended, we can run our calculation and write the
    // response.
    request.on('end', function() {
      barnsArray = JSON.parse(data);
      var appraiser = new BarnAppraiser(barnsArray);
      var result = appraiser.calculateBarnsValue();
      console.log("  Value of %d Barns with %d Animals: %d",
          barnsArray.length, result.totalAnimals, result.value);
      response.writeHead(200, {
        'Content-Type' : 'application/json'
      });
      response.write(JSON.stringify(result));
      response.end();
    });
    // Set a connection timeout to close the socket.
    request.on('socket', function(socket) {
      myTimeout = 500; // millis
      socket.setTimeout(myTimeout);
      socket.on('timeout', function() {
        console.log("  Timeout, aborting request")
        request.abort();
      });
    });
  }
});
server.listen(9055);
