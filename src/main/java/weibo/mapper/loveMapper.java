package weibo.mapper;

import weibo.pojo.love;

public interface loveMapper {
    int deleteByPrimaryKey(String dzid);

    int insert(love record);

    int insertSelective(love record);

    love selectByPrimaryKey(String dzid);

    int updateByPrimaryKeySelective(love record);

    int updateByPrimaryKey(love record);

    love ifLike(love record);

    void unlike(love record);

}