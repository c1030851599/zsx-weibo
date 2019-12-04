package weibo.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import weibo.Service.*;
import weibo.common.WeiboMethod;
import weibo.pojo.User;
import weibo.pojo.collect;
import weibo.pojo.love;
import weibo.pojo.weiboCustom;

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

//    @GetMapping("/6")
//    public String index02() {
//        return "indexWebUpload-2";
//    }
//
//    @GetMapping("/7")
//    public String index03() {
//        return "indexWebUpload";
//    }



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

    @RequestMapping("/demo")
    public String demo(){
        return "search2";
    }




}
