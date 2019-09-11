

var table = document.getElementById("table");
var btnAll = document.getElementById("btnAll");
var btnSortPrice = document.getElementById("btnSortPrice");
var btnSortMake = document.getElementById("btnSortMake");
var btnSortModel = document.getElementById("btnSortModel");
var dom = "http://localhost:8080/CA1/api/cars";




btnAll.addEventListener("click", getAllCars);
btnSortPrice.addEventListener("click", sortByPrice);
btnSortMake.addEventListener("click", sortByMake);
btnSortModel.addEventListener("click", sortByModel);




function sortByMake() {
    event.preventDefault();
    var url = dom + "/all";
    var conf = {method: "get"};

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return ("" + a.make).localeCompare(b.make);
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}


function sortByModel() {
    event.preventDefault();
    var url = dom + "/all";
    var conf = {method: "get"};

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return ("" + a.model).localeCompare(b.model);
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}








function sortByPrice() {
    event.preventDefault();
    var url = dom + "/all";
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
    var url = dom + "/all";
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