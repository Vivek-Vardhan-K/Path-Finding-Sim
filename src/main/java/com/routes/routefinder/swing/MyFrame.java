package com.routes.routefinder.swing;

import com.routes.routefinder.service.CityMapBuilder;

import javax.swing.*;

public class MyFrame extends JFrame {
    CityMapBuilder cityMapBuilder;
    MyPanel myPanel;
    public MyFrame(CityMapBuilder cmp){
        myPanel=new MyPanel(cmp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(myPanel);
        this.pack();
        this.setSize(500,500);
        this.setLayout(null);
        this.setVisible(true);
    }
}
