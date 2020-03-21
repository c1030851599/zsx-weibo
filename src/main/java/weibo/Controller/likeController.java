package weibo.Controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo.Service.UserService;
import weibo.Service.WeiboService;
import weibo.Service.loveService;
import weibo.Service.messageService;
import weibo.WebSocket.WebSocketServer;
import weibo.pojo.User;
import weibo.pojo.likemessage;
import weibo.pojo.love;
import weibo.pojo.weibo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@RestController
public class likeController {

    @Autowired
    UserService userService;
    @Autowired
    loveService likeService;
    @Autowired
    WeiboService weiboService;
    @Resource
    WebSocketServer webSocketServer;
    @Autowired
    messageService messageService;
    @Autowired
    RedisTemplate<Object,String> redisTemplate;

    @GetMapping("/like")
    @ApiOperation(value = "点赞，给某条博客点赞")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "zCount", value = "点赞数", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(name = "weiboid", value = "微博的id", required = true, dataType = "String",paramType = "query")
    })
    public String like(String zCount, String username, String weiboid){

        System.out.println(zCount + " "+username + " " + weiboid );

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

//消息通知：---------------------------------------------------------------------------------
//        本条微博作者的用户名
        String userName = weiboService.getUsernameByWeiboID(weiboid);
//        获取对该条微博点赞的实时总数量（当用户点击消息提示时清0）
      int likeCount = userService.getLikeCount(userName);
      int plCount = userService.getPlCount(userName);
      int zfCount = userService.getZfCount(userName);
//      点赞后点赞通知数量+1 并保存到数据库
      likeCount++;
      userService.updateLikeCount(userName);
      //        通过websocket发送通知给本条微博者：
      webSocketServer.sendInfo(userName, likeCount+","+plCount+","+zfCount);

//     添加一条点赞通知
        likemessage message = new likemessage();
        message.setDzweibo(weiboid);
        message.setLikeusername(username);
        message.setLikedusername(userName);
        message.setDztime(new Date());
        messageService.insetLikeMessage(message);

//   热点微博：——————————————————————————————————
//      获取分数值：  (不能存微博对象，因为存进redis时同一条微博就为两个不同的对象了（new 时为不同对象了），这样无法对同一条微博 作)
      Double score = redisTemplate.opsForZSet().score("hot", weiboid);
//      将这条微博加入到redis
      if (score == null || score == 0 ){
        redisTemplate.opsForZSet().add("hot",weiboid,1);
      } else {
        redisTemplate.opsForZSet().add("hot",weiboid,score+1);
      }
        return "";
    }


    @GetMapping("/unlike")
    @ApiOperation(value = "取消点赞，取消给某条博客点赞")
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
