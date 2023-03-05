package com.routes.routefinder;

import com.routes.routefinder.model.City;
import com.routes.routefinder.model.Route;
import com.routes.routefinder.service.CityMapBuilder;
import com.routes.routefinder.swing.MyFrame;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RouteFinderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RouteFinderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.setProperty("java.awt.headless", "false");
        CityMapBuilder cityMapBuilder=new CityMapBuilder(500,500,500);
        MyFrame myFrame=new MyFrame(cityMapBuilder);
//        System.exit(0);
    }
}
