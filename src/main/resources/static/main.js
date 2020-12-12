function getAll() {
    fetch("/admin/rest").then(
        response => {
            response.json().then(
                user => {
                    if (user.length > 0) {
                        let temp = " "

                        user.forEach((u) => {

                            temp += `
                                <tr>
                                <td id="id${u.id}">${u.id}</td>
                                <td id="username${u.id}">${u.username}</td>
                                <td id="rolesUser${u.id}">${u.roleString}</td>
                                <td><div class="btn-group"><button onclick='modalWindowEdit(${u.id})' data-target="#editUser"
                                                            data-toggle="modal" class="btn btn-primary">Edit</button></div></td>
                                <td><div class="btn-group"><button onclick="modalWindowDelete(${u.id})" data-target="#deleteUser"
                                data-toggle="modal" class="btn btn-danger">Delete</button></div></td></tr>`
                        })

                        document.getElementById("usersTable").innerHTML = temp
                    }
                })
        }
    )
}
getAll()