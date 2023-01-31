package edu.northeastern.numad23sp_yingjiehuang;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

    private static List<Link> lst = new ArrayList<Link>();

    public MyApp() {

    }

    public static List<Link> getList() {
        return lst;
    }

    public static void setList(List<Link> lst) {
        MyApp.lst = lst;
    }
}