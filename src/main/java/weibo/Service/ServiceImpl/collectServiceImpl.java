package weibo.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weibo.Service.collectService;
import weibo.pojo.collect;

@Service
public class collectServiceImpl implements collectService {

    @Autowired
    weibo.mapper.collectMapper collectMapper;

    @Override
    public void insert(collect record) {
        collectMapper.insert(record);
    }

    @Override
    public void uncollect(collect record) {
        collectMapper.uncollect(record);
    }

    @Override
    public collect ifcollect(collect record) {
        return collectMapper.ifcollect(record);
    }


}
