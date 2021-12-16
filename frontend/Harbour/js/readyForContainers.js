var readyForContainersButton = document.getElementById("sendReadyForContainersButton");

let host = "localhost"
let port = 2000

readyForContainersButton.addEventListener('click', () => {
    let containerIds_str = document.getElementById("containerIds").value;
    let containerIds = containerIds_str.split(",");

    data = {
        createdAt: null,
        containerIds: containerIds
    }

    console.log(data)
    console.log(JSON.stringify(data))
    fetch('http://' + host + ":" + port + '/api/containers/ready', {
        method: 'post',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(data)
    })
        .then(response => response.text())
        .then(resp_txt => {
            console.log(resp_txt)
            document.getElementById("response").innerText=resp_txt
        })
        .catch(error => console.log(error))
})

