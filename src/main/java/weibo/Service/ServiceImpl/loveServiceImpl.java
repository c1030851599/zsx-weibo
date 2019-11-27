package weibo.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weibo.Service.loveService;
import weibo.pojo.love;

@Service
public class loveServiceImpl implements loveService {

    @Autowired
    weibo.mapper.loveMapper loveMapper;

    @Override
    public void insert(love record) {
        loveMapper.insert(record);
    }

    @Override
    public love ifLike(love record) {
        return loveMapper.ifLike(record);
    }

    @Override
    public void unlike(love record) {
       loveMapper.unlike(record);
    }

}
