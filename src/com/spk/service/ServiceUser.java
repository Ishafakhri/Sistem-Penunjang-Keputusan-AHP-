package com.spk.service;

import com.spk.model.User;

public interface ServiceUser {
    
    boolean isUserExist();
    boolean validateUsername(User model);
    void insertData(User model);
}
