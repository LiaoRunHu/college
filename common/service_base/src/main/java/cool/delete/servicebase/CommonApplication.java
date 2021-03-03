package cool.delete.servicebase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2021-03-03 10:54
 */
@SpringBootApplication
@ComponentScan(basePackages = "cool.delete.commonutils")
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class,args);
    }
}
