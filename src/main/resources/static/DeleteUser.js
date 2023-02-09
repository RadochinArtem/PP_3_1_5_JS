const formDelete = document.getElementById('formDelete')
formDelete.addEventListener('submit', e =>{
    e.preventDefault();
    const formData = new FormData(formDelete);
    fetch("rest/users/"+formData.get("id"), {
        method: "DELETE"
    })
        .then(() => getUsers());
    $("#ModalDelete").modal("hide");
    formDelete.reset();
})
