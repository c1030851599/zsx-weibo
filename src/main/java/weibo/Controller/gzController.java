package weibo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo.Service.UserService;
import weibo.Service.WeiboService;
import weibo.Service.collectService;
import weibo.pojo.User;
import weibo.pojo.gz;

import javax.servlet.http.HttpSession;

@RestController
public class gzController {

    @Autowired
    UserService userService;

    @Autowired
    WeiboService weiboService;

    @Autowired
    collectService collectService;

    @GetMapping("/gz")
    public String gz(HttpSession session,String username){

        // 当前用户信息
        User me = (User) session.getAttribute("user");
        gz gz = new gz();
        gz.setGzusername(me.getUsername());
        gz.setGzedusername(username);
        userService.saveGz(gz);
        return "";
    }

    @GetMapping("/cancelGZ")
    public String cancelGZ(HttpSession session,String username){
        // 当前用户信息(关注者)
        User me = (User) session.getAttribute("user");
        gz gz = new gz();
        gz.setGzusername(me.getUsername());
        gz.setGzedusername(username);
        userService.cancelGz(gz);
        return "";
    }



}
