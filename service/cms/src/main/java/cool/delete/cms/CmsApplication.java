package cool.delete.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-08 23:35
 */
@SpringBootApplication
@MapperScan("cool.delete.cms.mapper")
@ComponentScan(basePackages = {"cool.delete"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
