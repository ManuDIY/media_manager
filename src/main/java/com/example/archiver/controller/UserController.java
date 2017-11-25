package com.example.archiver.controller;

import com.example.archiver.models.User;
import com.example.archiver.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
public class UserController {

    @RequestMapping("/create")
    @ResponseBody
    public String create(String name, String email)
    {
        String userId = "";
        try {
            User user = new User(name, email);
            userDao.save(user);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.getMessage();
        }
        return "User successfully created with id = " + userId;
    }

    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId = "";
        try {
            User user = userDao.findByEmail(email);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }


    @Autowired
    private UserDao userDao;

}
