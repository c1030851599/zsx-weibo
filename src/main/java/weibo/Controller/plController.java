package weibo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo.Service.UserService;
import weibo.pojo.User;
import weibo.pojo.hfplList;
import weibo.pojo.plList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class plController {

    @Autowired
    UserService userService;

    @Autowired
    weibo.Service.plListService plListService;

    @Autowired
    weibo.Service.hfplListService hfplListService;


    @GetMapping("/pl")
    public String pl(String plContent,String time,String username,String weiboid) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User user = userService.findUser(username);

        plList plList = new plList();
        plList.setId(UUID.randomUUID().toString());
        plList.setUserid(user.getId());
        plList.setUsername(username);
        plList.setPlcontent(plContent);
        Date date = sdf.parse(time);
        plList.setPltime(date);
        plList.setWeiboid(weiboid);

        plListService.insert(plList);
        return "";
    }

    @GetMapping("/hfpl")
    public String hfpl(String hfContent,String time,String username,String plid) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User user = userService.findUser(username);

        hfplList hfplList = new hfplList();
        hfplList.setId(UUID.randomUUID().toString());
        hfplList.setPlzid(user.getId());
        hfplList.setUsername(username);
        hfplList.setHfcontent(hfContent);
        Date date = sdf.parse(time);
        hfplList.setHfpltime(date);
        hfplList.setPlid(plid);
        hfplListService.insert(hfplList);

        return "";
    }





}
