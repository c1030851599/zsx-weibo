package weibo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import weibo.WebSocket.WebSocketServer;

@MapperScan(basePackages = "weibo.mapper")
@SpringBootApplication
//@Configuration
public class WeiboApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WeiboApplication.class, args);
        //解决WebSocket不能注入的问题
        WebSocketServer.setApplicationContext(applicationContext);

    }


//    /**
//     * 文件上传大小配置
//     * @return
//     */
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //允许上传的文件最大值
//        factory.setMaxFileSize("200MB"); //KB,MB
//        /// 设置总上传数据总大小
//        factory.setMaxRequestSize("300MB");
//        return factory.createMultipartConfig();
//    }
//




}
