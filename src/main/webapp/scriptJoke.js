fetch("/CA1/api/joke/all")
        .then(res => res.json())
        .then(data => {
            console.log("data", data);
            document.getElementById("text").innerHTML = data;
        })