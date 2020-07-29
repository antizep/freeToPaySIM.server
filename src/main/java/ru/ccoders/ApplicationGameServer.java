//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ccoders;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan
@EnableAutoConfiguration
public class ApplicationGameServer {
    @Autowired
    Environment environment;

    public ApplicationGameServer() {
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationGameServer.class, new String[0]);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return (args) -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            String[] var4 = beanNames;
            int var5 = beanNames.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String beanName = var4[var6];
                System.out.println(beanName);
            }

            String port = this.environment.getProperty("local.server.port");
            System.out.println(port);
        };
    }
}
