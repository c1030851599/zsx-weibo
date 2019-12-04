package weibo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weibo.Service.*;
import weibo.pojo.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class WeiboMethod {

  @Autowired
  WeiboService weiboService;

  @Autowired
  UserService userService;

  @Autowired
  plListService plListService;

  @Autowired
  hfplListService hfplListService;

  @Autowired
  loveService likeService;

  @Autowired
  collectService collectService;


  public List<weiboCustom> getWeibos(List<weiboCustom> weibos, collect collect, love love){
    //        将数据库中的时间从date转为string ，并且安装 yyyy-MM-dd HH:mm:ss 格式 。
    for (weiboCustom weibo :weibos) {
      weibo.getUser().setPersonalLabel( weibo.getUser().getpersonal_label());
      Date postTime = weibo.getPostTime();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String timeFormat = sdf.format(postTime);
      weibo.setPostTimeAsString(timeFormat);

      if (weibo.getZfwbid()!=null){
        weiboCustom weibo1 = weiboService.queryWeiboByID(weibo.getZfwbid());
        zfweibo zfweibo =new zfweibo();
        zfweibo.setWeiboId(weibo1.getWeiboId());
        zfweibo.setContent(weibo1.getContent());
        zfweibo.setMusic(weibo1.getMusic());
        zfweibo.setPic1(weibo1.getPic1());
        zfweibo.setPic2(weibo1.getPic2());
        zfweibo.setPic3(weibo1.getPic3());
        zfweibo.setPic4(weibo1.getPic4());
        zfweibo.setPic5(weibo1.getPic5());
        zfweibo.setPic6(weibo1.getPic6());
        zfweibo.setVideo(weibo1.getVideo());
        zfweibo.setPostTime(weibo1.getPostTime());
        zfweibo.setUser(((weiboCustom) weibo1).getUser());
        weibo.setZfweibo(zfweibo);
      }

//            是否对这一条微博点赞：
      love.setWbid(weibo.getWeiboId());
      love love1 = likeService.ifLike(love);

      if(love1 == null){
        weibo.setIflike(false);
      }else{
        weibo.setIflike(true);
      }

//            是否对这一条微博收藏：
      collect.setWbid(weibo.getWeiboId());
      collect ifcollect = collectService.ifcollect(collect);
      if(ifcollect == null){
        weibo.setIfcollect(false);
      }else{
        weibo.setIfcollect(true);
      }

      List<plList> plList =  plListService.selectByWeiboId(weibo.getWeiboId());
      for (plList plL : plList) {
        List<hfplList> hfplLists = hfplListService.selectByPlId(plL.getId());
        for (hfplList hfpl : hfplLists) {
          hfpl.setUserHeadImg(userService.findUser(hfpl.getUsername()).getHeadImgName());
        }
        plL.setHfplLists(hfplLists);
        plL.setUserHeadImg(userService.findUser(plL.getUsername()).getHeadImgName());
      }

      weibo.setPlLists(plList);
    }
    return weibos;
  }

}
