const form = document.querySelector(".user");
const firstName = document.querySelector("#firstName");
const lastName = document.querySelector("#lastName");
const email = document.querySelector("#email");
const password = document.querySelector("#password");
const repeatPassword = document.querySelector("#repeatPassword");

form.addEventListener("submit", (event) => {
     event.preventDefault();
     if(password.value !== repeatPassword.value){
        alert("Passwords must match");
        return;
     }
     const user = {
        first_name: firstName.value,
        last_name: lastName.value,
        email: email.value,
        password: password.value,
     }
     createUser(user);
});

function createUser (user) {
    fetch("api/user", {
        method: "POST",
        body: JSON.stringify(user),
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(res => res.json())
    .then(data => console.log(data))
    .catch(error => console.log(error))
}