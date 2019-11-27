package weibo.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import weibo.Service.hfplListService;
import weibo.pojo.hfplList;

import javax.annotation.Resource;
import java.util.List;

@Service
public class hfplListServiceImpl implements hfplListService {

    @Resource
    weibo.mapper.hfplListMapper hfplListMapper;


    @Override
    public void insert(hfplList record) {
        hfplListMapper.insert(record);
    }

    @Override
    public List<hfplList> selectByPlId(String plid) {
        List<hfplList> hfplLists = hfplListMapper.selectByPlId(plid);

        return hfplLists;
    }


}
