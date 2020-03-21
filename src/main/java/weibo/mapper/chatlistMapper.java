package weibo.mapper;

import org.apache.ibatis.annotations.Param;
import weibo.pojo.chatlist;
import weibo.pojo.chatrecord;

import java.util.List;

public interface chatlistMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(chatlist record);
//
//    int insertSelective(chatlist record);
//
//    chatlist selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(chatlist record);
//
//    int updateByPrimaryKey(chatlist record);

    List<chatlist> selectChatList(Integer id);

    void insertOne(chatlist list);

    chatlist ifHave(chatlist chatlist);

    void insertOneRecord(chatrecord chatrecord);

    chatlist ifTalk(@Param("me") Integer me,@Param("you") Integer you);

    void updateTime(chatlist chat);

    Integer getSxMessage(@Param("me") Integer me,@Param("sessionid")String sessionid);

    void updateSxMessage(@Param("me") Integer me,@Param("sessionid")String sessionid);

    void updateSxMessageToZero(@Param("me") Integer me,@Param("sessionid")String sessionid);
}