var sendApproveContainersButton = document.getElementById("sendApproveContainersButton");

let host = "localhost"
let port = 2000

sendApproveContainersButton.addEventListener('click', () => {
    let containerId = document.getElementById("containerIdApprove").value;

    data = {
        createdAt: null,
        containerIds: containerId
    }

    console.log(data)
    console.log(JSON.stringify(data))
    fetch('http://' + host + ":" + port + '/api/containers/approve', {
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

