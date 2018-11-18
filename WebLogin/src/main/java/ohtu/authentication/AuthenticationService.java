package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;
import static java.lang.Character.isLetter;

public class AuthenticationService {

    private UserDao userDao;
    private CreationStatus status;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConf) {
        status = new CreationStatus();
        if (userDao.findByName(username) != null) {
            status.addError("username already taken");
        }

        check(username, password);
        
        if (!password.equals(passwordConf)) status.addError("password and password confirmation do not match");

        if (status.isOk()) userDao.add(new User(username, password));

        return status;
    }

    private void check(String username, String password) {
        usernameCheck(username);
        passwordCheck(password);
    }
    
    private void usernameCheck(String username) {
        char[] characters = username.toCharArray();
        for (int i=0; i<characters.length; i++) {
            if (!isLetter(characters[i])) {
                status.addError("username should consist of letters");
                break;
            }
        }
        if (username.length() < 3) status.addError("username should have at least 3 characters");
    }
    
    private void passwordCheck(String password) {
        char[] characters = password.toCharArray();
        int i = 0;
        for (;i<characters.length; i++) {
            if (!isLetter(characters[i])) break;
        }
        if (i == characters.length) status.addError("password should contain number or special character");
        else if (password.length() < 8) status.addError("password should have at least 8 characters");
    }
}
