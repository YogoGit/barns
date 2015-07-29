BarnAppraiser = function(barnsArray) {
  this.barns = barnsArray;
  this.animals = [];
  // aggregate all of the barns' animals into one array.
  for (var i = 0; i < this.barns.length; i++) {
    if (typeof this.barns[i].animals === "undefined") {
      continue;
    }
    this.animals = this.animals.concat(this.barns[i].animals);
  }
  this.totalAnimals = 0;
  this.animalMultiple = 175;
  this.barnBaseValue = 50000;
}

BarnAppraiser.prototype.calculateBarnsValue = function() {
  var totalValue = 0;
  // calculate the value of the barns.
  totalValue += this.barnBaseValue * this.barns.length;

  // add the value of the animals, since the animals live in the barns.
  totalValue += this.calculateAnimalsValue();

  // return a the value as JS Object.
  var valueObj = {};
  valueObj["value"] = totalValue;
  valueObj["totalAnimals"] = this.totalAnimals;
  return valueObj;
}

// The algorithm for calculating the value of the animals.
BarnAppraiser.prototype.calculateAnimalsValue = function() {
  if (this.animals.length < 1) {
    return 0;
  }
  var animalsValue = 0;
  var alpha = "..abcdefghijklmnopqrstuvwxyz";
  for (var j = 0; j < this.animals.length; j++) {
    var animalQuantity = this.animals[j].quantity;
    this.totalAnimals += animalQuantity;
    var animalName = this.animals[j].name.toLowerCase();
    var alphaFactor = alpha.indexOf(animalName[0]);
    if (alphaFactor <= 0) {
      alphaFactor = 1;
    }
    animalsValue += alphaFactor * this.animalMultiple * animalQuantity;
  }
  return animalsValue;
}

module.exports = BarnAppraiser;