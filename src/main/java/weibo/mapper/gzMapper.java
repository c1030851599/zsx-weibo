package weibo.mapper;

import weibo.pojo.gz;

public interface gzMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(gz record);

    int insertSelective(gz record);

    gz selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(gz record);

    int updateByPrimaryKey(gz record);

    gz ifGZ(gz record);

    void deleteGz(gz record);
}