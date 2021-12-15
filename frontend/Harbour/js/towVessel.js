const button_2 = document.getElementById('towvessel');
const input_vessel = document.getElementById("vesselid");
const service_response = document.getElementById("towvesselresponse");

button_2.addEventListener('click', async () => {
    fetch('http://localhost:2001/api/towingpilotage/towvessel/'+ input_vessel.value, {
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




