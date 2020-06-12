package cool.delete.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-01 23:43
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@ComponentScan(basePackages = {"cool.delete"})
@MapperScan("cool.delete.statistics.mapper")
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
