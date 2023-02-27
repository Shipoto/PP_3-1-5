const url = 'http://localhost:8080/api/users/'
const json = 'users1.json'
const container = document.getElementById('tbodyTable')
let results = ''

const editModal = new bootstrap.Modal(document.getElementById('editModal'))
// const modalDelete = new bootstrap.Modal(document.getElementById('deleteModal'))

const formUser = document.querySelector('form')

const name = document.getElementById('name')
const surName = document.getElementsByName('surName')
const age = document.getElementById('age')
const department = document.getElementById('department')
const userName = document.getElementById('userName')
const password = document.getElementById('password')

let option = ''

// btnModalEdit.addEventListener('click', () => {
//     // alert('Done')
//     editModal.show()
//     option = 'edit'
// })

// TEST

// let request = new Request(url, {
//     method: "GET",
//     headers: {
//         'Content-Type': 'application/json',
//     },
// });

// function getUsers() {
//     fetch(request).then(res =>
//         res.json()).then(data => {
//         tableUsers = [];
//         if (data.length > 0) {
//             data.forEach(user => {
//                 tableUsers.push(user)
//             })
//         } else {
//             tableUsers = [];
//         }
// console.log(tableUsers)
//         tableUsers;
//     })
// }

// getUsers()

// TEST

const showUsers = (users) => {
    users.forEach(user => {
        results += `<tr>
                        <th>${user.id}</th>
                        <td>${user.name}      </td>
                        <td>${user.surName}   </td>
                        <td>${user.age}       </td>
                        <td>${user.department}</td>
                        <td>${user.username}  </td>
                        <td>${user.roles}     </td>

                        <td><button type="button" id="btnEdit" class="btn btn-primary" data-bs-toggle="modal" >
                        Edit
                            </button></td>

                        <td><button type="button" id="btnDelete" class="btn btn-danger" data-bs-toggle="modal" >
                            Delete
                            </button></td>    

                        
                </tr>
                `
        //<td><button type="button" class="text-center"><a class="btnEdit1 btn btn-primary"></a>Ed</td>
        // <td>${user.Delete}    <a class="btnDelete"></a>Delete</td>
    })
    container.innerHTML = results
}


// other

// fetch(json, {
//
//     mode: 'no-cors',
//     method: 'GET',
//     headers: {
//         'Content-Type': 'application/json',
//     },
// })
//     .then( response => response.json())
//     // console.log(response)
//     .then((data) => showUsers(data))
//     .catch( error => console.log(error))


fetch(url, {
    // mode: 'no-cors',
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
    },
})
    .then( response => response.json())
    // console.log(response)
    .then((data) => showUsers(data))
    .catch( error => console.log(error))



// catching button
const on = (element, event, button, handler) => {
    // console.log(handler)
    element.addEventListener(event, e => {
        if(e.target.closest(button)) {
            handler(e)
        }
    })
}

// pushing button Delete
on(document, 'click', '#btnDelete', e => {
    console.log('do')
    const idRow = e.target.parentNode.parentNode
    const id = idRow.firstElementChild.innerHTML
    alertify.confirm("This is a confirm dialog.",
        function(){
            fetch(json+id, {

                method: 'DELETE'
            })
                .then(res)
            alertify.success('Ok')
        },
        function(){
            alertify.error('Cancel')
        })
})

// pushing button Edit
let idForm = 0
on(document, 'click', '#btnEdit', e => {
    const idRow = e.target.parentNode.parentNode
    idForm = idRow.children[0].innerHTML
    const nameForm = idRow.children[1].innerHTML
    const surNameForm = idRow.children[2].innerHTML
    const ageForm = idRow.children[3].innerHTML
    const departmentForm = idRow.children[4].innerHTML
    const userNameForm = idRow.children[5].innerHTML
    const rolesForm = idRow.children[6].innerHTML

    // name.
    // surName.value = surNameForm
    // age.value = ageForm
    option = 'edit'
    editModal.show()
})


// button for new User
// btnModalNewUser.addEventListener('click', () => {
//     // alert('Done')
//     id.value = ''
//     name.value = ''
//     surName.value = ''
//     modalEdit1.show()
// })

// const show = (bootstra) => {
//     bootstra.forEach(user => {
//         result += <tr>
//             <td>${user.id}</td>
//             <td>${user.name}</td>
//             <td>${user.surName}</td>
//             <td>${user.age}</td>
//             <td>${user.department}</td>
//             <td class="text-center">
//                 <a class="btnEdit btn btn-primary">Edit</a>
//                 <a class="btnDelete btn btn-danger">Delete</a>
//                 </td>
//         </tr>
//     })
//     container.innerHTML = result
// }

// fetch(url)
//     .then( response => response.json())
//     .then( data => show(data) )
//     .catch( error => console.log(error))


