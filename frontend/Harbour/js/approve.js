var sendApproveContainersButton = document.getElementById("sendApproveContainersButton");

let host = "193.191.169.28"
let port = 80

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

