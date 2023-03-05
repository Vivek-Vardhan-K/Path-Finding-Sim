package com.routes.routefinder.service;

import com.routes.routefinder.model.City;
import com.routes.routefinder.model.Route;
import lombok.Data;

import java.util.*;

@Data
public class CityMapBuilder {
    boolean[][] boolArr;
    boolean[] vArr;
    boolean[] hArr;
    int width;
    int height;
    int cityCount;
    List<City> cityList;
    List<Route> routes;
    public CityMapBuilder(int w,int h,int c) throws Exception {
        this.width=w;
        this.height=h;
        this.cityCount=c;
        this.boolArr=new boolean[w][h];
        this.vArr=new boolean[h];
        this.hArr=new boolean[w];
        this.cityList=new ArrayList<>();
        this.routes=new ArrayList<>();
        generateCities();
        generateEdges(10);
    }

    private City placeRandomCity(int sqrRadii) throws Exception {
        boolean placed =false;
        int x=0,y=0;
        int xmin,xmax,ymin,ymax;
        boolean glitch=false;
        int iters=0;
        while(!placed){
            iters++;
            if(iters>height*width/2){
                System.err.println("Highly unlikely to build a map");
                throw new Exception("Can't build");
            }
            x=getRandomNumber(0,width-1);y=getRandomNumber(0,height-1);
            xmin=Math.max(0,x-sqrRadii);xmax=Math.min(width-1,x+sqrRadii);
            ymin=Math.max(0,y-sqrRadii);ymax=Math.min(height-1,y+sqrRadii);
            glitch=false;
            for(int i=ymin;i<=ymax;i++){
                if(vArr[i]){
                    for(int j=xmin;j<=xmax;j++){
                        if(boolArr[i][j]){
                            glitch=true;
                            break;
                        }
                    }
                }
                if(glitch){
                    break;
                }
            }
            if(!glitch){
                placed=true;
                boolArr[y][x]=true;
                vArr[y]=true;
                hArr[x]=true;
            }
        }
        cityList.add(new City(x,y));
        return cityList.get(cityList.size()-1);
    }

    private void generateCities() throws Exception {
        for(int i=0;i<cityCount;i++){
            placeRandomCity(12);
        }
    }

    private void generateEdges(int threshold){
        cityList.sort(Comparator.comparing(City::getX));
        boolean[] a=new boolean[width];boolean[] b=new boolean[height];boolean[] c=new boolean[width];boolean[] d=new boolean[height];
        int iters=cityCount;
        int p,q,r,s;
        int t1,t2;
        int intial_iters=iters;
        boolean once=false;
        while(iters > 0){
            if(!once && iters<intial_iters/2){
                cityList.sort(Comparator.comparing(City::getY));
                System.err.println("once ran");
                once=true;
            }
            int fw=getRandomNumber(0,1);
            //pick a city
            int cityIdx=getRandomNumber(0,cityCount-1);
            int minc=0,maxc=0,lastIdx,secondCityIdx;
            lastIdx=cityCount-1;
            if(fw==1){
                minc=cityIdx+1;
                maxc=Math.min(cityIdx+threshold,lastIdx);
            }
            else{
                maxc=cityIdx-1;
                minc=Math.max(cityIdx-threshold,0);
        }
            secondCityIdx=getRandomNumber(minc,maxc);
            if(minc>=maxc || (a[cityList.get(cityIdx).getX()] && b[cityList.get(cityIdx).getY()] && c[cityList.get(secondCityIdx).getX()] && d[cityList.get(secondCityIdx).getY()])){
                //route exists already or self loop
                continue;
            }
            p=cityList.get(cityIdx).getX();q=cityList.get(cityIdx).getY();r=cityList.get(secondCityIdx).getX();s=cityList.get(secondCityIdx).getY();
            t1=p-r;t2=q-s;
            if((t1*t1)+(t2*t2)>=5000){
                continue;
            }
            iters--;
            a[p]=true;b[q]=true;c[r]=true;d[s]=true;
            Route tempRoute=new Route(cityList.get(cityIdx),cityList.get(secondCityIdx));
            routes.add(tempRoute);
        }
    }

    public static int getRandomNumber(int min, int max) {
        max++;
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void printCity(){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                sb.append(boolArr[i][j]?1:0);
                sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
