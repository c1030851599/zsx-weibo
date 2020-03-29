package weibo.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import weibo.Service.UserService;
import weibo.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

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


    @PostMapping("/Register2")
    public String register(Model model,User user){
        String username=user.getUsername();
        String password=user.getPassword();

        User user1 = userService.findUser(username);

        //2.调用service层判断用户名是否存在（这一块其实是要查询数据库的）
        if(user1!=null){
            //存在
            model.addAttribute("msg", "此员工已存在，请更换一个!");
            return  "/login/register";

        }else {
            String passworedMD5 =  md5(password,"sxt");
            user.setPassword(passworedMD5);
            user.setSalt("sxt");
            userService.register(user);
            return "redirect:/Login";
        }
    }

//    md5加密：
public static final String md5(String password, String salt){
  //加密方式
  String hashAlgorithmName = "MD5";
  //盐：为了即使相同的密码不同的盐加密后的结果也不同
  ByteSource byteSalt = ByteSource.Util.bytes(salt);
  //密码
  Object source = password;
  //加密次数
  int hashIterations = 2;
  SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
  return result.toString();
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
