package cn.xxyangyoulin.service;

import cn.xxyangyoulin.dao.UserDao;
import cn.xxyangyoulin.entity.User;

public class UserService {
    private UserDao ud = new UserDao();

    public User login(User user){
        return ud.login(user);
    }

    public void register(User user){
        ud.register(user);
    }
}
