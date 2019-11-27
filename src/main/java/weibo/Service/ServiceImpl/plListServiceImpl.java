package weibo.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import weibo.Service.plListService;
import weibo.pojo.plList;

import javax.annotation.Resource;
import java.util.List;

@Service
public class plListServiceImpl implements plListService {

    @Resource
    weibo.mapper.plListMapper plListMapper;

    @Override
    public void insert(plList record) {
        plListMapper.insert(record);

    }

    @Override
    public List<plList> selectByWeiboId(String weiboid) {
        List<plList> plList = (List<weibo.pojo.plList>) plListMapper.selectByWeiboId(weiboid);
        return plList;
    }
}
