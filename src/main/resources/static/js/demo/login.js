const form = document.querySelector(".user");
const email = document.querySelector("#email");
const password = document.querySelector("#password");

function login (credentials) {
    fetch("api/login",{
      method: "POST",
      body: JSON.stringify(credentials),
      headers: {
          "Content-Type": "application/json"
      }})
    .then(res => res.text())
    .then(data => {
        if(data !== "FAIL"){
            localStorage.token = data;
            window.location.href = "tables.html"
        } else {
            alert("Wrong credentials");
        }
    })
    .catch(error => console.log(error))
}

form.addEventListener("submit", (event) => {
   event.preventDefault();
   if(email.value !== "" && password.value !== ""){
        const credentials = {
            email: email.value,
            password: password.value
        }
        login(credentials);
   } else {
        alert("Email and/or password can not be empty")
   }

})