package web.controller;

import domain.User;
import service.IUserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获取用户填写的登录用户名
        String username = request.getParameter("username");
        //获取用户填写的登录密码
        String password = request.getParameter("password");
        IUserService service = new UserServiceImpl();
        User user = service.loginUser(username, password);
        if (user == null) {
            String message = String.format("sorry,username or password error,please login again !!<meta http-equiv='refresh' content='2;url=%s'", request.getContextPath() + "/servlet/LoginUIServlet");
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("user", user);
        String message = String.format("congratulations:%s login successfully<meta http-equiv='refresh' content='3;url=%s'", user.getUserName(), request.getContextPath() + "/index.jsp");
        request.setAttribute("message", message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
        public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            doGet(request, response);
        }
}
