package weibo.Service;

import weibo.pojo.User;
import weibo.pojo.weibo;
import weibo.pojo.weiboCustom;

import java.util.List;

public interface WeiboService {
    // 发微博
    public void post(weibo weibo) throws Exception;

    //所有微博列表
    List<weiboCustom> queryAllWeibo();

//    所有带有图片的微博列表
    List<weiboCustom> queryAllWeiboByImg();

//    所有带有视频的微博列表
    List<weiboCustom> queryAllWeiboByVideo();

    //    所有带有音乐的微博列表
    List<weiboCustom> queryAllWeiboByMusic();

    //    所有带有文字的微博列表
    List<weiboCustom> queryAllWeiboByArticle();

//    个人微博列表
    List<weiboCustom> queryMyWeibo(User user);

//    个人收藏
    List<weiboCustom> queryCollectWeibo(User user);

//    更新点赞数
    void updateZanByPrimaryKey(weibo record);

    weiboCustom queryWeiboByID(String id);


}
