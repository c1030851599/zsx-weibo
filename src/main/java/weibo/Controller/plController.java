package weibo.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo.Service.*;
import weibo.WebSocket.WebSocketServer;
import weibo.pojo.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class plController {

    @Autowired
    UserService userService;

    @Autowired
    plListService plListService;

    @Autowired
    WeiboService weiboService;

    @Autowired
    hfplListService hfplListService;

    @Resource
    private WebSocketServer webSocketServer;

    @Autowired
    messageService messageService;

    @Autowired
    RedisTemplate<Object, String> redisTemplate;

    @GetMapping("/pl")
    @ApiOperation(value = "评论")
    public String pl(String plContent,String time,String username,String weiboid) throws ParseException {

        weibo weibo = new weibo();
        weibo .setWeiboId(weiboid);
        weibo weibo1 = weiboService.queryWeiboByID(weiboid);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User user = userService.findUser(username);

        plList plList = new plList();
        plList.setId(UUID.randomUUID().toString());
        plList.setUserid(user.getId());
        plList.setUsername(username);
        plList.setPlcontent(plContent);
        Date date = sdf.parse(time);
        plList.setPltime(date);
        plList.setWeiboid(weiboid);
        plListService.insert(plList);

        //        通过websocket发送通知给本条微博者：
//        本条微博作者的用户名
        String userName = weiboService.getUsernameByWeiboID(weiboid);
//        获取对该条微博点赞的实时总数量（当用户点击消息提示时清0）
        int likeCount = userService.getLikeCount(userName);
        int plCount = userService.getPlCount(userName);
        int zfCount = userService.getZfCount(userName);
        int chatMessage = userService.getChatMessage(username);
//      评论后评论通知数量+1
        plCount++;
        userService.updatePlCount(userName);
        webSocketServer.sendInfo(userName, likeCount+","+plCount+","+zfCount+","+ chatMessage );

        //     添加一条评论通知
        plmessage message = new plmessage();
        message.setPlweibo(weiboid);
        message.setPlusername(username);
        message.setPledusername(userName);
        message.setPlcontent(plContent);
        message.setPltime(new Date());
        messageService.insetplMessage(message);

        //   热点微博：——————————————————————————————————
//      获取分数值：
        Double score = redisTemplate.opsForZSet().score("hot", weiboid);

//      将这条微博加入到redis
        if (score == null || score == 0 ){
            redisTemplate.opsForZSet().add("hot",weiboid,1);
        } else {
            redisTemplate.opsForZSet().add("hot",weiboid,score+1);
        }
        return "";
    }

    @GetMapping("/hfpl")
    @ApiOperation(value = "回复评论")
    public String hfpl(String hfContent,String time,String username,String plid) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User user = userService.findUser(username);

        hfplList hfplList = new hfplList();
        hfplList.setId(UUID.randomUUID().toString());
        hfplList.setPlzid(user.getId());
        hfplList.setUsername(username);
        hfplList.setHfcontent(hfContent);
        Date date = sdf.parse(time);
        hfplList.setHfpltime(date);
        hfplList.setPlid(plid);
        hfplListService.insert(hfplList);

        return "";
    }





}
