package ua.nure.serdiuk.repository;

import ua.nure.serdiuk.entity.User;

import java.util.HashMap;

public class UserRepository {

    private static final HashMap<String, User> USERS = new HashMap<>();

    private static final UserRepository INSTANCE = new UserRepository();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    public void add(User user) {
        if (USERS.containsKey(user.getLogin())) {
            throw new RuntimeException(String.format("User with login %s already exists", user.getLogin()));
        }
        USERS.put(user.getLogin(), user);
    }

    public User get(String login) {
        if (!USERS.containsKey(login)) {
            throw new RuntimeException(String.format("User with login %s was not found", login));
        }
        return USERS.get(login);
    }
}
