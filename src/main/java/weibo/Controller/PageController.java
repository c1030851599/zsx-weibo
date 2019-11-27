package weibo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import weibo.Service.*;
import weibo.pojo.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    @GetMapping("/index")
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
    public String one(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByArticle();
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());

//        将数据库中的时间从date转为string ，并且安装 yyyy-MM-dd HH:mm:ss 格式 。
        for (weiboCustom weibo :weibos) {
            weibo.getUser().setPersonalLabel( weibo.getUser().getpersonal_label());
            Date postTime = weibo.getPostTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(postTime);
            weibo.setPostTimeAsString(timeFormat);

            if (weibo.getZfwbid()!=null){
                weiboCustom weibo1 = weiboService.queryWeiboByID(weibo.getZfwbid());
                zfweibo zfweibo =new zfweibo();
                zfweibo.setWeiboId(weibo1.getWeiboId());
                zfweibo.setContent(weibo1.getContent());
                zfweibo.setMusic(weibo1.getMusic());
                zfweibo.setPic1(weibo1.getPic1());
                zfweibo.setPic2(weibo1.getPic2());
                zfweibo.setPic3(weibo1.getPic3());
                zfweibo.setPic4(weibo1.getPic4());
                zfweibo.setPic5(weibo1.getPic5());
                zfweibo.setPic6(weibo1.getPic6());
                zfweibo.setVideo(weibo1.getVideo());
                zfweibo.setPostTime(weibo1.getPostTime());
                zfweibo.setUser(((weiboCustom) weibo1).getUser());
                weibo.setZfweibo(zfweibo);
            }

//            是否对这一条微博点赞：
            love.setWbid(weibo.getWeiboId());
            love love1 = likeService.ifLike(love);
            if(love1 == null){
                weibo.setIflike(false);
            }else{
                weibo.setIflike(true);
            }

//            是否对这一条微博收藏：
            collect.setWbid(weibo.getWeiboId());
            collect ifcollect = collectService.ifcollect(collect);
            if(ifcollect == null){
                weibo.setIfcollect(false);
            }else{
                weibo.setIfcollect(true);
            }

            List<plList> plList =  plListService.selectByWeiboId(weibo.getWeiboId());
            for (plList plL : plList) {
                List<hfplList> hfplLists = hfplListService.selectByPlId(plL.getId());
                for (hfplList hfpl : hfplLists) {
                    hfpl.setUserHeadImg(userService.findUser(hfpl.getUsername()).getHeadImgName());
                }
                plL.setHfplLists(hfplLists);
                plL.setUserHeadImg(userService.findUser(plL.getUsername()).getHeadImgName());
            }

            weibo.setPlLists(plList);
        }

        model.addAttribute("weibos", weibos);
        return "article";
    }

    @GetMapping("/picture")
    public String two(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByImg();
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());


//        将数据库中的时间从date转为string ，并且安装 yyyy-MM-dd HH:mm:ss 格式 。
        for (weiboCustom weibo :weibos) {
            weibo.getUser().setPersonalLabel( weibo.getUser().getpersonal_label());
            Date postTime = weibo.getPostTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(postTime);
            weibo.setPostTimeAsString(timeFormat);

            if (weibo.getZfwbid()!=null){
                weiboCustom weibo1 = weiboService.queryWeiboByID(weibo.getZfwbid());
                zfweibo zfweibo =new zfweibo();
                zfweibo.setWeiboId(weibo1.getWeiboId());
                zfweibo.setContent(weibo1.getContent());
                zfweibo.setMusic(weibo1.getMusic());
                zfweibo.setPic1(weibo1.getPic1());
                zfweibo.setPic2(weibo1.getPic2());
                zfweibo.setPic3(weibo1.getPic3());
                zfweibo.setPic4(weibo1.getPic4());
                zfweibo.setPic5(weibo1.getPic5());
                zfweibo.setPic6(weibo1.getPic6());
                zfweibo.setVideo(weibo1.getVideo());
                zfweibo.setPostTime(weibo1.getPostTime());
                zfweibo.setUser(((weiboCustom) weibo1).getUser());
                weibo.setZfweibo(zfweibo);
            }

//            是否对这一条微博点赞：
            love.setWbid(weibo.getWeiboId());
            love love1 = likeService.ifLike(love);
            if(love1 == null){
                weibo.setIflike(false);
            }else{
                weibo.setIflike(true);
            }

//            是否对这一条微博收藏：
            collect.setWbid(weibo.getWeiboId());
            collect ifcollect = collectService.ifcollect(collect);
            if(ifcollect == null){
                weibo.setIfcollect(false);
            }else{
                weibo.setIfcollect(true);
            }

            List<plList> plList =  plListService.selectByWeiboId(weibo.getWeiboId());
            for (plList plL : plList) {
                List<hfplList> hfplLists = hfplListService.selectByPlId(plL.getId());
                for (hfplList hfpl : hfplLists) {
                    hfpl.setUserHeadImg(userService.findUser(hfpl.getUsername()).getHeadImgName());
                }
                plL.setHfplLists(hfplLists);
                plL.setUserHeadImg(userService.findUser(plL.getUsername()).getHeadImgName());
            }

            weibo.setPlLists(plList);
        }

        model.addAttribute("weibos", weibos);
        return "picture";
    }

    @GetMapping("/video")
    public String three(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByVideo();
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());



        //        将数据库中的时间从date转为string ，并且安装 yyyy-MM-dd HH:mm:ss 格式 。
        for (weiboCustom weibo :weibos) {
            weibo.getUser().setPersonalLabel( weibo.getUser().getpersonal_label());
            Date postTime = weibo.getPostTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(postTime);
            weibo.setPostTimeAsString(timeFormat);

            if (weibo.getZfwbid()!=null){
                weiboCustom weibo1 = weiboService.queryWeiboByID(weibo.getZfwbid());
                zfweibo zfweibo =new zfweibo();
                zfweibo.setWeiboId(weibo1.getWeiboId());
                zfweibo.setContent(weibo1.getContent());
                zfweibo.setMusic(weibo1.getMusic());
                zfweibo.setPic1(weibo1.getPic1());
                zfweibo.setPic2(weibo1.getPic2());
                zfweibo.setPic3(weibo1.getPic3());
                zfweibo.setPic4(weibo1.getPic4());
                zfweibo.setPic5(weibo1.getPic5());
                zfweibo.setPic6(weibo1.getPic6());
                zfweibo.setVideo(weibo1.getVideo());
                zfweibo.setPostTime(weibo1.getPostTime());
                zfweibo.setUser(((weiboCustom) weibo1).getUser());
                weibo.setZfweibo(zfweibo);
            }

//            是否对这一条微博点赞：
            love.setWbid(weibo.getWeiboId());
            love love1 = likeService.ifLike(love);
            if(love1 == null){
                weibo.setIflike(false);
            }else{
                weibo.setIflike(true);
            }

//            是否对这一条微博收藏：
            collect.setWbid(weibo.getWeiboId());
            collect ifcollect = collectService.ifcollect(collect);
            if(ifcollect == null){
                weibo.setIfcollect(false);
            }else{
                weibo.setIfcollect(true);
            }

            List<plList> plList =  plListService.selectByWeiboId(weibo.getWeiboId());
            for (plList plL : plList) {
                List<hfplList> hfplLists = hfplListService.selectByPlId(plL.getId());
                for (hfplList hfpl : hfplLists) {
                    hfpl.setUserHeadImg(userService.findUser(hfpl.getUsername()).getHeadImgName());
                }
                plL.setHfplLists(hfplLists);
                plL.setUserHeadImg(userService.findUser(plL.getUsername()).getHeadImgName());
            }

            weibo.setPlLists(plList);
        }

        model.addAttribute("weibos", weibos);
        return "video";
    }

    @GetMapping("/person")
    public String pserson(HttpSession session, Model model) {

        // 当前用户信息
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryMyWeibo(user);
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());



//        将数据库中的时间从date转为string ，并且安装 yyyy-MM-dd HH:mm:ss 格式 。
        for (weiboCustom weibo :weibos) {
            weibo.getUser().setPersonalLabel( weibo.getUser().getpersonal_label());
            Date postTime = weibo.getPostTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(postTime);
            weibo.setPostTimeAsString(timeFormat);

            if (weibo.getZfwbid()!=null){
                weiboCustom weibo1 = weiboService.queryWeiboByID(weibo.getZfwbid());
                zfweibo zfweibo =new zfweibo();
                zfweibo.setWeiboId(weibo1.getWeiboId());
                zfweibo.setContent(weibo1.getContent());
                zfweibo.setMusic(weibo1.getMusic());
                zfweibo.setPic1(weibo1.getPic1());
                zfweibo.setPic2(weibo1.getPic2());
                zfweibo.setPic3(weibo1.getPic3());
                zfweibo.setPic4(weibo1.getPic4());
                zfweibo.setPic5(weibo1.getPic5());
                zfweibo.setPic6(weibo1.getPic6());
                zfweibo.setVideo(weibo1.getVideo());
                zfweibo.setPostTime(weibo1.getPostTime());
                zfweibo.setUser(((weiboCustom) weibo1).getUser());
                weibo.setZfweibo(zfweibo);
            }

//            是否对这一条微博点赞：
            love.setWbid(weibo.getWeiboId());
            love love1 = likeService.ifLike(love);
            if(love1 == null){
                weibo.setIflike(false);
            }else{
                weibo.setIflike(true);
            }

//            是否对这一条微博收藏：
            collect.setWbid(weibo.getWeiboId());
            collect ifcollect = collectService.ifcollect(collect);
            if(ifcollect == null){
                weibo.setIfcollect(false);
            }else{
                weibo.setIfcollect(true);
            }

            List<plList> plList =  plListService.selectByWeiboId(weibo.getWeiboId());
            for (plList plL : plList) {
                List<hfplList> hfplLists = hfplListService.selectByPlId(plL.getId());
                for (hfplList hfpl : hfplLists) {
                    hfpl.setUserHeadImg(userService.findUser(hfpl.getUsername()).getHeadImgName());
                }
                plL.setHfplLists(hfplLists);
                plL.setUserHeadImg(userService.findUser(plL.getUsername()).getHeadImgName());
            }

            weibo.setPlLists(plList);
        }

        model.addAttribute("weibos", weibos);
        model.addAttribute("user",user);
        model.addAttribute("personalLabel",user.getpersonal_label());

        return "person";
    }

    @GetMapping("/psersonCollect")
    public String psersonCollect(HttpSession session, Model model) {

        // 当前用户信息
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryCollectWeibo(user);
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());

//        将数据库中的时间从date转为string ，并且安装 yyyy-MM-dd HH:mm:ss 格式 。
        for (weiboCustom weibo :weibos) {
            weibo.getUser().setPersonalLabel( weibo.getUser().getpersonal_label());
            Date postTime = weibo.getPostTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(postTime);
            weibo.setPostTimeAsString(timeFormat);

            if (weibo.getZfwbid()!=null){
                weiboCustom weibo1 = weiboService.queryWeiboByID(weibo.getZfwbid());
                zfweibo zfweibo =new zfweibo();
                zfweibo.setWeiboId(weibo1.getWeiboId());
                zfweibo.setContent(weibo1.getContent());
                zfweibo.setMusic(weibo1.getMusic());
                zfweibo.setPic1(weibo1.getPic1());
                zfweibo.setPic2(weibo1.getPic2());
                zfweibo.setPic3(weibo1.getPic3());
                zfweibo.setPic4(weibo1.getPic4());
                zfweibo.setPic5(weibo1.getPic5());
                zfweibo.setPic6(weibo1.getPic6());
                zfweibo.setVideo(weibo1.getVideo());
                zfweibo.setPostTime(weibo1.getPostTime());
                zfweibo.setUser(((weiboCustom) weibo1).getUser());
                weibo.setZfweibo(zfweibo);
            }

//            是否对这一条微博点赞：
            love.setWbid(weibo.getWeiboId());
            love love1 = likeService.ifLike(love);
            if(love1 == null){
                weibo.setIflike(false);
            }else{
                weibo.setIflike(true);
            }

//            是否对这一条微博收藏：
            collect.setWbid(weibo.getWeiboId());
            collect ifcollect = collectService.ifcollect(collect);
            if(ifcollect == null){
                weibo.setIfcollect(false);
            }else{
                weibo.setIfcollect(true);
            }

            List<plList> plList =  plListService.selectByWeiboId(weibo.getWeiboId());
            for (plList plL : plList) {
                List<hfplList> hfplLists = hfplListService.selectByPlId(plL.getId());
                for (hfplList hfpl : hfplLists) {
                    hfpl.setUserHeadImg(userService.findUser(hfpl.getUsername()).getHeadImgName());
                }
                plL.setHfplLists(hfplLists);
                plL.setUserHeadImg(userService.findUser(plL.getUsername()).getHeadImgName());
            }

            weibo.setPlLists(plList);
        }
        model.addAttribute("weibos", weibos);
        model.addAttribute("user",user);
        model.addAttribute("personalLabel",user.getpersonal_label());

        return "person_collect";
    }




    @GetMapping("/music")
    public String music(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByMusic();
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());


        //        将数据库中的时间从date转为string ，并且安装 yyyy-MM-dd HH:mm:ss 格式 。
        for (weiboCustom weibo :weibos) {
            weibo.getUser().setPersonalLabel( weibo.getUser().getpersonal_label());
            Date postTime = weibo.getPostTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(postTime);
            weibo.setPostTimeAsString(timeFormat);

            if (weibo.getZfwbid()!=null){
                weiboCustom weibo1 = weiboService.queryWeiboByID(weibo.getZfwbid());
                zfweibo zfweibo =new zfweibo();
                zfweibo.setWeiboId(weibo1.getWeiboId());
                zfweibo.setContent(weibo1.getContent());
                zfweibo.setMusic(weibo1.getMusic());
                zfweibo.setPic1(weibo1.getPic1());
                zfweibo.setPic2(weibo1.getPic2());
                zfweibo.setPic3(weibo1.getPic3());
                zfweibo.setPic4(weibo1.getPic4());
                zfweibo.setPic5(weibo1.getPic5());
                zfweibo.setPic6(weibo1.getPic6());
                zfweibo.setVideo(weibo1.getVideo());
                zfweibo.setPostTime(weibo1.getPostTime());
                zfweibo.setUser(((weiboCustom) weibo1).getUser());
                weibo.setZfweibo(zfweibo);
            }

//            是否对这一条微博点赞：
            love.setWbid(weibo.getWeiboId());
            love love1 = likeService.ifLike(love);
            if(love1 == null){
                weibo.setIflike(false);
            }else{
                weibo.setIflike(true);
            }

//            是否对这一条微博收藏：
            collect.setWbid(weibo.getWeiboId());
            collect ifcollect = collectService.ifcollect(collect);
            if(ifcollect == null){
                weibo.setIfcollect(false);
            }else{
                weibo.setIfcollect(true);
            }

            List<plList> plList =  plListService.selectByWeiboId(weibo.getWeiboId());
            for (plList plL : plList) {
                List<hfplList> hfplLists = hfplListService.selectByPlId(plL.getId());
                for (hfplList hfpl : hfplLists) {
                    hfpl.setUserHeadImg(userService.findUser(hfpl.getUsername()).getHeadImgName());
                }
                plL.setHfplLists(hfplLists);
                plL.setUserHeadImg(userService.findUser(plL.getUsername()).getHeadImgName());
            }

            weibo.setPlLists(plList);
        }

        model.addAttribute("weibos", weibos);

        return "music";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "/login/login";
//    }

    @GetMapping("/register")
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
