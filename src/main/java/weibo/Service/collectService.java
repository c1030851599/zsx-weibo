package weibo.Service;

import weibo.pojo.collect;

public interface collectService {
    void insert(collect record);
    void uncollect(collect record);
    collect ifcollect(collect record);
}
