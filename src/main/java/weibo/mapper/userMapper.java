package weibo.mapper;

import weibo.pojo.User;
public interface userMapper {
    int insert(User record);

    int insertSelective(User record);

    User login(User user);

    User findUser(String username);

    void updateHeadImg(User user);

    void updateInfo(User user);

}