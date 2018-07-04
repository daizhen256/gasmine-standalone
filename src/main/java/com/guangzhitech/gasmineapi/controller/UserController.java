package com.guagnzhitech.gasmineapi.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guagnzhitech.gasmineapi.mapper.UserMapper;
import com.guagnzhitech.gasmineapi.model.User;
import com.guagnzhitech.gasmineapi.support.JsonArg;

@RestController
public class UserController extends BaseController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/api/admin/users", produces = MEDIA_TYPE)
    public String listAdminUser() {
        JSONArray array = new JSONArray();
        userMapper.findAll().forEach(user -> {
            JSONObject object = new JSONObject();
            object.put("id", user.getId());
            object.put("username", user.getUsername());
            object.put("enabled", user.isEnabled());
            array.put(object);
        });
        return objectResult(array);
    }

    @Transactional
    @PostMapping(value = "/api/admin/users", produces = MEDIA_TYPE)
    public String saveAdminUser(
            @JsonArg("$.username") String username,
            @JsonArg("$.password") String password) {
        if (userMapper.findByUsername(username) != null) {
            return errorMessage("Username already exists");
        }
        User user = new User(username, password);
        userMapper.save(user);
        JSONObject object = new JSONObject();
        object.put("id", user.getId());
        object.put("username", user.getUsername());
        object.put("enabled", user.isEnabled());
        return objectResult(object);
    }

    @Transactional
    @PostMapping(value = "/api/admin/users/{id}", produces = MEDIA_TYPE)
    public String editAdminUser(
            @PathVariable("id") Long id,
            @JsonArg("$.username") String username,
            @JsonArg("$.password") String password,
            @JsonArg("$.enabled") boolean enabled) {
        User user = userMapper.getOne(id);
        user.setUsername(username);
        user.setEnabled(enabled);
        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
        }
        JSONObject object = new JSONObject();
        object.put("id", user.getId());
        object.put("username", user.getUsername());
        object.put("enabled", user.isEnabled());
        return objectResult(object);
    }

}
