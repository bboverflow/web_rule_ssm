package cn.trusteye.rule_ssm.controller;

import cn.trusteye.rule_ssm.po.Items;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rayman on 2017/2/16.
 */
@Controller
public class ExampleController {
    @RequestMapping("example1")
    public ModelAndView example1() throws Exception{
        List<Items> itemsList = new ArrayList<Items>();
        Items item1  = new Items();
        item1.setName("联想笔记本");
        item1.setPrice(6000f);
        item1.setDetail("ThinkPad T430联想笔记本");

        Items item2 = new Items();
        item2.setName("苹果手机");
        item2.setPrice(5000f);
        item2.setDetail("Apple 6S手机");

        itemsList.add(item1);
        itemsList.add(item2);

        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);

        modelAndView.setViewName("/items/itemsList");

        return modelAndView;
    }
}
