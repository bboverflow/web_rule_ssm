package cn.trusteye.web.start.controller;

import cn.trusteye.web.start.po.Items;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rayman on 2017/2/16.
 */
public class ItemsController2 implements HttpRequestHandler{
    @Override
    public void handleRequest(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws ServletException, IOException {
        //调用service查找 数据库，查询商品列表，这里使用静态数据模拟
        List<Items> itemsList = new ArrayList<Items>();
        //向list中填充静态数据

        Items items_1 = new Items();
        items_1.setName("联想笔记本");
        items_1.setPrice(6000f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone6苹果手机！");

        itemsList.add(items_1);
        itemsList.add(items_2);

        httpServletRequest.setAttribute("itemsList",itemsList);
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(httpServletRequest,httpServletResponse);

    }

//    @Override
//    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        //调用service查找 数据库，查询商品列表，这里使用静态数据模拟
//        List<Items> itemsList = new ArrayList<Items>();
//        //向list中填充静态数据
//
//        Items items_1 = new Items();
//        items_1.setName("联想笔记本");
//        items_1.setPrice(6000f);
//        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");
//
//        Items items_2 = new Items();
//        items_2.setName("苹果手机");
//        items_2.setPrice(5000f);
//        items_2.setDetail("iphone6苹果手机！");
//
//        itemsList.add(items_1);
//        itemsList.add(items_2);
//
//        //返回ModelAndView
//        ModelAndView modelAndView =  new ModelAndView();
//        //相当 于request的setAttribut，在jsp页面中通过itemsList取数据
//        modelAndView.addObject("itemsList", itemsList);
//
//        //指定视图
//        modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
//
//        return modelAndView;
//    }
}
