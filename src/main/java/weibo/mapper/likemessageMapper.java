package weibo.mapper;

import weibo.pojo.likemessage;

import java.util.List;

public interface likemessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(likemessage record);

    int insertSelective(likemessage record);

    likemessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(likemessage record);

    int updateByPrimaryKey(likemessage record);

    List<likemessage> selectAll(String username);


}