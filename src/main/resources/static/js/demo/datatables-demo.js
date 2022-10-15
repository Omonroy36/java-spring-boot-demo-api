// Call the dataTables jQuery plugin
$(document).ready(function() {
  getUsers();
  $('#dataTable').DataTable();
});

function addUsersToTable (users) {
    const tbody = document.querySelector("#dataTable > tbody");
    for(const user of users){
        const tr = document.createElement("tr");
        const actionButton = document.createElement("td");
        const button = document.createElement("button");
        button.classList.add("btn", "btn-danger", "btn-circle", "btn-sm");
        button.innerHTML = '<i class="fas fa-trash"></i>';
        button.addEventListener("click", () => {
            deleteUser(user.id);
            tbody.remove(tr);
        });
        actionButton.append(button);
        for(const property in user){
            const td = document.createElement("td");
            td.innerHTML = user[property];
            tr.append(td);
        }
        tr.append(actionButton);
        tbody.append(tr);
    }
}

function deleteUser(id) {
    if(!confirm("Do you want to delete this user?")){
       return;
    }
    fetch("api/user/" + id, {
        method:"DELETE"
    })
    .then(res => res.json())
    .then(data => console.log(data))
    .catch(error => console.log(error))
}


function getUsers () {
    fetch("api/user", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            "Authorization": localStorage.token
        }
    })
    .then(res => res.json())
    .then(data => addUsersToTable(data))
    .catch(error => console.log(error))
}
