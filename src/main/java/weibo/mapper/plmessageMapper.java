package weibo.mapper;

import weibo.pojo.plmessage;

import java.util.List;

public interface plmessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(plmessage record);

    int insertSelective(plmessage record);

    plmessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(plmessage record);

    int updateByPrimaryKey(plmessage record);

    List<plmessage> selectAll(String username);
}