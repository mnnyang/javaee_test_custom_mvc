package cn.xxyangyoulin.dao;

import cn.xxyangyoulin.entity.User;

public class UserDao {
    public User login(User user){
        if ("tom".equals(user.getName()) && "root".equals(user.getPwd())){
            return user;
        }else {
            return null;
        }
    }

    public void register(User user){
        System.out.println("注册");
    }
}
