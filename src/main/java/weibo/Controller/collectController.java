package weibo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo.Service.UserService;
import weibo.Service.WeiboService;
import weibo.Service.collectService;
import weibo.pojo.User;
import weibo.pojo.collect;

import java.util.UUID;

@RestController
public class collectController {

    @Autowired
    UserService userService;

    @Autowired
    WeiboService weiboService;

    @Autowired
    collectService collectService;

    @Autowired
    RedisTemplate<Object,String> redisTemplate;

    @GetMapping("/collect")
    public String collect(String state,String username,String weiboid){
        collect collect = new collect();

        User user = userService.findUser(username);

        collect.setCollectid(UUID.randomUUID().toString());
        collect.setUserid(user.getId());
        collect.setWbid(weiboid);
        collectService.insert(collect);

//   热点微博：——————————————————————————————————
//      获取分数值：  (不能存微博对象，因为存进redis时同一条微博就为两个不同的对象了（new 时为不同对象了），这样无法对同一条微博 作)
        Double score = redisTemplate.opsForZSet().score("hot", weiboid);
//      将这条微博加入到redis
        if (score == null || score == 0 ){
            redisTemplate.opsForZSet().add("hot",weiboid,1);
        } else {
            redisTemplate.opsForZSet().add("hot",weiboid,score+1);
        }

        return "";
    }

    @GetMapping("/uncollect")
    public String uncollect(String state,String username,String weiboid){

        collect collect = new collect();

        User user = userService.findUser(username);

        collect.setCollectid(UUID.randomUUID().toString());
        collect.setUserid(user.getId());
        collect.setWbid(weiboid);
        collectService.uncollect(collect);

        return "";
    }




}
