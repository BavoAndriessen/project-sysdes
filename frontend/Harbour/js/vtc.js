const registervesselvtcbutton = document.getElementById("registervesselvtc");
const vesselidforvtc = document.getElementById("vesselidforvtc");
const vesselsizeforvtc = document.getElementById("vesselsize");
const vesselstateforvtc = document.getElementById("vesselstate");
const gatesstatusresponsebutton = document.getElementById("getgatesresponsebutton");
const getgatesresonse = document.getElementById("getgatesresponse");
const vesselregisterresponse = document.getElementById("vesselregisterresponse");
const gatestateresponse = document.getElementById("gatestateresponse");
const gateid = document.getElementById("gateidforvtc");
const changestateresponsebutton = document.getElementById("changestateresponsebutton");

let port4 = 80;
let host4 = "193.191.169.28";
if(registervesselvtcbutton){
    registervesselvtcbutton.addEventListener('click', async () => {
        let data = createData();
        fetch('http://' + host4 + ":" + port4 + '/api/vtc/', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                "Connection": "keep-alive",
                "Accept": "*/*"
            },
            body: data
        }).then(response => response.text())
            .then(response => vesselregisterresponse.value = response)
            .catch(error => console.log(error))

    });
}
if(gatesstatusresponsebutton) {
    gatesstatusresponsebutton.addEventListener('click', async () => {
        fetch('http://' + host4 + ":" + port4 + '/api/vtc/commands/change_gate', {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                "Connection": "keep-alive",
                "Accept": "*/*"
            },
        }).then(response => response.json())
            .then(response => getgatesresonse.value = response)
            .catch(error => console.log(error))

    });
}
if(changestateresponsebutton) {
    changestateresponsebutton.addEventListener('click', async () => {
        fetch('http://' + host4 + ":" + port4 + '/api/vtc/gate/' + gateid.value, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
                "Connection": "keep-alive",
                "Accept": "*/*"
            },
        }).then(response => response.text())
            .then(response => gatestateresponse.value = response)
            .catch(error => console.log(error))

    });
}
function createData(){
    return JSON.stringify({"vesselId": vesselidforvtc.value,"size":vesselsizeforvtc.value,"state": vesselstateforvtc.value});
}








