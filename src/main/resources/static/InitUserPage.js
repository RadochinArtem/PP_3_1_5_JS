let navbarEmail = document.getElementById("navbar.email")
let navbarRoles = document.getElementById("navbar.roles")

let id = document.getElementById("id")
let firstname = document.getElementById("firstname")
let lastname = document.getElementById("lastname")
let age = document.getElementById("age")
let email = document.getElementById("email")
let rolestable = document.getElementById("roles")

fetch("user/users/current_user")
    .then(response => response.json())
    .then(data => {
        navbarEmail.textContent = data["email"]
        email.textContent = data["email"]

        let roles = data["roles"]
        let stringRoles = ''
        for(let i = 0; i < roles.length; i++) {
            stringRoles += roles[i]['name'].split('_')[1] + ' '
        }
        navbarRoles.textContent = stringRoles
        rolestable.textContent = stringRoles
        id.textContent = data["id"]
        firstname.textContent = data["firstName"]
        lastname.textContent = data["lastName"]
        age.textContent = data["age"]

    })


