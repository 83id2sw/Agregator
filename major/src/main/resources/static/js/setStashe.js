
function createNewSet() {

    let name = document.querySelector("#name")
    let description = document.querySelector("#description")

    let products = document.querySelectorAll(".products")

    var data = {
        name: name.value,
        description: description.value,
    };

    for (let i = 0; i < products.length; i++) {
        data[products[i].id] = products[i].querySelector("#checkbox").checked
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/lookamaker/add",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });

    success()
}

function success(data) {
    document.querySelector("#description").value = ""
    document.querySelector("#name").value = ""
    let products = document.querySelectorAll(".products")
    for (let i = 0; i < products.length; i++) {
        products[i].querySelector("#checkbox").checked = false;
    }
    document.querySelector("#success").innerHTML = "Успешно сохранено"
}