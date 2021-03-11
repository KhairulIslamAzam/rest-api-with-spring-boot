package com.example.service;

import com.example.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int countId = 3;

    static {
        users.add(new User(1, "Azam", new Date()));
        users.add(new User(2, "Asif", new Date()));
        users.add(new User(3, "Nishad", new Date()));
    }

    public List<User> findAllUser() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++countId);
        }
        users.add(user);
        return user;
    }

    public User findById(int id) {
        return users.stream().filter(u-> u.getId() == id)
                .findFirst().orElse(null);
    }
}
