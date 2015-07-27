var BarnAppraiser = require('./barnAppraiser.js');

describe('barn appraiser', function() {

	it('should return a value of 0 for an empty list of barns', function() {
		var appraiser = new BarnAppraiser([]);
		expect(appraiser.calculateBarnsValue().value).toBe(0);
	});

	it('should return totalAnimals of 0 for an empty list of barns', function() {
		var appraiser = new BarnAppraiser([]);
		expect(appraiser.calculateBarnsValue().totalAnimals).toBe(0);
	});

	it('should return a value of 50000 for one barn and no animals', function() {
		var appraiser = new BarnAppraiser([{name: "Red Barn", animals: []}]);
		expect(appraiser.calculateBarnsValue().value).toBe(50000);
	});

	it('should return a value of 51400 for one barn with 2 chickens', function() {
		var appraiser = new BarnAppraiser([{name: "Red Barn", animals: [{name: "Chicken", quantity: 2}]}]);
		expect(appraiser.calculateBarnsValue().value).toBe(51400);
	});

	it('should return totalAnimals of 2 for one barn with 2 chickens', function() {
		var appraiser = new BarnAppraiser([{name: "Red Barn", animals: [{name: "Chicken", quantity: 2}]}]);
		expect(appraiser.calculateBarnsValue().totalAnimals).toBe(2);
	});

	it('should return a value of 107525 for two barns, one with 2 chickens and another with 7 donkeys', function() {
		var appraiser = new BarnAppraiser([{name: "Red Barn", animals: [{name: "Chicken", quantity: 2}]},
                                                   {name: "Blue Barn", animals: [{name: "Donkey", quantity: 7}]}]);
		expect(appraiser.calculateBarnsValue().value).toBe(107525);
	});

	it('should return totalAnimals of 9 for two barns with 2 chickens and 7 donkeys', function() {
        	var appraiser = new BarnAppraiser([{name: "Red Barn", animals: [{name: "Chicken", quantity: 2}]},
                                                   {name: "Blue Barn", animals: [{name: "Donkey", quantity: 7}]}])
		expect(appraiser.calculateBarnsValue().totalAnimals).toBe(9);
	});

});
