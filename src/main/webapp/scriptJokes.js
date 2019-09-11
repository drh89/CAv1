fetch("/CAv1/api/Jokes/all")
        .then(res => res.json())
        .then(data => {
            console.log("data", data);
            document.getElementById("text").innerHTML = data;
        })