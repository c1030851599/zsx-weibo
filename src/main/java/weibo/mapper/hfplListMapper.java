package weibo.mapper;

import weibo.pojo.hfplList;

import java.util.List;

public interface hfplListMapper {
    int deleteByPrimaryKey(String id);

    void insert(hfplList record);

    int insertSelective(hfplList record);

    hfplList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(hfplList record);

    int updateByPrimaryKey(hfplList record);

    List<hfplList> selectByPlId(String plid);
}