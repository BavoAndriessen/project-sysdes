const scan_container = document.getElementById("scancontainer");

const scanresponse = document.getElementById("scancontainerresponse");

scan_container.addEventListener('click', async () => {
    let data = createData();
    console.log(data);
    fetch('http://localhost:2000/api/containers/scan', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept": "*/*"
        },
        body: data
    }).then(response => response.text())
        .then(data => scanresponse.value = data)
        .catch(error => console.log(error))
});


function createData(){
    let containerid = document.getElementById("containerid");
    let status = document.getElementById("status");
    let containerlocation = document.getElementById("containerlocationtype");
    let locationid = document.getElementById("locationid");
    return JSON.stringify({"containerId": containerid.value, "newStatus": status.value, "newLocation":{"locationType":containerlocation.value,"locationIdentifier":locationid.value}});
}
