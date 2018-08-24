package cn.xxyangyoulin.action;

import cn.xxyangyoulin.entity.User;
import cn.xxyangyoulin.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterAction {
    public Object register(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        Object uri;

        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);

        UserService userService = new UserService();
        userService.register(user);


        return "success";

    }
}
