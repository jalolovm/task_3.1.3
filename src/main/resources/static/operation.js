document.getElementById("editModal").addEventListener("submit", editUser)

function editUser(e) {
    e.preventDefault()

    let id = document.getElementById("idEdit").value
    let username = document.getElementById("editUserUsername").value
    let password = document.getElementById("editUserPassword").value
    let roles = setRoles(Array.from(document.getElementById("roles_arr").selectedOptions).map(option => option.value))


    fetch("/admin/rest", {
        method: "Put",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            id: id,
            username: username,
            password: password,
            roles: roles
        })
    }).finally(() => {
        $("#editUser").modal("hide")
        getAll()
    })
}


function setRoles(role_arr) {
    let roles = []

    if (role_arr.indexOf("ROLE_ADMIN") !== -1) {
        roles.push({id: 2, role: "ROLE_ADMIN"})
    }

    if (role_arr.indexOf("ROLE_USER") !== -1) {
        roles.push({id: 1, role: "ROLE_USER"})
    }
    return roles
}


document.getElementById("deleteUser").addEventListener("submit", deleteUser)


function deleteUser(e) {
    e.preventDefault()

    let id = document.getElementById("idDelete").value
    let username = document.getElementById("deleteUserUsername").value
    let password = document.getElementById("deleteUserPassword").value

    fetch("/admin/rest", {
        method: "Delete",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            id: id,
            username: username,
            password: password,
        })
    }).finally(() => {
        $("#deleteUser").modal("hide")
        getAll()
    })
}

document.getElementById("newUserForm").addEventListener("submit", createUser)

function createUser(e) {
    e.preventDefault()

    let username = document.getElementById("newName").value
    let password = document.getElementById("newPassword").value
    let roles = setRoles(Array.from(document.getElementById("newRoles").selectedOptions).map(options => options.value))

    fetch("/admin/rest", {
        method: "Post",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            username: username,
            password: password,
            roles: roles
        })
    }).finally(() => {
        getAll()
        document.getElementById("users-tab").click()
        document.getElementById("newUserForm").reset()
    }
)
}