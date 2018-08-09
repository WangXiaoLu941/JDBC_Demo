package wxl.contorller;

import wxl.bean.Users;
import wxl.service.ServiceFactory;
import wxl.service.user.UserService;
import wxl.util.Md5Encrypt;
import wxl.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    //不实例化service层对象  让工厂去实例化
    private UserService userService;

    //当用户访问我们这个servlet的时候 先执行init
    @Override
    public void init() throws ServletException {
        userService=(UserService) ServiceFactory.getServiceImpl("userService");
    }

    @Override
    public Class getServletClass() {
        System.out.println("=====02:UserServlet===》getServletClass");
        return UserServlet.class;
    }

    public  String  register(HttpServletRequest req, HttpServletResponse resp){
        //获取用户输入的参数
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        Users users=new Users();
        users.setUserName(userName);
        try {
            users.setPassword(Md5Encrypt.getEncryptedPwd(password));
            System.out.println(users.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        users.setUserType(0);  //设置用户类型
        int num= userService.add(users);
        if (num>0){
            return  "main";
        }else{
            return "register";
        }
    }


    public ResultUtil login(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("====>UserServlet===>login");
        //获取用户登录的用户名和密码
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        ResultUtil util=new ResultUtil();
        //得从数据库中获取一个用户名  如果没有用户名不需要再执行后续代码
        if (userName.equals("admin")){
            util.resultSuccess(userName);
        }else{
            util.resultFail("用户名错误");
        }
        //调用MD5验证密码
        return util;
    }
}
