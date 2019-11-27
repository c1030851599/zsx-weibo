package weibo.Service;

import weibo.pojo.plList;

import java.util.List;

public interface plListService {
    void insert(plList record);
    List<plList> selectByWeiboId(String weiboid);
}
