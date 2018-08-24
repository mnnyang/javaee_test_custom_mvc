package cn.xxyangyoulin.action;

import cn.xxyangyoulin.entity.User;
import cn.xxyangyoulin.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction {
    public String login(HttpServletRequest req, HttpServletResponse resp){
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");

        String resultString = null;

        User user = new User();
        user.setName(name);
        user.setPwd(pwd);

        UserService service = new UserService();
        User loginUser = service.login(user);

        if (loginUser == null){
            resultString = "fail";
        }else {
            req.getSession().setAttribute("userInfo", user);

            resultString = "success";
        }

        return resultString;
    }
}
