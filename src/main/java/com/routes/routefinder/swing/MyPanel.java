package com.routes.routefinder.swing;

import com.routes.routefinder.model.City;
import com.routes.routefinder.model.Route;
import com.routes.routefinder.service.CityMapBuilder;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyPanel extends JPanel {
    CityMapBuilder cityMapBuilder;
    public MyPanel(CityMapBuilder cmp) {
        this.setPreferredSize(new Dimension(1080,1080));
        this.cityMapBuilder=cmp;
    }
    @SneakyThrows
    public void paint(Graphics g){
        Graphics2D g2D=(Graphics2D) g;
        List<City> ls=cityMapBuilder.getCityList();
        List<Route> routes=cityMapBuilder.getRoutes();
        g2D.setStroke(new BasicStroke(4));
        for(City city:ls){
            g2D.drawLine(city.getX(),city.getY(),city.getX(),city.getY());
        }
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(Color.green);
//        g2D.drawLine(0,0,1080,1080);
        for(Route r:routes){
            g2D.drawLine(r.getA().getX(),r.getA().getY(),r.getB().getX(),r.getB().getY());
        }

    }
}
