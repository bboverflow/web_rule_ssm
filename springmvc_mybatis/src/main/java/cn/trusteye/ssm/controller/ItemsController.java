package cn.trusteye.ssm.controller;

import cn.trusteye.ssm.exception.CustomException;
import cn.trusteye.ssm.po.ItemsCustom;
import cn.trusteye.ssm.service.ItemsService;
import cn.trusteye.ssm.validation.ValidGroup1;
import cn.trusteye.ssm.vo.ItemsQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
    public ModelAndView queryItem(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception{

        //调用service查找 数据库，查询商品列表，这里使用静态数据模拟
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
        //向list中填充静态数据

        //返回ModelAndView
        ModelAndView modelAndView =  new ModelAndView();
        //相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        //指定视图
        modelAndView.setViewName("items/itemsList");

        return modelAndView;

    }

    //商品修改
    @RequestMapping("/editItems")
    public String editItems(Model model,@RequestParam(value = "id",required = true) Integer item_id) throws Exception{
        //调用service查询商品信息
        ItemsCustom itemsCustom = itemsService.findItemsById(item_id);

        model.addAttribute("itemsCustom",itemsCustom);

        return "items/editItems";
    }

    //商品提交
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(Model model, HttpServletRequest request,
                                  Integer id,
                                  @Validated(value = {ValidGroup1.class}) ItemsQueryVo itemsQueryVo,
                                  BindingResult bindingResult,
                                  MultipartFile multipartFile) throws Exception{
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for(ObjectError objectError:allErrors){
                System.out.println(objectError.getDefaultMessage());
            }
            model.addAttribute("allErrors",allErrors);

            //回写页面
            model.addAttribute("itemsCustom",itemsQueryVo.getItemsCustom());

            return "items/editItems";
        }

//        itemsService.updateItems(id,itemsQueryVo);



        //重定向,不用加根路径
        return "redirect:queryItems.action";
        //转发,不用加根路径
//        return "forward:queryItems.action";
    }

    //商品删除
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id) throws Exception{

        ItemsQueryVo itemsQueryVo = new ItemsQueryVo();
        itemsQueryVo.setItems_id(Arrays.asList(items_id));

        itemsService.deleteItems(itemsQueryVo);

        return "success";
    }

    //批量商品修改
    @RequestMapping("/editItemsAll")
    public String editItemsAll(Model model,ItemsQueryVo itemsQueryVo) throws Exception{
        //调用service查找 数据库
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
        model.addAttribute("itemsList",itemsList);

        return "items/editItemsBatch";
    }

    //批量商品修改提交
    @RequestMapping("/editItemsAllSubmit")
    public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception{

        return "success";
    }
}
