const button_get_container = document.getElementById("getcontainerlocation");
const input_container = document.getElementById("containeridLocation");
const location_response = document.getElementById("containerlocation");

let host8 = "193.191.169.28"
let port8 = 80


button_get_container.addEventListener('click', async () => {
    fetch('http://' + host8 + ":" + port8 + '/api/containers/'+ input_container.value + '/location', {
        method: 'get',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*",
        },
    })
        .then(response => {
            if(response.status === 200){
                return response.json();
            }
            else if(response.status === 400){
                location_response.value= "Invalid container id";
            }
            else{
                location_response.value= "Container not found";
            }
        })
        .then(data => {
            if(data !== undefined){
                location_response.value= "Container location: " + data.locationType + "\nLocation identifier: " + data.locationIdentifier;
            }
        })
        .catch(error => console.log(error))
});


