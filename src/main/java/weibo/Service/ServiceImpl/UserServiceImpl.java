package weibo.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weibo.Service.UserService;
import weibo.mapper.userMapper;
import weibo.pojo.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    userMapper userMapper;

    @Override
    public boolean login(User user) {
        boolean flag;
        User login = userMapper.login(user);
        if (login!=null){
            flag = true;
        }
        else {
            flag = false;
        }
        return flag;
    }

    @Override
    public User findUser(String username) {
        return userMapper.findUser(username);
    }

    @Override
    public void updateHeadImg(User user) {
        userMapper.updateHeadImg(user);
    }

    @Override
    public void updateInfo(User user) {
        userMapper.updateInfo(user);
    }


}
