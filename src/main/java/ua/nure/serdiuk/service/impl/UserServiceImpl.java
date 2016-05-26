package ua.nure.serdiuk.service.impl;

import ua.nure.serdiuk.entity.User;
import ua.nure.serdiuk.repository.UserRepository;
import ua.nure.serdiuk.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public boolean auth(String email, String password) {
        User user = UserRepository.getInstance().get(email);
        return user.getPassword().equals(password);
    }
}
