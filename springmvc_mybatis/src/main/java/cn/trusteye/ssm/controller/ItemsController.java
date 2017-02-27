package cn.trusteye.ssm.controller;

import cn.trusteye.ssm.po.ItemsCustom;
import cn.trusteye.ssm.service.ItemsService;
import cn.trusteye.ssm.vo.ItemsQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rayman on 2017/2/22.
 */
@Controller
//窄化请求路径
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    ItemsService itemsService;
    //商品查询
    @RequestMapping("/queryItems")
    public ModelAndView queryItem(HttpServletRequest request) throws Exception{

        System.out.println(request.getParameter("id"));

        //调用service查找 数据库，查询商品列表，这里使用静态数据模拟
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);
        //向list中填充静态数据

        //返回ModelAndView
        ModelAndView modelAndView =  new ModelAndView();
        //相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        //指定视图
        modelAndView.setViewName("items/itemsList");

        return modelAndView;

    }

/*
//    @RequestMapping("/editItems")
    @RequestMapping(value = "/editItems",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView editItems() throws Exception{
        //调用service查询商品信息
        ItemsCustom itemsCustom = itemsService.findItemsById(1);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsCustom",itemsCustom);
        modelAndView.setViewName("items/editItems");
        return modelAndView;
    }
*/
    //商品修改
    @RequestMapping("/editItems")
    public String editItems(Model model,Integer id) throws Exception{
        //调用service查询商品信息
        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        model.addAttribute("itemsCustom",itemsCustom);

        return "items/editItems";
    }

    //商品提交
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(HttpServletRequest request) throws Exception{
        //重定向,不用加根路径
//        return "redirect:queryItems.action";
        //重定向,不用加根路径
        return "forward:queryItems.action";
    }
}
