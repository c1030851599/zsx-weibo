package weibo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo.pojo.huifu;

@RestController
public class huifuController {

    @RequestMapping("json/pl")
    public huifu huifu(){

        huifu huifu = new huifu();
        huifu.setAtName("李鸡鸡");
        huifu.setHfContent("嘿嘿嘿，成功了？？？");
        return huifu;
    }


}
