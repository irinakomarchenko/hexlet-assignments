@layout("layout/page.jte")

<form action="/users" method="post">
    <div>
        <label>
            first Name
            <input type="text" name="firstName" />
        </label>
    </div>
    <div>
        <label>
            last Name
            <input type="text" name="lastName" />
        </label>
    </div>
    <div>
        <label>
            Email
            <input type="email" required name="email" />
        </label>
    </div>
    <div>
        <label>
            Password
            <input type="password" required name="password" />
        </label>
    </div>
    <div>
        <label>
            Password confirmation
            <input type="password" required name="passwordConfirmation" />
        </label>
    </div>
    <input type="submit" value="Register" />
</form>