const DeleteModal = document.getElementById('ModalDelete')
DeleteModal.addEventListener('show.bs.modal', event => {
    // Button that triggered the modal
    const Dbutton = event.relatedTarget
    // Extract info from data-bs-* attributes
    const userIdDelete = Dbutton.getAttribute('data-bs-userId')
    const userNameDelete = Dbutton.getAttribute('data-bs-userFirstName')
    const userLastNameDelete = Dbutton.getAttribute('data-bs-userLastName')
    const userAgeDelete = Dbutton.getAttribute('data-bs-userAge')
    const userEmailDelete = Dbutton.getAttribute('data-bs-userEmail')
    // Update the modal's content.

    const modalUserIdDelete = DeleteModal.querySelector('#userIdDelete')
    const modalNameDelete = DeleteModal.querySelector('#userNameDelete')
    const modalUserLastNameDelete = DeleteModal.querySelector('#userLastNameDelete')
    const modalUserAgeDelete = DeleteModal.querySelector('#userAgeDelete')
    const modalUserEmailDelete = DeleteModal.querySelector('#userEmailDelete')

    modalUserIdDelete.value = userIdDelete
    modalNameDelete.value = userNameDelete
    modalUserLastNameDelete.value = userLastNameDelete
    modalUserAgeDelete.value = userAgeDelete
    modalUserEmailDelete.value = userEmailDelete

})
