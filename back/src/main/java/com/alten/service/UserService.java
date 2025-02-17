package com.alten.service;

import com.alten.models.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserByEmail(String email);
}
