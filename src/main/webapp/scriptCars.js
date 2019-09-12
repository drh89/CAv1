

var table = document.getElementById("table");
var btnAll = document.getElementById("btnAll");
var btnSortPrice = document.getElementById("btnSortPrice");
var btnSortMake = document.getElementById("btnSortMake");
var btnSortModel = document.getElementById("btnSortModel");
var btnSortModelYear = document.getElementById("btnSortModelYear");
var btnSortRegistrationDate = document.getElementById("btnSortRegistration");
var url = "http://localhost:8080/CAv1/api/cars/all";




btnAll.addEventListener("click", getAllCars);
btnSortPrice.addEventListener("click", sortByPrice);
btnSortMake.addEventListener("click", sortByMake);
btnSortModel.addEventListener("click", sortByModel);
btnSortModelYear.addEventListener("click", sortByModelYear);
btnSortRegistrationDate.addEventListener("click", sortByRegistrationDate);


function compareProperty(a, b) {
    return a.localeCompare(b);
}

function sortMakeModelPrice(a, b) {
    return compareProperty(a.make, b.make) || compareProperty(a.model, b.model) || a.price - b.price;
}


function sortByRegistrationDate(){
    event.preventDefault();
    var conf = {method: "get"};
    
    var promise = fetch(url, conf);
    
    promise.then(res => res.json())
            .then(function(data){
                var newData = data.sort(function(a,b){
                    return compareProperty(a.registrationDate,b.registrationDate);
                });
                var html = generateTable(newData);
                table.innerHTML = html;
    })
}


function sortByModelYear(){
    event.preventDefault();
    var conf = {method: "get"};
    
    var promise = fetch(url, conf);
    
    promise.then(res => res.json())
            .then(function(data){
                var newData = data.sort(function(a,b){
                    return a.modelYear - b.modelYear;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
    })
}

function sortByMake() {
    event.preventDefault();

    var conf = {method: "get"};

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return sortMakeModelPrice(a, b);
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}


function sortByModel() {
    event.preventDefault();

    var conf = {method: "get"};

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return compareProperty(a.model, b.model);
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}



function sortByPrice() {
    event.preventDefault();

    var conf = {method: "get"};

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return a.price - b.price;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}


function getAllCars() {
    event.preventDefault();

    var conf = {method: "get"};

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var html = generateTable(data);
                table.innerHTML = html;

            });
}






function generateTable(data) {
    var newData = data.map(function (c) {
        return "<tr><td>" + c.make + "</td>" + "<td>" + c.model + "</td>" +
                "<td>" + c.registrationDate + "</td>" + "<td>" + c.modelYear + "</td>" +
                "<td>" + c.horsepower + "</td>" + "<td>" + c.mileage + "</td>" +
                "<td>" + c.doors + "</td>" + "<td>" + c.price + "</td></tr>";
    });
    return "<tr><th>Make</th>" + "<th>Model</th>" + "<th>Registration Date</th>" +
            "<th>Model Year</th>" + "<th>Horsepower</th>" +
            "<th>Mileage</th>" + "<th>Doors</th>" + "<th>Price</th></tr>" + newData.join("");


}