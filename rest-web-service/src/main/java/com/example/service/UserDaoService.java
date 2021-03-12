package com.example.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.example.exception.UserNotFoundException;
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

        //here from list users it filter the u.getid and match with id if match the id then
        //return the id or else it return null;
        return users.stream().filter(u -> u.getId() == id)
                .findFirst().orElseThrow(() -> new UserNotFoundException(id +" not found"));

        //same as above code where the stream api
//        for (User user : users) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
//        throw new UserNotFoundException(id + " not found");
    }
}
