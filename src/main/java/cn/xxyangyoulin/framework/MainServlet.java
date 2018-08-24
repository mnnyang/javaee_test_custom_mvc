package cn.xxyangyoulin.framework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class MainServlet extends HttpServlet {

    private ActionManager actionManager;

    @Override
    public void init() throws ServletException {
        actionManager = new ActionManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try{
            String uri = req.getRequestURI();

            String actionName = uri.substring(uri.lastIndexOf("/")+1,
                    uri.indexOf(".action"));

            System.out.println(uri+"-------"+actionName);

            ActionMapping actionMapping = actionManager.getActionMapping(actionName);

            String className = actionMapping.getClassName();

            String method = actionMapping.getMethod();

            Class<?> clazz = Class.forName(className);

            Object obj = clazz.newInstance();

            Method declaredMethod = clazz.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);

            String runResult = (String) declaredMethod.invoke(obj,req,resp);
            Result result = actionMapping.getResults().get(runResult);

            String type = result.getType();
            String page = result.getPage();

            if ("redirect".equals(type)) {
                resp.sendRedirect(req.getContextPath()+page);
            }else {
                req.getRequestDispatcher(page).forward(req,resp);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
