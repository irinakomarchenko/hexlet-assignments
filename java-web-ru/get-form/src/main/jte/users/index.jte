@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page
@layout("layout/page.jte")


<h1>Список пользователей</h1>

<form action="/users" method="get" class="mb-4">
    <input type="text" name="term" placeholder="Поиск по имени..." value="${page.getTerm()}" />
    <button type="submit">Найти</button>
</form>

@if (page.getUsers().isEmpty()) {
    <p>Ничего не найдено.</p>
    } @else {
<ul class="list-group">
    @for (var user : page.getUsers()) {
    <li class="list-group-item">
        <a href="/users/${user.getId()}">
            ${user.getFirstName()} ${user.getLastName()}
        </a>
    </li>
    }
    @endfor
</ul>
}
@endif




