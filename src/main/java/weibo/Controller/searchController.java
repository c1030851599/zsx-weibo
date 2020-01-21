package weibo.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import weibo.Service.WeiboService;
import weibo.common.WeiboMethod;
import weibo.pojo.User;
import weibo.pojo.collect;
import weibo.pojo.love;
import weibo.pojo.weiboCustom;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class searchController {
    @Autowired
    WeiboMethod method;

    @Autowired
    WeiboService weiboService;

    @RequestMapping("/search")
    @ApiOperation(value = "查询模块，综合")
    public  String  search(HttpSession session, Model model, String content){
        // 当前用户信息
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryByKeyWord(content);
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());

        List<weiboCustom> weibos1 = method.getWeibos(weibos,collect,love);

        model.addAttribute("weibos", weibos1);
        model.addAttribute("personalLabel",user.getpersonal_label());
        model.addAttribute("searchContent", content);
        return "searchPage";
    }

    @GetMapping("/searchByArticle")
    @ApiOperation(value = "跳转到搜索文章页面")
    public String one(HttpSession session,Model model, String content) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByArticleKey(content);
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());

        List<weiboCustom> weibos1 = method.getWeibos(weibos,collect,love);

        model.addAttribute("weibos", weibos1);
        model.addAttribute("searchContent", content);
        return "searchArticlePage";
    }

    @GetMapping("/searchByPicture")
    @ApiOperation(value = "跳转到搜索图片区")
    public String two(HttpSession session,Model model, String content) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByImgKey(content);
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());


        List<weiboCustom> weibos1 = method.getWeibos(weibos,collect,love);

        model.addAttribute("weibos", weibos1);
        model.addAttribute("searchContent", content);
        return "searchPicturePage";
    }

    @GetMapping("/searchByVideo")
    @ApiOperation(value = "跳转到搜索视频区")
    public String three(HttpSession session,Model model, String content) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByVideoKey(content);
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());

        List<weiboCustom> weibos1 = method.getWeibos(weibos, collect, love);

        model.addAttribute("weibos", weibos1);
        model.addAttribute("searchContent", content);

        return "searchVideoPage";
    }

    @GetMapping("/searchByMusic")
    @ApiOperation(value = "跳转到搜索音乐区")
    public String music(HttpSession session,Model model, String content) {
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeiboByMusicKey(content);
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());


        List<weiboCustom> weibos1 = method.getWeibos(weibos, collect, love);

        model.addAttribute("weibos", weibos1);
        model.addAttribute("searchContent", content);
        return "searchMusicPage";
    }





}
