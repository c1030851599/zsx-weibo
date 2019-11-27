package weibo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class searchController {

    @PostMapping("/search")
    public  String  search(String search){

        System.out.println(search);

        return "redirect:/queryAll";
    }
}
