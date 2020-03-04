package weibo.mapper;

import weibo.pojo.User;
public interface userMapper {
    int insert(User record);

    int insertSelective(User record);

    User login(User user);

    User findUser(String username);

    void updateHeadImg(User user);

    void updateInfo(User user);

    String selectUserNameByID(Integer id);

    Integer selectLikeCount(String username);

    Integer selectPLCount(String username);

    Integer selectZFCount(String username);

    void updateLikeCount(String username);

    void setZero(String username);

    void updatePLCount(String username);

    void setplZero(String username);

    void updateZFCount(String username);

    void setzfZero(String username);

}