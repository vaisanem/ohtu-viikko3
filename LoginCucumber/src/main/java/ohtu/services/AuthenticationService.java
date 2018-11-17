package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Character.isLetter;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

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

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        return usernameInvalid(username) || passwordInvalid(password);
    }
    
    private boolean usernameInvalid(String username) {
        char[] characters = username.toCharArray();
        for (int i=0; i<characters.length; i++) {
            if (!isLetter(characters[i])) return true;
        }
        return username.length() < 3;
    }
    
    private boolean passwordInvalid(String password) {
        char[] characters = password.toCharArray();
        int i = 0;
        for (;i<characters.length; i++) {
            if (!isLetter(characters[i])) break;
        }
        if (i == characters.length) return true;
        else return password.length() < 8;
    }
}
