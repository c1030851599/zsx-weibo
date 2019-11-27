package weibo.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import weibo.Service.UserService;
import weibo.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/login")
    public String userLogin(Model model, User user, HttpServletResponse response){
        if(user==null){
            return "/login/login";
        }
        String username=user.getUsername();
        String password=user.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,false);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //此步将 调用realm的认证方法
            currentUser.login(token);
        } catch(IncorrectCredentialsException e){
            //这最好把 所有的 异常类型都背会
            model.addAttribute("msg", "账号或者密码错误!");
            return "/login/login";
        } catch (AuthenticationException e) {
            model.addAttribute("msg", "登录失败!");
            return "/login/login";
        }
        return "redirect:/queryAll";
    }

    //配合shiro配置中的默认访问url
    @RequestMapping(value="/Login")
    public String getLogin(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response){
        return "/login/login";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value="logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){

        //subject的实现类DelegatingSubject的logout方法，将本subject对象的session清空了
        //即使session托管给了redis ，redis有很多个浏览器的session
        //只要调用退出方法，此subject的、此浏览器的session就没了
        try {
            //退出
            SecurityUtils.getSubject().logout();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "/login/login";

    }

    @RequestMapping(value="403")
    public String unAuth(){
        return "403";
    }

//    @RequestMapping(value="/login")
//    public String userLogin(Model model, User user, HttpSession session){
//
//
//        boolean flag = userService.login(user);
//        if (flag){
//            session.setAttribute("user",user);
//            model.addAttribute("user",user);
//            return "index";
//        }else{
//            model.addAttribute("msg","账号或者密码错误，请重新输入！！！");
//            return "/login/login";
//        }
//    }

}
