package weibo.Service;

import weibo.pojo.chatlist;
import weibo.pojo.chatlistVo;
import weibo.pojo.chatrecord;
import weibo.pojo.chatrecordVo;

import java.util.List;

public interface chatService {

  /**
   * 获取我的聊天列表
   */
  List<chatlistVo> getChatList(Integer id);

  /**
   * 获取聊天记录
   */
  List<chatrecordVo> getChatRecord(String chatId);


  chatlist selectChatList(Integer me,Integer you);


  /**
   * 新增聊天列表
   */
  void saveNew(chatlist list);

  /**
   * 查询是否已经存在这个聊天对象
   */

  Boolean ifHava(chatlist list);

  /**
   * 新增一条消息（同一条消息记录）
   */
  void saveNewMessage(chatrecord record);

  /**
   * 查找 我要谈的人是否与 我 建立了会话连接
   */
  chatlist ifTalk(Integer me,Integer you);

  void updateTime(chatlist chat);

  /**
   * 获取收到的的私信消息数
   */

  Integer getSxCount(Integer you,String sessionid);

  void updateSxCount(Integer you,String sessionid);

  void updateSxCountToZero(Integer you,String sessionid);


}
