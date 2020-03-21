package weibo.mapper;

import weibo.pojo.chatrecord;

import java.util.List;

public interface chatrecordMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(chatrecord record);
//
//    int insertSelective(chatrecord record);
//
//    chatrecord selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(chatrecord record);
//
//    int updateByPrimaryKey(chatrecord record);

    List<chatrecord> getChatrecordList(String sessionId);
}