package weibo.Service;

import weibo.pojo.hfplList;

import java.util.List;

public interface hfplListService {
    void insert(hfplList record);
    List<hfplList> selectByPlId(String plid);
}
