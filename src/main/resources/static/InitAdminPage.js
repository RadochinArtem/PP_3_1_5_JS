let navbarEmail = document.getElementById("navbar.email")
let navbarRoles = document.getElementById("navbar.roles")

fetch("admin/users/current_user")
.then(response => response.json())
.then(data => {
    navbarEmail.textContent = data["email"]

    let roles = data["roles"]
    let stringRoles = ''
    for(let i = 0; i < roles.length; i++) {
        stringRoles += roles[i]['name'].split('_')[1] + ' '
    }
    navbarRoles.textContent = stringRoles

})

