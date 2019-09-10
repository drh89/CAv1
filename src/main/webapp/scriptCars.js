

var table = document.getElementById("table");
var btnAll = document.getElementById("btAll");
var dom = "http://localhost:8080/jpareststarter/api/cars";




btnAll.addEventListener("click",getAllCars);



function getAllCars(){
    var url = dom + "/all";
    var conf = {method : "GET"};
    var html = null;
    var promise = fetch(url, conf);
    
    promise.then(res=> res.jsson())
            .then(function(data){
                 html = generateTable(data);
    })
    table.innerHTML = html;
}





function generateTable(data){
    var newData = data.map(function (c){
        return "<tr><td>" + c.make + "</td>" + "<td>" + c.model + "</td>" +
                "<td>" + c.registrationDate + "</td>" + "<td>" + c.modelYear + "</td>" +
                "<td>" + c.horsepower + "</td>" + "<td>" + c.mileage + "</td>" +
                "<td>" + c.doors + "</td>" + "<td>" + c.price + "</td></tr>"; 
    })
    return "<th>Make</th>" + "<th>Model</th>" + "<th>Registration Date</th>" + 
            "<th>Model Year</th>" + "<th>Horsepower</th>" + 
            "<th>Mileage</th>" + "<th>Doors</th>" + "<th>Price</th>" + newData.join("");
        
    
}