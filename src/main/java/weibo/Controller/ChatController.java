package weibo.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import weibo.Service.UserService;
import weibo.Service.WeiboService;
import weibo.Service.chatService;
import weibo.Service.messageService;
import weibo.WebSocket.WebSocketServer;
import weibo.WebSocket.WebSocketWeChatServer;
import weibo.pojo.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ChatController {

  @Autowired
  chatService service;
  @Autowired
  UserService userService;
  @Autowired
  WebSocketWeChatServer weChatServer;
  @Autowired
  messageService messageService;
  @Resource
  WebSocketServer webSocketServer;
  @Autowired
  WeiboService weiboService;

  @GetMapping("/weChat")
  @ApiOperation(value = "跳转到聊天页面")
  public String weChat(HttpSession session, Model model,@RequestParam("youusername") String youusername) {
    User user = (User) session.getAttribute("user");

    //    获取最新（正在）聊天对象信息
    User you = userService.findUser(youusername);

//    获取聊天对象列表
    List<chatlistVo> chatList = service.getChatList(user.getId());

    chatlist chatlist = service.selectChatList(user.getId(),you.getId());

    //    获取最新（正在）聊天的记录
    String sessionid = chatlist.getSessionid();
    List<chatrecordVo> chatRecord = service.getChatRecord(sessionid);

    model.addAttribute("chatLog", chatRecord);
    model.addAttribute("chatList",chatList);
    model.addAttribute("you",you);
    model.addAttribute("sessionid",sessionid);


    return "/wechat";
  }


  @GetMapping("/talk/{username}")
  @ApiOperation(value = "点击私聊")   //使用Model来存放参数，在重定向时该参数也会被带过去
  public String talk(HttpSession session, Model model, @PathVariable("username") String username, RedirectAttributes ra) {
    User me = (User) session.getAttribute("user");
    User you = userService.findUser(username);

//    新增一条会话列表人
    chatlist chatlist = new chatlist();
    chatlist.setMe(me.getId());
    chatlist.setYou(you.getId());
    chatlist.setLasttimetalk(new Date());
    chatlist.setSxMessage(0);
    chatlist.setSessionid(UUID.randomUUID().toString());

//    点击该消息对象时，清空消息数量
   chatlist chatlist1 = service.selectChatList(me.getId(), you.getId());
    if (chatlist1 != null) {
      service.updateSxCountToZero(you.getId(), chatlist1.getSessionid());
    }

    if(!service.ifHava(chatlist)){
      service.saveNew(chatlist);
//    这里注意：这是强关联，一旦A与B建立会话，双方要同时将对方加入会话列表，因为你找别人聊天，别人一定已和你建立连接
      chatlist.setYou(me.getId());
      chatlist.setMe(you.getId());
      service.saveNew(chatlist);
    }

    ra.addAttribute("youusername",username);
    return "redirect:/weChat";
  }


  @GetMapping("/talk")
  @ResponseBody()
  @ApiOperation(value = "发送消息")
  public String talkTo(HttpSession session,String username,String messages,String sessionid) {
    User me = (User) session.getAttribute("user");
    User you = userService.findUser(username);
    chatrecord chatlog = new chatrecord();
    chatlog.setContant(messages);
    chatlog.setSendtime(new Date());
    chatlog.setSessionid(sessionid);
    chatlog.setSendman(me.getId());
    chatlog.setReceiveman(you.getId());
    service.saveNewMessage(chatlog);

    chatlist chat = new chatlist();
    chat.setSessionid(sessionid);
    chat.setLasttimetalk(new Date());
    service.updateTime(chat);

//    私信消息通知
    int sxCount = service.getSxCount(me.getId(),sessionid);
    sxCount++;
    service.updateSxCount(me.getId(),sessionid);

//    通过websocket发送消息给对方
    weChatServer.sendInfo(you.getUsername(), me.getUsername()+"|"+messages+"|"+ sxCount);

//私聊消息通知：---------------------------------------------------------------------------------
//        获取对该条微博点赞的实时总数量（当用户点击消息提示时清0）
    int likeCount = userService.getLikeCount(username);
    int plCount = userService.getPlCount(username);
    int zfCount = userService.getZfCount(username);
    int chatMessage = userService.getChatMessage(username);
//      私聊后 私聊通知数量+1 并保存到数据库
    chatMessage++;
    userService.updateChatCount(username);
    //        通过websocket发送通知给本条微博者：
    webSocketServer.sendInfo(username, likeCount+","+plCount+","+zfCount + "," + chatMessage );

    return "";

  }


}
