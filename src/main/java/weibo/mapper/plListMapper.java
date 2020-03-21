package weibo.mapper;

import weibo.pojo.plList;

import java.util.List;

public interface plListMapper {
    int deleteByPrimaryKey(String id);

    int insert(plList record);

    int insertSelective(plList record);

    plList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(plList record);

    int updateByPrimaryKey(plList record);

    List<plList> selectByWeiboId(String weiboid);

    String selectWeiboIdByPlId(String pl);

}