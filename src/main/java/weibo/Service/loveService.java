package weibo.Service;

import weibo.pojo.love;

public interface loveService {
    void insert(love record);
    love ifLike(love record);
    void unlike(love record);
}
