package cn.hnx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by hnx on 2018/7/30.
 */
@SpringBootApplication
@ComponentScan(basePackages={"cn.hnx"})
public class AppApplication {
    public static void main(String[] args) {
        startServer(args);
    }
    public static void startServer(String... args){
        SpringApplication.run(AppApplication.class, args);
    }
}
