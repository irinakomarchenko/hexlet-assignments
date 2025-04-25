@import exercise.dto.users.UserPage
@param UserPage page

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>
<h1>User Info</h1>
<p>First name: ${page.getUser().getFirstName()}</p>
<p>Last name: ${page.getUser().getLastName()}</p>
<p>Email: ${page.getUser().getEmail()}</p>
</body>
</html>
