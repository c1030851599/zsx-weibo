package weibo.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import weibo.Service.*;
import weibo.common.WeiboMethod;
import weibo.pojo.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class WeiboController {

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

    @Autowired
    WeiboMethod method;


    @PostMapping(value = "/postWB")
    @ApiOperation(value = "发送微博")
    public String upload(@RequestParam("images[]") MultipartFile[] images,@RequestParam("videoFile") MultipartFile videoFile,@RequestParam("musicFile") MultipartFile musicFile, HttpSession session, weibo weibo) throws Exception {
//        接收图片！！！！！！！！！！
        String[] a = new String[6];
        boolean flag = images[0].getOriginalFilename().equals("");
        if (images != null && images.length > 0 && !flag) {
                for (int i = 0; i < images.length; i++) {
                    String Filename =  images[i].getOriginalFilename();
                    a[i] = Filename;
                    System.out.println("上传的文件名为：" + Filename);
                    // 获取文件的后缀名
                    String suffixName = Filename.substring(Filename.lastIndexOf("."));
                    System.out.println("文件的后缀名为：" + suffixName);
                    // 设置文件存储路径
                    String filePath = "E:/lidea编程项目/毕业设计项目（微博）/src/main/resources/static/imgUpload/";
                    String path = filePath + Filename;
                    File dest = new File(path);
                    // 检测是否存在目录
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();// 新建文件夹
                    }
                    images[i].transferTo(dest);// 文件写入
                }
                weibo.setPic1(a[0]);
                weibo.setPic2(a[1]);
                weibo.setPic3(a[2]);
                weibo.setPic4(a[3]);
                weibo.setPic5(a[4]);
                weibo.setPic6(a[5]);
            }

//        接收视频：
        boolean videoFlag = videoFile.getOriginalFilename().equals("");
        if (!videoFile.isEmpty() && !videoFlag) {

                String videoName =  videoFile.getOriginalFilename();
                System.out.println("上传的视频名为：" + videoName);
                // 获取文件的后缀名
                String suffixName = videoName.substring(videoName.lastIndexOf("."));
                System.out.println("视频的后缀名为：" + suffixName);
                // 设置文件存储路径
                String filePath = "E:/lidea编程项目/毕业设计项目（微博）/src/main/resources/static/videoUpload/";
                String path = filePath + videoName;
                File dest = new File(path);
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();// 新建文件夹
                }
            videoFile.transferTo(dest);// 文件写入

            weibo.setVideo(videoName);
        }

        //        接收音乐：
        boolean musicFlag = musicFile.getOriginalFilename().equals("");
        if (!musicFile.isEmpty() && !musicFlag) {

            String musicName =  musicFile.getOriginalFilename();
            System.out.println("上传的音乐名为：" + musicName);
            // 获取文件的后缀名
            String suffixName = musicName.substring(musicName.lastIndexOf("."));
            System.out.println("音乐的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "E:/lidea编程项目/毕业设计项目（微博）/src/main/resources/static/musicUpload/";
            String path = filePath + musicName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            musicFile.transferTo(dest);// 文件写入

            weibo.setMusic(musicName);
        }

        //        微博id(用uuid 取代id )
        weibo.setWeiboId(UUID.randomUUID().toString());

        // 用户id
        User user = (User) session.getAttribute("user");
        weibo.setUserId(user.getId());

        // 发送时间 （格式转换）
        Date postTime = new java.sql.Date(new java.util.Date().getTime());
        weibo.setPostTime(postTime);
        System.out.println(postTime);

        weiboService.post(weibo);
        return "redirect:/queryAll";
    }

    @PostMapping(value = "/zfWB")
    @ApiOperation(value = "转发所有微博（实时）")
    public String upload(String zfContent,String username, String weiboid) throws Exception {
        weibo weibo = new weibo();
        //        微博id(用uuid 取代id )
        weibo.setWeiboId(UUID.randomUUID().toString());
        weibo.setContent(zfContent);

        User user = userService.findUser(username);
        // 用户id
        weibo.setUserId(user.getId());

        // 发送时间 （格式转换）
        Date postTime = new java.sql.Date(new java.util.Date().getTime());
        weibo.setPostTime(postTime);
        weibo.setZfwbid(weiboid);

        weiboService.post(weibo);
        return "redirect:/queryAll";
    }


    @GetMapping("/queryAll")
    @ApiOperation(value = "查询所有微博（实时）")
    public String queryAllWeiboNow(HttpSession session, Model model){
        // 当前用户信息
        User user = (User) session.getAttribute("user");
        List<weiboCustom> weibos = weiboService.queryAllWeibo();
        love love = new love();
        love.setUserid(user.getId());
        collect collect = new collect();
        collect.setUserid(user.getId());

        List<weiboCustom> weibos1 = method.getWeibos(weibos,collect,love);

        model.addAttribute("weibos", weibos1);
        model.addAttribute("personalLabel",user.getpersonal_label());
        return "/index";
    }

    @GetMapping("/queryMe")
    @ApiOperation(value = "查询所有我微博（实时）")
    public String queryAllPersonalWeibo(HttpSession session, Model model){
        // 当前用户信息
        User user = (User) session.getAttribute("user");

        List<weiboCustom> weibos = weiboService.queryAllWeibo();

//        将数据库中的时间从date转为string ，并且安装 yyyy-MM-dd HH:mm:ss 格式 。
        for (weiboCustom weibo :weibos) {
            Date postTime = weibo.getPostTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(postTime);
            weibo.setPostTimeAsString(timeFormat);
        }


        model.addAttribute("weibos", weibos);

        return "/index";
    }


    @RequestMapping(value = "/headImg")
    @ApiOperation(value = "头像上传")
    public String headImg(@RequestParam("headImg") MultipartFile headImg, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");

        //        接收头像：
        boolean headImgFlag = headImg.getOriginalFilename().equals("");
        if (!headImg.isEmpty() && !headImgFlag) {

            String headImgName =  headImg.getOriginalFilename();
            System.out.println("上传的头像名为：" + headImgName);
            // 获取文件的后缀名
            String suffixName = headImgName.substring(headImgName.lastIndexOf("."));
            System.out.println("头像的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "E:/lidea编程项目/毕业设计项目（微博）/src/main/resources/static/headImgUpload/";
            String path = filePath + headImgName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            headImg.transferTo(dest);// 文件写入

            user.setHeadImgName(headImgName);
        }
        userService.updateHeadImg(user);

        return "redirect:/person";

    }




}
