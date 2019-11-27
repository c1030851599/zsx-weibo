package weibo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import weibo.Service.UserService;
import weibo.pojo.User;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/updateInfo")
    public String updateInfo(@RequestParam("Stringbirthday")String Stringbirthday , User user, HttpSession session, Model model) throws ParseException {
        User User = (User) session.getAttribute("user");
        User.setpersonal_label(user.getpersonal_label());
        User.setWebName(user.getWebName());
        User.setName(user.getName());
        User.setCity(user.getCity());
        User.setGender(user.getGender());

        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        Date date =  formatter.parse(Stringbirthday);

        User.setBirthday(date);

        userService.updateInfo(User);

        model.addAttribute("user",User);
        session.setAttribute("user",User);
        model.addAttribute("personalLabel",User.getpersonal_label());

        return "updatePersonInfo";


    }


}
