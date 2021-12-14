const button = document.getElementById('badge');
const button_pay = document.getElementById('pay');
const input_staff = document.getElementById("staff");
const input_confirmation = document.getElementById("confirmation");
const input_pay = document.getElementById("paycheck");

button.addEventListener('click', async () => {
    fetch('http://localhost:2004/api/administration/staff/'+ input_staff.value + '?time=' + getTimeNow(), {
        method: 'put',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*"
        }
    })
        .then(response => response.text())
        .then(data => printConfirmation(data))
        .catch(error => console.log(error))
});

button_pay.addEventListener('click', async () => {
    fetch('http://localhost:2004/api/administration/staff/'+ input_staff.value, {
        method: 'get',
        headers: {
            "Content-Type": "application/json",
            "Connection": "keep-alive",
            "Accept" : "*/*"
        }
    })
        .then(response => {
            if(response.status === 200){
                return response.json();
                // console.log(data)
                // printPayCheck(data);
            }
            else if(response.status === 400){
                printShiftNotOVer();
            }
            else{
                printStaffNotFound();
            }
        })
        .then(data => {
            if(data !== undefined){
                printPayCheck(data);
            }
        })
        // .then(response => response.text())
        // .then(data => printPayCheck(data ? JSON.parse(data) : {}))
        .catch(error => console.log(error))
});

function printConfirmation(conf){
    input_confirmation.value = conf;
    input_pay.value = "";
}

function printPayCheck(paycheck){
    input_confirmation.value = "";
    input_pay.value = "";
    input_pay.value += paycheck.firstName + " " + paycheck.lastName + "\n";
    input_pay.value += paycheck.hoursWorked + " hours worked for " + paycheck.wage+ " euros per hour\n";
    input_pay.value += paycheck.pay + " euros earned";
}

function printStaffNotFound(){
    input_pay.value = "";
    input_pay.value += "Staff " + input_staff.value + " is not found \n";
}

function printShiftNotOVer(){
    input_pay.value = "";
    input_pay.value += "Shift is not over yet \n";
}

function getTimeNow() {
    let today = new Date();
    let date = today.getFullYear()+'-'+(addLeadingZero(today.getMonth())+1)+'-'+addLeadingZero(today.getDate());
    let time = addLeadingZero(today.getHours()) + ":" + addLeadingZero(today.getMinutes());
    let dateTime = date+' '+time;
    console.log(dateTime);
    return dateTime;
}

function addLeadingZero(n) {
    if(n < 10){
        n = "0" + n;
    }
    return n;
}
