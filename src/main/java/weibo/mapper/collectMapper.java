package weibo.mapper;
import weibo.pojo.collect;

public interface collectMapper {
    int deleteByPrimaryKey(String collectid);

    void insert(collect record);

    int insertSelective(collect record);

    collect selectByPrimaryKey(String collectid);

    int updateByPrimaryKeySelective(collect record);

    int updateByPrimaryKey(collect record);

    void uncollect(collect record);

    collect ifcollect(collect record);

}