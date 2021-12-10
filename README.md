Special Department

## Story

As your Codecool Journey comes closer to its conclusion, the time has come for a final Teamwork Project that will put to test all of the programming skills you've obtained so far (and some new ones you will learn on the way)!

You have the freedom of choosing your teammates (assemble a team of 3-4 students) and the project's topic this time. Think of an app that you would find useful in your daily activities, a tool that an employee of a certain industry might crave, a fun game or something completely out of the box.

This project is meant for 4 sprints at least, but it may keep you company until the end of the course, or even much longer. Who knows? Although we will not give you any direct tasks to fulfill, there will be some technical requirements for each sprint. You are expected to make incremental changes in a Scrum way, developing the project further and further, adding new features, technologies, etc.

## Tasks

1.Technical requirements - backend.
    - A user can register into the application by setting at least their username, e-mail address, and password.
    - A user can log in to the application.
    - The user can log out from the application.
    - There are at least two different roles defined in the application.
    - There are at least 3 endpoints, that are only available for authenticated users.
    - There are at least 5 endpoints, that are only available for authenticated users.
    - There is an admin page, which lists all users of the application, available only with the `admin` role.

2. [OPTIONAL] After a successful registration (username not taken, etc.) send a welcome e-mail to your new users!
    - After successful registration, the user receives an email welcoming them to the page.

3. [OPTIONAL] The `Forgot your password` button on the login page, that can help restore the password by email.
    - There is a `Forgot your password` button on the login page.
    - After clicking on the `Forgot your password` button the user can give their email.
    - After the email given for recovering the password, the email is validated. If no such email can be found in the database, an error is shown.
    - After choosing `Forgot your password` with a registered account, an email is received.
    - The `Forgot your password` email contains a link, that redirects to a page, where the user can set a new password. The password belonging to the email address is overwritten to the new one given.

4. [OPTIONAL] A possibility to log in with `Google Id` using guide provided in resources
    - New project in Google's credential page is added. It has an `OAuth 2.0 Client IDs` record.
    - User secrets are used to store Google's `ClientSecret`. Ensure this data is not saved in the repo!
    - Google authentication is enabled. Users can successfully authenticate via `GoogleId`.

5. A couple of technical requirements defined below.
    - There is a registration page, where a user can register by setting at least their username, e-mail address, and password.
    - There is a login page, where a user can log in to the application, by giving their username and password.
    - The username of the logged-in user is shown on the page's header.
    - There is a logout button on the page. After hitting the logout button, the user is redirected to the login page.
