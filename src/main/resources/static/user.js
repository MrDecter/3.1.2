$(async function () {
    await loadUser();
});

async function loadUser() {
    fetch("http://localhost:8080/api/user")
        .then(r => r.json())
        .then(data => {
            $('#navUsername').append(data.username);
            let roles = data.roles.map(role => " " + role.rolename);
            $('#navRoles').append(roles);
        })
        .catch((error) => {
            alert(error);
        });
}