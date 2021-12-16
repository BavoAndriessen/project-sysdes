const get_transfer_commands = document.getElementById("gettransfercommands");
const transfer_response  = document.getElementById("transfercommandsresponse")

let host6 = "193.191.169.28"
let port6 = 80

get_transfer_commands.addEventListener('click', async () => {
    fetch('http://' + host6 + ":" + port6 + '/api/containers/commands/transfer', {
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
        })
        .then( data => {transfer_response.value = data;})
        .catch(error => console.log(error))


});
