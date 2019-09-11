
getFetchDataAll();
//getFetchDataId(2);
//getFetchDataRandom();
var btn = document.getElementById("btnFetch");
btn.addEventListener("click", buttonEventFetch);

var btn = document.getElementById("btnRandom");
btn.addEventListener("click", getFetchDataRandom);

var btn = document.getElementById("btnAll");
btn.addEventListener("click", getFetchDataAll);



function buttonEventFetch() {
    var id = document.getElementById("jokeInput").value;
    getFetchDataId(id);
}





function getFetchDataAll() {
    fetch("/CAv1/api/Jokes/all")
            .then(res => res.json())
            .then(data => {
                console.log("data", data);
                document.getElementById("tableDiv").innerHTML = tableString(data);
            })
}

function getFetchDataId(id) {
    fetch("/CAv1/api/Jokes/" + id)
            .then(res => res.json())
            .then(data => {
                console.log("data", data);
                document.getElementById("tableDiv").innerHTML = tableString(data);
            })
}

function getFetchDataRandom() {
    fetch("/CAv1/api/Jokes/random")
            .then(res => res.json())
            .then(data => {
                console.log("data", data);
                document.getElementById("tableDiv").innerHTML = tableString(data);
            })
}


function convertToArray(array)
{
    try {
        if(array[0] === undefined)
        {
            throw "undefined";
        }
        return array;
    } catch (e)
    {
        return [array];
    }
}

function getKeyTypes(array)
{
    var keys = array.map(item => Object.keys(item));
    return keys[0];
}

function getKeyTypesTableHeader(array)
{
    var s = "<tr>";
    for (var i of getKeyTypes(array))
    {
        s += "<th scope=\"col\">" + i + "</th>";
    }
    s += "</tr>";
    return s;
}


function getItemContent(item)
{
    return Object.keys(item);
}

function getItemValues(item)
{
    return Object.values(item)
}

function getKeys2(array)
{
    var s = "";
    for (var i = 0; i < array.length; i++)
    {
        s += '<tr>'
        for (var v of getItemValues(array[i]))
        {
            s += "<td>" + v + "</td>";
        }
        s += '</tr>'
    }
    return s;
}

function tableString(array)
{
    array = convertToArray(array);
    var htmlString = '<table class="table">' +
            '<thead>' +
            getKeyTypesTableHeader(array) +
            '</thead>' +
            '<tbody>' +
            '</tr>' +
            '</thead>' +
            '<tbody>' +
            '<tr>' +
            getKeys2(array) +
            '</tr>'
    '</tbody>' +
            '</table>';
    return htmlString;
}

