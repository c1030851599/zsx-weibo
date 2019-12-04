package weibo.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class searchController {

    @PostMapping("/search")
    @ApiOperation(value = "查询模块，尚未开发！！！")
    public  String  search(String search){

        System.out.println(search);

        return "redirect:/queryAll";
    }
}
