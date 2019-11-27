package weibo.Config;

//新增加一个类用来添加虚拟路径映射

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imgUpload/**").addResourceLocations("file:E:/lidea编程项目/毕业设计项目（微博）/src/main/resources/static/imgUpload/");
        registry.addResourceHandler("/videoUpload/**").addResourceLocations("file:E:/lidea编程项目/毕业设计项目（微博）/src/main/resources/static/videoUpload/");
        registry.addResourceHandler("/headImgUpload/**").addResourceLocations("file:E:/lidea编程项目/毕业设计项目（微博）/src/main/resources/static/headImgUpload/");
        registry.addResourceHandler("/musicUpload/**").addResourceLocations("file:E:/lidea编程项目/毕业设计项目（微博）/src/main/resources/static/musicUpload/");

    }
}
