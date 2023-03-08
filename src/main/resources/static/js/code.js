const url = 'http://localhost:8080/api/users/'
const container = document.getElementById('tbodyTable')
const containerNewUser = document.getElementById('tbodyNewUser')
let results = ''

const editModal = new bootstrap.Modal(document.getElementById('editModal'))
// const modalDelete = new bootstrap.Modal(document.getElementById('deleteModal'))
const newUser = new bootstrap.Tab(document.querySelector('#myTab button[data-bs-target="#newUserTabContent"]'))

const submitNewUser = new bootstrap.Tab(document.querySelector('#myTab button[data-bs-target="#newUserTabContent"]'))


const triggerEl = document.querySelector('#myTab button[data-bs-target="#newUserTabContent"]')
const newUser1 = bootstrap.Tab.getInstance(triggerEl)

console.log('answer' + newUser)
// const newUser = bootstrap.Tab(document
//     .querySelector('#v-pills-tabContent'[href="#profile"]')
// triggerEl).show()


let option = ''

// create users table
const showUsers = (users) => {
    let count = 0
    users.forEach(user => {
        results += `<tr >
                        <th>${user.id}</th>
                        <td>${user.name}</td>
                        <td>${user.surName}</td>
                        <td>${user.age}</td>
                        <td>${user.department}</td>
                        <td>${user.username}</td>
                        <td>${user.roles.map(role => role.name.substring(5))}     </td>

                        <td><button type="button" data-id="${count}" id="btnEdit" class="btn btn-primary" data-bs-toggle="modal" >
                        Edit
                            </button></td>

                        <td><button type="button"  id="btnDelete" class="btn btn-danger" data-bs-toggle="modal" >
                            Delete
                            </button></td>    
                    </tr>
                    `
        count += 1
    })

    container.innerHTML = results

}


// fetch and typing users table

function getMainPage() {
    fetch(url, {
        // mode: 'no-cors',
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then( response => response.json())
        .then((data) => showUsers(data))
        .catch( error => console.log(error)
        )}

getMainPage()

// fetching current user
fetch(url + "user")
    .then(res => res.json())
    .then(data => {
        currentUser = data;
        showUser(currentUser);
    })



// show current user
const showUser = (user) => {
    let res = "";
    res += `<tr>
                        <th>${user.id}        </th>
                        <td>${user.name}      </td>
                        <td>${user.surName}   </td>
                        <td>${user.age}</td>
                        <td>${user.department}</td>
                        <td>${user.username}  </td>
                        <td>${user.roles.map(role => role.name.substring(5))}     </td>
                </tr>
                `
    // console.log(user.roles)
    document.getElementById("tbodyCurrentUser").innerHTML = res;
}






// show user by ID


const showUserById = (user) => {
    let res = "";
    res += `<div>
                        <th>${user.id}        </th>
                        <td>${user.name}      </td>
                        <td>${user.surName}   </td>
                        <td>${user.age}</td>
                        <td>${user.department}</td>
                        <td>${user.username}  </td>
                        <td>${user.roles.map(role => role.name.substring(5))}     </td>
                </div>
                `
    res;
}

// fetching user by ID
const curUs = (id) => {fetch(url + id)
    // function curUs(id) {
    // fetch(url + id)
    .then(res => res.json())
    .then(data => {
        currentUser = data;
        // console.log("DATA", currentUser)
        showUser(currentUser);
    })
}

// BUTTONS
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
    const idRow = e.target.parentNode.parentNode
    const id = idRow.firstElementChild.innerHTML
    alertify.confirm(`Delete user with id: ${id}.`,
        function(){
            fetch(url+id, {
                method: 'DELETE'
            })
                .then(res => res.json())
                .then(() => location.reload())
            //   alertify.success('Ok')
        },
        function(){
            //   alertify.error('Cancel')
        })
})

// parsing consts Edit in forms
const formUser = document.querySelector('modalEditForm')

const nameEdit = document.getElementById('nameEdit')
const surName = document.getElementById('surName')
const age = document.getElementById('age')
const department = document.getElementById('department')
const userName = document.getElementById('userName')
const password = document.getElementById('password')
const roles = document.getElementById('rolesEdited')
// console.log('roles', roles)

// Roles for newUser
function getRoles(role1, role2) {

    let userRoles = [];
    const roleAdmin = {id:1, name:'ROLE_ADMIN'};
    const roleUser = {id:2, name:'ROLE_USER'};
    // alert(role1)
    // alert(role2)
    // alert(roles[1].value)
    if (role1) {
        userRoles.push(roleAdmin)
    } if (role2) {
        userRoles.push(roleUser)
    }
    // alert(userRoles)
    return userRoles
}

// Roles for current edited User

// function getUserRoles(rolesForm) {
//     alert(rolesForm)
//     let currentUserRoles = []
//     const roleAdmin = {id:1, name:'ROLE_ADMIN'};
//     const roleUser = {id:2, name:'ROLE_USER'};
//     if (rolesForm.includes('ADMIN')) {
//         currentUserRoles.push(roleAdmin)
//     } if (rolesForm.includes('USER')) {
//         currentUserRoles.push(roleUser)
//     }
//     return currentUserRoles
// }

// pushing button Edit and scraping data from every table's row
// let rolesForm = '';
let idForm = 0;
let formEdit = '';
on(document, 'click', '#btnEdit', e => {
    const idRow = e.target.parentNode.parentNode
    idForm = idRow.children[0].innerHTML
    const nameForm = idRow.children[1].innerHTML
    console.log(nameForm)
    const surNameForm = idRow.children[2].innerHTML
    const ageForm = idRow.children[3].innerHTML
    const departmentForm = idRow.children[4].innerHTML
    const userNameForm = idRow.children[5].innerHTML
    const rolesForm = idRow.children[6].innerHTML

    const count_id = idRow.children[7].children[0].getAttribute('data-id')
    // alert(count_id)
    // alert(rolesForm)


    // typing data in fields modal's window
    nameEdit.value = nameForm
    surName.value = surNameForm
    age.value = ageForm
    department.value = departmentForm
    userName.value = userNameForm

    roles.options[0].selected = false
    roles.options[1].selected = false

    if (rolesForm.includes('ADMIN')) {roles.options[0].selected = true}
    if (rolesForm.includes('USER')) {roles.options[1].selected = true}


    option = 'edit'
    editModal.show()
    formEdit = document.forms["modalEditForm"];
    formEdit.addEventListener('submit', saveEditedUser)
    // console.log(formEdit)


    function showUsersString(name, surName, age, department, userName, roles, count_id) {
        // console.log(roles)
        let rowId = document.getElementById('tbodyTable').children[count_id]
        rowId.children[1].innerText = name
        rowId.children[2].innerText = surName
        rowId.children[3].innerText = age
        rowId.children[4].innerText = department
        rowId.children[5].innerText = userName
        rowId.children[6].innerText = roles.map(role => role.name.substring(5))


    }

    function saveEditedUser(e) {
        e.preventDefault()
        let newUserRoles = []
        // alert('SAVEE')

        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: idForm,
                name: formEdit.nameEdit.value,
                surName: formEdit.surName.value,
                age: formEdit.age.value,
                department: formEdit.department.value,
                userName: formEdit.userName.value,
                roles: getRoles(formEdit.roles.options[0].selected,
                    formEdit.roles.options[1].selected)
            })
            // })
            // =========== RELOAD ONE STRING =============
        }).then(() => showUsersString(
            formEdit.nameEdit.value,
            formEdit.surName.value,
            formEdit.age.value,
            formEdit.department.value,
            formEdit.userName.value,
            getRoles(formEdit.roles.options[0].selected,
                formEdit.roles.options[1].selected),
            count_id
        ))
        // })
        // .then(response => response.json())
        // .then(response => window.location.reload())
        editModal.hide()
    }
})





// PUSH TAB NEW USER
on(document, 'click', '#newUserTab', e => {
        // alert('klik')
        const roles = document.getElementById('rolesNew')
        // console.log(roles)
        roles.value = [{id:1, name:'ROLE_ADMIN'}, {id:2, name:'ROLE_USER'}]
        // console.log('rolesNew', rolesNew)
    }
)


// pushing AddNewUser submit button
const form = document.forms["formNewUser"];




// console.log(form)
form.addEventListener('submit', addNewUser)

function addNewUser(e) {
    // e.preventDefault()

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            // id:0,
            name:form.newName.value,
            surName:form.newSurName.value,
            age:form.age.value,
            department:form.department.value,
            userName:form.userName.value,
            password:form.password.value,
            roles:getRoles(form.roles.options[0].selected, form.roles.options[1].selected)
            // roles:[{id:1, name:'ROLE_ADMIN'},{id:2, name:'ROLE_USER'}]


            // roles:{id: 1, name: 'ROLE_ADMIN'}
            // roles:[{id:1, name:'ROLE_ADMIN'}]
            // roles:getRoles([ADMIN, USER])
        })
    })

// }).then(() => showUsers())
}








// =======================  END  =================================
// =======================  END  =================================
// =======================  END  =================================
// =======================  END  =================================
// =======================  END  =================================
// =======================  END  =================================











// 2 SAVE BUTTON EDIT WINDOW

// form.addEventListener('submit', btnSaveEdit)

// function saveEdited(e) {
//     e.preventDefault()
//     fetch(url, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
// },
// body: JSON.stringify({
// })
// })


// pushing button New user
// let idNewUserForm = 0;
// on(document, 'click', '#newUserTab', e => {

//     name1.value = '';
//     surName.value = '';
//     age.value = '';
//     department.value = '';
//     userName.value = '';
//     password.value = '';
//     roles.value = '';

//     option = 'newUser'
//     console.log("show new")
//     newUser1.show()
// })







// }).then(response => response.json())
//     .then( data => {
//         console.log(data)
//         const newUser = []
//         newUser.push(u)
//         console.log(newUser)
//         showUsers(newUser)

// }).then(() => {
//     form.reset()
//     showUsers()
// })
// }


// pushing AddNewUser submit button
// const form = document.forms["formNewUser"];

// form.addEventListener('submit', (e) => {
//     // alert('Done')
//     e.preventDefault()
//     fetch(url, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//     },
//     body: JSON.stringify({
//         newName:newName.value,
//         surName:surName.value,
//         age:age.value,
//         department:department.value,
//         userName:userName.value,
//         roles:roles.value
//     })
// }).then(() => {
//     form.reset();
//     showUsers();
// })


//     .then(response => response.json())
//     .then( data => {
//         const newUser = []
//         newUser.push(data)
//         console.log(newUser)
//         showUsers(newUser)
//     })
// })


















// if(option == 'create') {
//     alert("create")
//     fetch(url, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//     },
//     body: JSON.stringify({
//         // newName:newName.value
//         // surName:surName.value,
//         // age:age.value,
//         // department:department.value,
//         // userName:userName.value,
//         // roles:roles.value
//     })
//     .then(response => response.json())
//     .then( data => {
//         const newUser = []
//         newUser.push(data)
//         console.log(newUser)
//         showUsers(newUser)
//     })
//     })
// }
// if (option == 'edit') {
//     alert("edit")
//     fetch(url+idForm,{
//         method: 'PUT',
//         headers: {
//             'Content-Type':'application/json'
//         },
//         body: JSON.stringify({
//             descripcion:descripcion.value,
//             precio:precio.value,
//             stock:stock.value
//         })
//     })
//     .then( response => response.json() )
//     .then( response => location.reload() )
// }
// modalArticulo.hide()
// }
// id.value = ''
// name.value = ''
// surName.value = ''
// modalEdit1.show()
// )







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
