package weibo.Service;

import weibo.pojo.User;

public interface UserService {
    boolean login(User user);
    User findUser(String username);
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

}
