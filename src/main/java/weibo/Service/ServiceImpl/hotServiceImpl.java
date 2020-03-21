package weibo.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import weibo.Service.WeiboService;
import weibo.Service.hotService;
import weibo.pojo.hotWeibo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class hotServiceImpl implements hotService {

  @Autowired
  RedisTemplate<Object,String> redisTemplate;

  @Autowired
  WeiboService weiboService;

  @Override
  public List<hotWeibo> getHotWeibo() {
    List<hotWeibo> list = new ArrayList<>();
    Set<ZSetOperations.TypedTuple<String>> hot = redisTemplate.opsForZSet().reverseRangeWithScores("hot", 0, 15);
    Iterator<ZSetOperations.TypedTuple<String>> iterator = hot.iterator();
    while (iterator.hasNext()){
      hotWeibo hotWeibo = new hotWeibo();
      ZSetOperations.TypedTuple<String> typedTuple = iterator.next();
      String weiboId = typedTuple.getValue();
      double score1 = typedTuple.getScore();
      String content1 = weiboService.queryWeiboByID(weiboId).getContent();
      String content;
      if (content1.length() >= 10){
         content = content1.substring(0,10);
      } else {
        content = content1;
      }

      hotWeibo.setContent(content);
      hotWeibo.setScore((int) score1);
      hotWeibo.setWeiboId(weiboId);

      list.add(hotWeibo);
    }

    return list;
  }


}
