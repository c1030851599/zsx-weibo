package weibo.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import weibo.Service.UserService;
import weibo.Service.WeiboService;
import weibo.Service.chatService;
import weibo.Service.messageService;
import weibo.WebSocket.WebSocketServer;
import weibo.common.WeiboMethod;
import weibo.pojo.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

  @Autowired
  WeiboMethod method;
  @Autowired
  WeiboService weiboService;
  @Autowired
  messageService messageService;
  @Autowired
  chatService service;

  @Autowired
  UserService userService;
  @Resource
  WebSocketServer webSocketServer;

  @RequestMapping("/likeMeMessage")
  @ApiOperation(value = "给我点赞的人 的页面")
  public  String  likemeMessage(HttpSession session, Model model){
    User user = (User) session.getAttribute("user");
    List<likemessage> likemessages = messageService.selectlikeMessage(user.getUsername());
    List<message> list = new ArrayList<>();

    for (likemessage likemessage: likemessages) {
      message message = new message();
      message.setUser(userService.findUser(likemessage.getLikeusername()));
      Date postTime = likemessage.getDztime();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String timeFormat = sdf.format(postTime);
      message.setPostTime(timeFormat);

      message.setZfweibo(weiboService.queryweiboById(likemessage.getDzweibo()));
      list.add(message);
    }

//    同时将点赞的消息数量清0
    int likeCount = 0;
    int plCount = userService.getPlCount(user.getUsername());
    int zfCount = userService.getZfCount(user.getUsername());
    userService.updateZero(user.getUsername());
    //        通过websocket发送通知给本条微博者：
    webSocketServer.sendInfo(user.getUsername(), likeCount+","+plCount+","+zfCount);



    model.addAttribute("weibos", list);
    return "likeMe";
  }


  @RequestMapping("/plMeMessage")
  @ApiOperation(value = "给我评论的人 的页面")
  public  String  plmeMessage(HttpSession session, Model model){
    User user = (User) session.getAttribute("user");
    List<plmessage> plmessages = messageService.selectplMessage(user.getUsername());
    List<message> list = new ArrayList<>();

    for (plmessage plmessage: plmessages) {
      message message = new message();
      message.setUser(userService.findUser(plmessage.getPlusername()));
      Date postTime = plmessage.getPltime();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String timeFormat = sdf.format(postTime);
      message.setPostTime(timeFormat);
      message.setPlContent(plmessage.getPlcontent());
      message.setZfweibo(weiboService.queryweiboById(plmessage.getPlweibo()));
      list.add(message);
    }

//    同时将评论的消息数量清0
    int likeCount = userService.getLikeCount(user.getUsername());
    int plCount =0;
    int zfCount = userService.getZfCount(user.getUsername());
    userService.updatePlZero(user.getUsername());
    //        通过websocket发送通知给本条微博者：
    webSocketServer.sendInfo(user.getUsername(), likeCount+","+plCount+","+zfCount);


    model.addAttribute("weibos", list);
    return "plMe";
  }


  @RequestMapping("/zfMeMessage")
  @ApiOperation(value = "给我zf的人 的页面")
  public  String  zfmeMessage(HttpSession session, Model model, String content){
    User user = (User) session.getAttribute("user");
    List<zfmessage> zfmessages = messageService.selectzfMessage(user.getUsername());
    List<message> list = new ArrayList<>();

    for (zfmessage zfmessage: zfmessages) {
      message message = new message();
      message.setUser(userService.findUser(zfmessage.getZfusername()));
      Date postTime = zfmessage.getZftime();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String timeFormat = sdf.format(postTime);
      message.setPostTime(timeFormat);

      message.setZfweibo(weiboService.queryweiboById(zfmessage.getZfweibo()));
      list.add(message);
    }

//    同时将转发的消息数量清0
    int likeCount = userService.getLikeCount(user.getUsername());
    int plCount = userService.getPlCount(user.getUsername());
    int zfCount = 0;
    userService.updateZfZero(user.getUsername());
    //        通过websocket发送通知给本条微博者：
    webSocketServer.sendInfo(user.getUsername(), likeCount+","+plCount+","+zfCount);

    model.addAttribute("weibos", list);
    return "zfMe";
  }

  @RequestMapping("/chatMeMessage")
  @ApiOperation(value = "私信我的页面")
  public  String  chatMeMessage(HttpSession session, Model model, String content){

    User user = (User) session.getAttribute("user");
//    获取聊天对象列表
    List<chatlistVo> chatList = service.getChatList(user.getId());

    model.addAttribute("chatList",chatList);


//    同时将私聊的消息数量清0
    int likeCount = userService.getLikeCount(user.getUsername());
    int plCount = userService.getPlCount(user.getUsername());
    int zfCount = userService.getZfCount(user.getUsername());
    int chatMessage = 0;
    userService.updateChatZero(user.getUsername());
    //        通过websocket发送通知给本条微博者：
    webSocketServer.sendInfo(user.getUsername(), likeCount+","+plCount+","+zfCount+","+chatMessage);

    return "/wechatWithouYou";
  }





}
