
let loadContainers = document.getElementById("loadContainers");
let unloadContainers = document.getElementById("unloadContainers");
let berthId = document.getElementById("ligplaatsId");
let tabel = document.getElementById("get_all_berths");
let fillTabel = document.getElementById("get_all_berths")
let response = document.getElementById("load_process");

let host0 = "193.191.169.28"
let port0 =  80

loadContainers.addEventListener('click', async () => {
    response.data = "";
    fetch('http://' + host0 + ":" + port0 + '/api/berth/load/' + berthId.value, {
        method: 'get',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*"
        },
    })
        .then(response => {
            if(response.status===200){
                return response.text();
            }
            else if(response.status === 400){
                location_response.value= "Invalid berth id";
            }
            else{
                location_response.value= "berth not found";
            }
        })
        .then( data => {response.value = data;})
        .catch(error => console.log(error))
});

unloadContainers.addEventListener('click', async () => {
    response.data = "";
    fetch('http://' + host0 + ":" + port0 + '/api/berth/unload/' + berthId.value, {
        method: 'get',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*"
        },
    })
        .then(response => {
            if(response.status===200){
                return response.text();
            }
            else if(response.status === 400){
                location_response.value= "Invalid berth id";
            }
            else{
                location_response.value= "berth not found";
            }
        })
        .then( data => {response.value = data;})
        .catch(error => console.log(error))
});
fillTabel.addEventListener('click', async () => {
    response.data = "";
    fetch('http://' + host0 + ":" + port0 + '/api/berth/all', {
        method: 'get',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*"
        },
    })
        .then(response => {
            if(response.status===200){
                return response.json();
            }
            else if(response.status === 400){
                location_response.value= "Invalid berth id";
            }
            else{
                location_response.value= "berth not found";
            }
        })
        .then( data => {zetInTabel(data);})
        .catch(error => console.log(error))
});


let i = 0;
function zetInTabel(response){
    document.getElementById("alle_ligplaatsen");
    ligpaatsen = response;

    for (let ligplaats of ligpaatsen) {
        i = i + 1;
        let tr = document.createElement("tr");
        let th = document.createElement("th");
        th.scope = "row";
        th.value = i;
        tr.appendChild(th);
        let td_1 = document.createElement("td");
        td_1.innerText = ligplaats.berthId;
        let td_2 = document.createElement("td");
        td_2.innerText = ligplaats.size;
        let td_3 = document.createElement("td");
        td_3.innerText = ligplaats.state;
        let td_4 = document.createElement("td");
        td_4.innerText = ligplaats.berthNumber;
        let td_5 = document.createElement("td");
        td_5.innerText = ligplaats.worker.id;
        let td_6 = document.createElement("td");
        td_6.innerText = ligplaats.worker.state;
        let td_7 = document.createElement("td");
        td_7.innerText = ligplaats.vesselId;

        tr.appendChild(td_1);
        tr.appendChild(td_2);
        tr.appendChild(td_3);
        tr.appendChild(td_4);
        tr.appendChild(td_5);
        tr.appendChild(td_6);
        tr.appendChild(td_7);
        document.getElementById("alle_ligplaatsen").appendChild(tr);
    }
}

function showHideFoto(){
    let img = document.getElementById("poppei");
    if (img.classList.contains("foto-hide")){
        img.classList.remove("foto-hide");
        img.classList.add("foto-show");
    }
    else {
        img.classList.remove("foto-show");
        img.classList.add("foto-hide");
    }
}
