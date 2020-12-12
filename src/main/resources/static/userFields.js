function modalWindowEdit(id){
document.getElementById("idEdit").value=id
document.getElementById("headerEdit").innerText="Edit user: " + $("#username" + id).text()
document.getElementById("editUserUsername").value=$("#username" + id).text()
document.getElementById("editUserPassword").value=" "
}

function modalWindowDelete(id){
document.getElementById("idDelete").value=id
document.getElementById("headerDelete").innerText="Delete user: " + $("#username" + id).text()
document.getElementById("deleteUserUsername").value=$("#username" + id).text()
}