const button = document.getElementById('askservice');
const input_vessel = document.getElementById("vesselid");
const service_response = document.getElementById("serviceasked");

let host = "193.191.169.28"
let port = 80

button.addEventListener('click', async () => {
    fetch('http://' + host + ":" + port + '/api/maintenanceservice/askforservice/'+ input_vessel.value, {
        method: 'get',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*"
        }
    })
        .then(response => {
            if(response.status===200){
                return response.text();
            }
            })
        .then( data => {service_response.value = data;})
        .catch(error => console.log(error))


});




