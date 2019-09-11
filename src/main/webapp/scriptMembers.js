/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


fetch("/CAv1/api/member/all")
        .then(res => res.json())
        .then(data => {
            console.log("data", data);
            document.getElementById("text").innerHTML = toHtml(data);
        })



var btn = document.getElementById("btn");
btn.addEventListener("click", reloadData);


function reloadData() {
    fetch("/CAv1/api/member/all")
            .then(res => res.json())
            .then(data => {
                console.log("data", data);
                document.getElementById("text").innerHTML = toHtml(data);
            })
    }

function toHtml(data) {
    var html;
    html = "<table class=\"table\">" +
                "<thead>" +
                    "<tr>" +
                        "<th scope=\"col\">Name</th>" +
                        "<th scope=\"col\">Student ID</th>" +
                        "<th scope=\"col\">Color</th>"
                    "</tr>" +
                "</thead>" +
            "<tbody>";
    var htmlMap = data.map((member) =>
            "<tr>" +
                "<td>" + member.student_name + "</td>" +
                "<td>" + member.student_id + "</td>" +
                "<td>" + member.student_color + "</td>"
    );
    html += htmlMap.join("");
    html += "</tbody>" + "</table>";

    return html;
}