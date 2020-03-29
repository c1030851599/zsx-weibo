package weibo.Service;

import weibo.pojo.User;
import weibo.pojo.gz;

import java.util.List;

public interface UserService {
    boolean login(User user);

    void register(User user);


    User findUser(String username);
    User findUserById(Integer id);

    void updateHeadImg(User user);
    void updateInfo(User user);
    Integer getPlCount(String Username);
    Integer getLikeCount(String Username);
    Integer getZfCount(String Username);
    void updateLikeCount(String userName);
    void updateZero(String userName);

    void updatePlCount(String userName);
    void updatePlZero(String userName);

    void updateZfCount(String userName);
    void updateZfZero(String userName);

//    是否关注
    boolean ifGZ(gz gz);

//    新增一条关注记录：
    void saveGz(gz gz);

//    取消关注
    void cancelGz(gz gz);

//    查找用户
    List<User> searchperson(String content);


}
