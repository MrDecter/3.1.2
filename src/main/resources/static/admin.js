$(async function () {
    await allUsers();

});

async function allUsers() {
    const table = $('#bodyAllUserTable');
    table.empty()
    fetch("http://localhost:8080/admin/api")
        .then(r => r.json())
        .then(data => {
            data.forEach(user => {
                let users = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.lastname}</td>
                            <td>${user.age}</td>
                            <td>${user.roles.map(role => " " + role.roles)}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" id="buttonEdit" data-action="edit" data-id="${user.id}" data-target="#edit">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" id="buttonDelete" data-action="delete" data-id="${user.id}" data-target="#delete">Delete</button>
                            </td>
                        </tr>)`;
                table.append(users);
            })
        })
}