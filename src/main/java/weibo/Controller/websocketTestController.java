package weibo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weibo.WebSocket.WebSocketServer;

import javax.annotation.Resource;
import java.io.IOException;
@RestController
public class websocketTestController {

  @Resource
  private WebSocketServer webSocketServer;

  /**
   * 给指定用户推送消息
   * @param userName 用户名
   * @param message 消息
   * @throws IOException
   */
  @RequestMapping(value = "/socket", method = RequestMethod.GET)
  public void testSocket1(@RequestParam String userName, @RequestParam String message){
    webSocketServer.sendInfo(userName, message);
  }


  /**
   * 给所有用户推送消息
   * @param message 消息
   * @throws IOException
   */
  @RequestMapping(value = "/socket/all", method = RequestMethod.GET)
  public void testSocket2(@RequestParam String message){
    try {
      webSocketServer.onMessage(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
