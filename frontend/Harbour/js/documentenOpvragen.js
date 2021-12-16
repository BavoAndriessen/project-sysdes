const button = document.getElementById('documenten_kwaliteit');
const input_doucment = document.getElementById("document");
const input_vessel = document.getElementById("vessel");
const input_containers = document.getElementById("containers");

let host9 = "193.191.169.28"
let port9 =  80

button.addEventListener('click', async () => {
    input_containers.value = "";
    input_doucment.value = "";
    fetch('http://' + host9 + ":" + port9 + '/api/administration/documents/'+ input_vessel.value, {
        method: 'get',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*"
        }
    })
        .then(response => response.json())
        .then(data => {
            data.forEach(printDocument)
        })
        .catch(error => console.log(error))
});

function printDocument(document){
    console.log(document.vesselId);
    input_doucment.value = document.documentId;
    // input_vessel.value = document.vesselId;
    input_containers.value = "";
    console.log(document.documentId);
    document.containers.forEach(cont => {
        input_containers.value += cont + "\n";
    })
}


