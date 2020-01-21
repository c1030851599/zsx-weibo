package weibo.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weibo.Service.WeiboService;
import weibo.mapper.weiboMapper;
import weibo.pojo.User;
import weibo.pojo.weibo;
import weibo.pojo.weiboCustom;

import java.util.List;

@Service
public class WeiboServiceImpl implements WeiboService {

    @Autowired
    weiboMapper weiboMapper;

    @Override
    public void post(weibo weibo) throws Exception {
        int insert = weiboMapper.insert(weibo);
        System.out.println(insert);
    }

    /**
     * 查询所有微博
     */
    @Override
    public List<weiboCustom> queryAllWeibo() {
        List<weiboCustom> weibos = weiboMapper.queryAllWeibo();
        return weibos;
    }

    @Override
    public List<weiboCustom> queryAllWeiboByImg() {
        List<weiboCustom> weibos = weiboMapper.queryAllWeiboByImg();
        return weibos;
    }

    @Override
    public List<weiboCustom> queryAllWeiboByVideo() {
        List<weiboCustom> weibos = weiboMapper.queryAllWeiboByVideo();
        return weibos;
    }

    @Override
    public List<weiboCustom> queryAllWeiboByMusic() {
        List<weiboCustom> weibos = weiboMapper.queryAllWeiboByMusic();
        return weibos;
    }

    @Override
    public List<weiboCustom> queryAllWeiboByArticle() {
        List<weiboCustom> weibos = weiboMapper.queryAllWeiboByArticle();
        return weibos;
    }

    @Override
    public List<weiboCustom> queryMyWeibo(User user) {
        List<weiboCustom> weibos = weiboMapper.queryMyWeibo(user);
        return weibos;
    }

    @Override
    public List<weiboCustom> queryCollectWeibo(User user) {
        List<weiboCustom> weiboCustoms = weiboMapper.queryCollectWeibo(user);
        return weiboCustoms;
    }

    @Override
    public void updateZanByPrimaryKey(weibo record) {
        weiboMapper.updateZanByPrimaryKey(record);
    }

    @Override
    public weiboCustom queryWeiboByID(String id) {
        weiboCustom weibo = weiboMapper.queryWeiboByID(id);
        return weibo;
    }


    @Override
    public List<weiboCustom> queryByKeyWord(String content) {
        List<weiboCustom> weibos = weiboMapper.queryByKey(content);
        return weibos;
    }

    @Override
    public List<weiboCustom> queryAllWeiboByImgKey(String content) {
        List<weiboCustom> weibos = weiboMapper.queryAllWeiboByImgKey(content);
        return weibos;
    }

    @Override
    public List<weiboCustom> queryAllWeiboByVideoKey(String content) {
        List<weiboCustom> weibos = weiboMapper.queryAllWeiboByVideoKey(content);
        return weibos;
    }

    @Override
    public List<weiboCustom> queryAllWeiboByMusicKey(String content) {
        List<weiboCustom> weibos = weiboMapper.queryAllWeiboByMusicKey(content);
        return weibos;
    }

    @Override
    public List<weiboCustom> queryAllWeiboByArticleKey(String content) {
        List<weiboCustom> weibos = weiboMapper.queryAllWeiboByArticleKey(content);
        return weibos;
    }


}
