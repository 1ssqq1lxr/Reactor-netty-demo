package com.iot.device;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.reactive.config.EnableWebFlux;

import static org.springframework.boot.SpringApplication.run;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableWebFlux
public class App 
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext run = run(App.class, args);
    }
}
