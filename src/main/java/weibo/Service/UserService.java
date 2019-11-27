package weibo.Service;

import weibo.pojo.User;

public interface UserService {
    boolean login(User user);
    User findUser(String username);
    void updateHeadImg(User user);
    void updateInfo(User user);
}
