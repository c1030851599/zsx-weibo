package weibo.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import weibo.Service.*;
import weibo.common.WeiboMethod;
import weibo.pojo.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class PageController {
    @Autowired
    WeiboService weiboService;

    @Autowired
    UserService userService;

    @Autowired
    plListService plListService;

    @Autowired
    hfplListService hfplListService;

    @Autowired
    loveService likeService;

    @Autowired
    collectService collectService;

    @Autowired
    WeiboMethod method;

    @GetMapping("/index")
    @ApiOperation(value = "跳转到首页")
    public String index() {
        return "redirect:/queryAll";
    }

    @GetMapping("/Index")
    @ApiOperation(value = "跳转到首页")
    public String Index() {


        return "index";
    }

//    @GetMapping("/weChat")
//    @ApiOperation(value = "跳转到聊天页面")
//    public String weChat(HttpSession session,Model model) {
//        User user = (User) session.getAttribute("user");
//
//        return "/wechat";
//    }





    @GetMapping("/service")
    @ApiOperation(value = "跳转到聊天页面")
    public String service() {

        return "/websocket_service.html";
    }

    @GetMapping("/client")
    @ApiOperation(value = "跳转到聊天页面")
    public String client() {

        return "/websocket_client.html";
    }




    @GetMapping("/article")
    @ApiOperation(value = "跳转到文章页面")
    public String one(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByArticle();
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());

        List<weiboCustom> weibos1 = method.getWeibos(weibos,collect,love);

        model.addAttribute("weibos", weibos1);
        return "article";
    }

    @GetMapping("/picture")
    @ApiOperation(value = "跳转到图片区")
    public String two(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByImg();
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());


        List<weiboCustom> weibos1 = method.getWeibos(weibos,collect,love);

        model.addAttribute("weibos", weibos1);

        return "picture";
    }

    @GetMapping("/video")
    @ApiOperation(value = "跳转到视频区")
    public String three(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByVideo();
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());

        List<weiboCustom> weibos1 = method.getWeibos(weibos, collect, love);

        model.addAttribute("weibos", weibos1);


        return "video";
    }

    @GetMapping("/person")
    @ApiOperation(value = "跳转到个人页面")
    public String pserson(HttpSession session, Model model) {

        // 当前用户信息
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryMyWeibo(user);
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());



        List<weiboCustom> weibos1 = method.getWeibos(weibos, collect, love);

        model.addAttribute("weibos", weibos1);

        model.addAttribute("user",user);
        model.addAttribute("personalLabel",user.getpersonal_label());

        return "person";
    }


    @GetMapping("/{username}")
    @ApiOperation(value = "跳转到某用户的个人页面")
    public String Otherpserson(HttpSession session,@PathVariable("username") String username, Model model) {

        // 当前用户信息
        User me = (User) session.getAttribute("user");

        User user =  userService.findUser(username);
        List<weiboCustom> weibos = weiboService.queryMyWeibo(user);
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());

        List<weiboCustom> weibos1 = method.getWeibos(weibos, collect, love);

        gz gz = new gz();
        gz.setGzusername(me.getUsername());
        gz.setGzedusername(user.getUsername());
//        查看是否关注了此用户：(true 已关注， false 未关注)
        boolean b = userService.ifGZ(gz);

        model.addAttribute("ifGZ", b);
        model.addAttribute("weibos", weibos1);
        model.addAttribute("user",user);
        model.addAttribute("personalLabel",user.getpersonal_label());
        String myUsername = me.getUsername();
        if(username.equals(myUsername)){
            return "redirect:/person";
        }
        return "GZperson";
    }


    @GetMapping("/psersonCollect")
    @ApiOperation(value = "跳转到个人收藏页")
    public String psersonCollect(HttpSession session, Model model) {

        // 当前用户信息
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryCollectWeibo(user);
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());

        List<weiboCustom> weibos1 = method.getWeibos(weibos, collect, love);

        model.addAttribute("weibos", weibos1);
        model.addAttribute("user",user);
        model.addAttribute("personalLabel",user.getpersonal_label());

        return "person_collect";
    }




    @GetMapping("/music")
    @ApiOperation(value = "跳转到音乐区")
    public String music(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByMusic();
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());


        List<weiboCustom> weibos1 = method.getWeibos(weibos, collect, love);

        model.addAttribute("weibos", weibos1);


        return "music";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "/login/login";
//    }

    @GetMapping("/register")
    @ApiOperation(value = "跳转到注册页面")
    public String register() {
        return "/login/register";
    }


    @GetMapping("/updatePersonInfo")
    public String updatePersonInfo(HttpSession session, Model model) {
        User User = (User) session.getAttribute("user");
        model.addAttribute("user",User);
        model.addAttribute("personalLabel",User.getpersonal_label());
        return "updatePersonInfo";
    }




}
