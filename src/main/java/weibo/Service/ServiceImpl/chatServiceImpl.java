package weibo.Service.ServiceImpl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weibo.Service.UserService;
import weibo.Service.chatService;
import weibo.mapper.chatlistMapper;
import weibo.mapper.chatrecordMapper;
import weibo.pojo.chatlist;
import weibo.pojo.chatlistVo;
import weibo.pojo.chatrecord;
import weibo.pojo.chatrecordVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class chatServiceImpl implements chatService {

  @Autowired
  chatlistMapper mapper;
  @Autowired
  chatrecordMapper chatrecordMapper;
  @Autowired
  UserService userService;

  @Override
  public List<chatlistVo> getChatList(Integer id) {
    List<chatlist> chatlists = mapper.selectChatList(id);
    List<chatlistVo> voList = new ArrayList<>();

    for (chatlist list:chatlists) {
      chatlistVo vo = new chatlistVo();

      String sessionid = list.getSessionid();
      List<chatrecordVo> chatRecord = getChatRecord(sessionid);
      //  获取最新的那条消息
      if(chatRecord.size() != 0 && chatRecord != null){
        chatrecordVo chatrecordVo = chatRecord.get(chatRecord.size() - 1);
        vo.setNewestMsg(chatrecordVo.getChatrecord().getContant());
      }else{
        vo.setNewestMsg("");
      }

//     获取me：
      vo.setMe(userService.findUserById(list.getMe()));
      //     获取you：
      vo.setYou(userService.findUserById(list.getYou()));
      vo.setList(list);
      voList.add(vo);
    }
    return voList;

  }


  @Override
  public List<chatrecordVo> getChatRecord(String chatId) {

    List<chatrecord> chatrecordList = chatrecordMapper.getChatrecordList(chatId);

    List<chatrecordVo> voList = new ArrayList<>();

    for (chatrecord cord:chatrecordList) {
      chatrecordVo vo = new chatrecordVo();
//     获取me：
      vo.setMe(userService.findUserById(cord.getSendman()));
      //     获取you：
      vo.setYou(userService.findUserById(cord.getReceiveman()));
      vo.setChatrecord(cord);
      voList.add(vo);
    }
    return voList;
  }

  @Override
  public chatlist selectChatList(Integer me,Integer you) {
    return mapper.ifTalk(you,me);
  }

  @Override
  public void saveNew(chatlist list) {
    mapper.insertOne(list);
  }

  @Override
  public Boolean ifHava(chatlist list) {
    chatlist chatlist = mapper.ifHave(list);
    if (chatlist == null){
      return false;
    }else {
      return true;
    }

  }

  @Override
  public void saveNewMessage(chatrecord record) {
    mapper.insertOneRecord(record);
  }

  @Override
  public chatlist ifTalk(Integer me, Integer you) {
   return mapper.ifTalk(me,you);
  }

  @Override
  public void updateTime(chatlist chat) {
    mapper.updateTime(chat);
  }

  @Override
  public Integer getSxCount(Integer you, String sessionid) {
    Integer sxMessage = mapper.getSxMessage(you, sessionid);
    if (sxMessage == null){
      return 0;
    }
    return sxMessage;
  }

  @Override
  public void updateSxCount(Integer you, String sessionid) {
    mapper.updateSxMessage(you, sessionid);
  }

  @Override
  public void updateSxCountToZero(Integer you, String sessionid) {
    mapper.updateSxMessageToZero(you, sessionid);
  }

}
