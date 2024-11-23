package com.itrainu.Doa;

import com.itrainu.Bean.UserBean;
import com.itrainu.Module.User;

public interface UserDao {
    boolean addUser(User user);
    boolean addUser(UserBean user);
	boolean isValidUser(String username, String password);
}
