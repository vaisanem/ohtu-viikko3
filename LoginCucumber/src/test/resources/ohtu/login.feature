Feature: User can log in with valid username/password-combination

    Scenario: user can login with correct password
       Given command login is selected
       When  username "pekka" and password "akkep" are entered
       Then  system will respond with "logged in"

    Scenario: user cannot login with inproper password
        Given command login is selected
        When username "pekka" and password "kepakko" are entered
        Then system will respond with "wrong username or password"

    Scenario: nonexistent user cannot login
        Given command login is selected
        When username "akkep" and password "pekka" are entered
        Then system will respond with "wrong username or password"
