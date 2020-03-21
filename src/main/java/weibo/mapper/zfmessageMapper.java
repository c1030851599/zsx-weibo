package weibo.mapper;

import weibo.pojo.zfmessage;

import java.util.List;

public interface zfmessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(zfmessage record);

    int insertSelective(zfmessage record);

    zfmessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(zfmessage record);

    int updateByPrimaryKey(zfmessage record);

    List<zfmessage> selectAll(String username);
}