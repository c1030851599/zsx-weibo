package weibo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo.Service.UserService;
import weibo.Service.WeiboService;
import weibo.Service.loveService;
import weibo.pojo.User;
import weibo.pojo.love;
import weibo.pojo.weibo;

import java.util.UUID;

@RestController
public class likeController {

    @Autowired
    UserService userService;

    @Autowired
    loveService likeService;

    @Autowired
    WeiboService weiboService;

    @GetMapping("/like")
    public String like(String zCount,String username,String weiboid){

        love love = new love();

        User user = userService.findUser(username);


        love.setDzid(UUID.randomUUID().toString());
        love.setUserid(user.getId());
        love.setWbid(weiboid);
        likeService.insert(love);

        weibo weibo = new weibo();
        weibo .setWeiboId(weiboid);
        weibo.setZan(Integer.parseInt(zCount));
        weibo weibo1 = weiboService.queryWeiboByID(weiboid);
        weibo.setPostTime(weibo1.getPostTime());

        weiboService.updateZanByPrimaryKey(weibo);
        return "";
    }

    @GetMapping("/unlike")
    public String unlike(String zCount,String username,String weiboid){

        love love = new love();

        User user = userService.findUser(username);


        love.setDzid(UUID.randomUUID().toString());
        love.setUserid(user.getId());
        love.setWbid(weiboid);
        likeService.unlike(love);

        weibo weibo = new weibo();
        weibo .setWeiboId(weiboid);
        weibo.setZan(Integer.parseInt(zCount));
        weibo weibo1 = weiboService.queryWeiboByID(weiboid);
        weibo.setPostTime(weibo1.getPostTime());

        weiboService.updateZanByPrimaryKey(weibo);

        return "";
    }



}