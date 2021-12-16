var arrivedWithContainersButton = document.getElementById("arrivedWithContainersButton");

let host_2 = "localhost"
let port_2 = 2000

arrivedWithContainersButton.addEventListener('click', () => {
    let containerIds_str = document.getElementById("arrivedContainerIds").value;
    let containerIds = containerIds_str.split(",");

    var location = {}
    location.locationType = document.getElementById("arrivedLocationType").value
    location.locationIdentifier = document.getElementById("arrivedLocationIdentifier").value

    data = {
        createdAt: null,
        at: JSON.stringify(location),
        containerIds: containerIds
    }

    console.log(data)
    console.log(JSON.stringify(data))

    fetch('http://' + host_2 + ":" + port_2 + '/api/containers/arrived', {
        method: 'post',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*",
            // "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(data)
    })
        .then(response => response.text())
        .then(resp_txt => {
            console.log(resp_txt)
            document.getElementById("arrivedResponse").innerText=resp_txt
        })
        .catch(error => console.log(error))
})

