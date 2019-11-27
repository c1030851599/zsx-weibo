package weibo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/collect")
    public String collect(String state,String username,String weiboid){
        collect collect = new collect();

        User user = userService.findUser(username);

        collect.setCollectid(UUID.randomUUID().toString());
        collect.setUserid(user.getId());
        collect.setWbid(weiboid);
        collectService.insert(collect);

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
