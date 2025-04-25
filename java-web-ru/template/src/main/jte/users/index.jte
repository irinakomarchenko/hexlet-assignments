@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<h1>Users</h1>
<ul>
    @for(User user : page.getUsers())
        <li>
            <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
        </li>
    @endfor
</ul>
</body>
</html>
