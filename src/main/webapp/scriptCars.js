

var table = document.getElementById("table");
var btnAll = document.getElementById("btnAll");
var btnFilter = document.getElementById("btnFilter");
var input = document.getElementById("input");
var btnPrice = document.getElementById("btnPrice");
var inputPrice = document.getElementById("inputPrice");
var dom = "http://localhost:8080/CAv1/api/cars";




btnAll.addEventListener("click", getAllCars);
btnFilter.addEventListener("click", getCarsByMake);
btnPrice.addEventListener("click", getCarsByPrice);

table.addEventListener("click", function (e) {
    var target = e.target;
    if (target.id === "make") {
        sortByMake();
    }
    if (target.id === "model") {
        sortByModel();
    }
    if (target.id === "registration") {
        sortByRegistrationDate();
    }
    if (target.id === "modelyear") {
        sortByModelYear();
    }
    if (target.id === "horsepower") {
        sortByHorsepower();
    }
    if (target.id === "mileage") {
        sortByMileage();
    }
    if (target.id === "doors") {
        sortByDoors();
    }
    if (target.id === "price") {
        sortByPrice();
    }
});


function getCarsByPrice() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";
    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.filter(c => c.price <= inputPrice.value);
                newData.sort(function(a,b){
                   return sortMakeModelPrice(a,b); 
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}


// "Filter" SQL
function getCarsByMake() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/makename/" + input.value;
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



function sortByHorsepower() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";
    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return a.horsepower - b.horsepower;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });

}

function sortByDoors() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";


    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return a.doors - b.doors;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });

}
function sortByMileage() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";


    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return a.mileage - b.mileage;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });

}

function sortByRegistrationDate() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return compareProperty(a.registrationDate, b.registrationDate);
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}


function sortByModelYear() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return a.modelYear - b.modelYear;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}

function sortByMake() {
    event.preventDefault();

    var conf = {method: "get"};
    var url = dom + "/all";

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
    var url = dom + "/all";


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
    var url = dom + "/all";

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
    var url = dom + "/all";

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var html = generateTable(data);
                table.innerHTML = html;

            });
}


function compareProperty(a, b) {
    return a.localeCompare(b);
}

function sortMakeModelPrice(a, b) {
    return compareProperty(a.make, b.make) || compareProperty(a.model, b.model) || a.price - b.price;
}



function generateTable(data) {
    var newData = data.map(function (c) {
        return "<tr><td>" + c.make + "</td>" + "<td>" + c.model + "</td>" +
                "<td>" + c.registrationDate + "</td>" + "<td>" + c.modelYear + "</td>" +
                "<td>" + c.horsepower + "</td>" + "<td>" + c.mileage + "</td>" +
                "<td>" + c.doors + "</td>" + "<td>" + c.price + "</td></tr>";
    });
    return "<tr><th><img id = \"make\" src = \"Make.png\"></img></th>" + "<th><img id = \"model\" src = \"Model.png\"></img></th>" + "<th><img id = \"registration\" src = \"Registration Date.png\"></img></th>" +
            "<th><img id = \"modelyear\" src = \"Model Year.png\"></img></th>" + "<th><img id = \"horsepower\" src = \"Horsepower.png\"></img></th>" +
            "<th><img id = \"mileage\" src = \"Milage.png\"></img></th>" + "<th><img id = \"doors\" src = \"Doors.png\"></img></th>" + "<th><img id = \"price\" src = \"Price.png\"></img></th></tr>" + newData.join("");


}