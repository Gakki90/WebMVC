package web.controller;

import domain.User;
import exception.UserExistException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import service.IUserService;
import service.impl.UserServiceImpl;
import util.WebUtils;
import web.formbean.RegisterFormBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterFormBean formBean = WebUtils.request2Bean(request, RegisterFormBean.class);
            if (formBean.validate() == false) {
                request.setAttribute("formbean", formBean);
                request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
                return;
            }
            User user = new User();
            try {
                ConvertUtils.register(new DateLocaleConverter(), Date.class);
                BeanUtils.copyProperties(user, formBean);
                user.setId(WebUtils.makeId());
                IUserService service = new UserServiceImpl();
                service.registerUser(user);
                String message = String.format("register successfully,go to login page after 3 seconds!!<meta http-equiv='refresh' content='3;url=%s'/>", request.getContextPath() + "/servlet/LoginUIServlet");
                request.setAttribute("message", message);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace(); // 在后台记录异常
                request.setAttribute("message", "对不起，注册失败！！");
                request.getRequestDispatcher("/message.jsp").forward(request, response);
            } catch (UserExistException e) {
                e.printStackTrace();
                formBean.getErrors().put("message", "sorry,fail to register ");
                request.setAttribute("formbean", formBean);
                request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            }
        }

        public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }

}
