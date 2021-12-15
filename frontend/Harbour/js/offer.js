let register_button = document.getElementById("register_offer");
let get_button = document.getElementById("get_offer");
let accept_button = document.getElementById("accept_offer");
let refuse_button = document.getElementById("refuse_offer");

let vesselid_input = document.getElementById("vessel");
let lengthofstay_input = document.getElementById("lengthOfStay");
let vesselsize_input = document.getElementById("vesselSize");
let amountofwaste_input = document.getElementById("amountOfWaste");
let containerlist_input = document.getElementById("containerList");
let date_field = document.getElementById("arrivalDate");
let additionalServicesField = document.getElementById("additionalServices");
let offerId_field = document.getElementById("offer");
let price_field = document.getElementById("price");

register_button.addEventListener('click', async() => {
    let date = date_field.value
    let servicesList = [];
    additionalServicesField.value.split("\n").forEach(a => servicesList.push(a));
    let vesselId = vesselid_input.value;
    let lengthOfStay = lengthofstay_input.value;
    let vesselSize = vesselsize_input.value;
    let amountOfWaste = amountofwaste_input.value;
    let containerList = [];
    containerlist_input.value.split("\n").forEach(a => containerList.push({"contents": a}));

    let body = {
        "vesselId": vesselId,
        "arrivalDateTime": date,
        "lengthOfStay": lengthOfStay,
        "vesselSize": vesselSize,
        "amountOfWaste": amountOfWaste,
        "additionalServices": servicesList,
        "containerList": containerList
    };

    console.log(JSON.stringify(body));

    fetch('http://localhost:2005/api/kapiteinsdienst/registerVessel', {
        method: 'post',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*"
        },
        body : JSON.stringify(body)
    })
        .then(response => response.json())
        .then(data => {
            //prijs en offerid in juiste value veld
            console.log(data)
        })
        .catch(error => console.log(error))
});

accept_button.addEventListener('click', () => {
    fetch('http://localhost:2005/api/kapiteinsdienst/'+ vesselsize_input.value +'/offerConfirmation?confirmation=true', {
        method: 'post',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*"
        },
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
        })
        .catch(error => console.log(error))
});

refuse_button.addEventListener('click', () => {
    fetch('http://localhost:2005/api/kapiteinsdienst/'+ vesselsize_input.value +'/offerConfirmation?confirmation=false', {
        method: 'post',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*"
        },
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
        })
        .catch(error => console.log(error))
});
