@import gg.jte.Content
@param Content content
@param Content footer = null

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Сайт пользователей</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="container mt-4">
<header class="mb-4">
    <a href="/" class="me-3">Главная</a>
    <a href="/users">Пользователи</a>
</header>

${content}

@if (footer != null)
    <footer class="mt-5 border-top pt-3">
        ${footer}
    </footer>
@endif
</body>
</html>
