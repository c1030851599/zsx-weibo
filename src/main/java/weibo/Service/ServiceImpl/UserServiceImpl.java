package weibo.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weibo.Service.UserService;
import weibo.mapper.gzMapper;
import weibo.mapper.userMapper;
import weibo.pojo.User;
import weibo.pojo.gz;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    gzMapper gzMapper;

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
    public void register(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public User findUser(String username) {
        return userMapper.findUser(username);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void updateHeadImg(User user) {
        userMapper.updateHeadImg(user);
    }

    @Override
    public void updateInfo(User user) {
        userMapper.updateInfo(user);
    }

    @Override
    public Integer getPlCount(String Username) {
        return userMapper.selectPLCount(Username);
    }

    @Override
    public Integer getLikeCount(String Username) {
        return userMapper.selectLikeCount(Username);
    }

    @Override
    public Integer getZfCount(String Username) {
        return userMapper.selectZFCount(Username);
    }

    @Override
    public void updateLikeCount(String userName) {
         userMapper.updateLikeCount(userName);
    }

    @Override
    public void updateZero(String userName) {
        userMapper.setZero(userName);
    }

    @Override
    public void updatePlCount(String userName) {
         userMapper.updatePLCount(userName);
    }

    @Override
    public void updatePlZero(String userName) {
        userMapper.setplZero(userName);
    }

    @Override
    public void updateZfCount(String userName) {
         userMapper.updateZFCount(userName);
    }

    @Override
    public void updateZfZero(String userName) {
        userMapper.setzfZero(userName);
    }

    @Override
    public boolean ifGZ(gz gz) {
        gz result = gzMapper.ifGZ(gz);
        if (result == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void saveGz(gz gz) {
        gzMapper.insert(gz);
    }

    @Override
    public void cancelGz(gz gz) {
        gzMapper.deleteGz(gz);
    }

    @Override
    public List<User> searchperson(String content) {
        return userMapper.findPerson(content);
    }


}
