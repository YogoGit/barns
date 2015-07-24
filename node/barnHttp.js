  var http = require('http');

  // request is an instance of http.IncomingMessage
  // response is an instance of http.ServerResponse
  var server = http.createServer(function (request, response) {
    console.log("Node request recieved");
    if(request.method === "GET" || request.method === "POST") {
      var data = '';

      request.on('data', function (chunk) {
        data += chunk;
      });
   
      var jsonObject;
      request.on('end', function(){
        jsonObject = JSON.parse(data);
        var result  = calculateBarnsValue(jsonObject);
        console.log("Value of Barns calculated: " + result.value);
      	response.writeHead(200, { 'Content-Type': 'application/json' });
        response.write(JSON.stringify(result));
        response.end();
      }); 
    }
  });
  server.listen(9055);

function calculateBarnsValue(barnsArr){
    // iterate through the barns to calculate the value of the animals.
    var alpha = "..abcdefghijklmnopqrstuvwxyz";
    var valueFactor = 1275;
    var totalValue = 47500 * barnsArr.length;
    outer:
    for(var i=0; i<barnsArr.length; i++){
      if(typeof barnsArr[i].animals === "undefined") continue outer;
      var animalsArr = barnsArr[i].animals;
      for(var j=0; j<animalsArr.length; j++){
        var animalName = animalsArr[j].name.toLowerCase();
        var alphaFactor = alpha.indexOf(animalName[0]) * 2;
        if(alphaFactor <= 0) alphaFactor = 1; 
        totalValue += alphaFactor * valueFactor;
      }
    }
    // return a the value as JS Object.
    var valueObj = {};
    valueObj["value"] = totalValue;
    return valueObj;
}
