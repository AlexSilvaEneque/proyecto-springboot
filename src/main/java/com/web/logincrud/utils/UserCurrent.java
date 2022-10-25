package com.web.logincrud.utils;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import com.web.logincrud.model.UserModel;
import com.web.logincrud.service.UserService;

public class UserCurrent {
    @Autowired
    UserService userv;
    
    public UserModel current(Principal p) {
        UserModel ucurrent = userv.getByEmail(p.getName()).get();
        return ucurrent;
    }
}
