

var table = document.getElementById("table");
var btnAll = document.getElementById("btnAll");
var dom = "http://localhost:8080/CAv1/api/cars";




btnAll.addEventListener("click", getAllCars);



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