package cn.trusteye.ssm.service.impl;

import cn.trusteye.ssm.exception.CustomException;
import cn.trusteye.ssm.mapper.ItemsCustomMapper;
import cn.trusteye.ssm.mapper.ItemsMapper;
import cn.trusteye.ssm.po.Items;
import cn.trusteye.ssm.po.ItemsCustom;
import cn.trusteye.ssm.service.ItemsService;
import cn.trusteye.ssm.vo.ItemsQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Rayman on 2017/2/22.
 */
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    ItemsCustomMapper itemsCustomMapper;

    @Autowired
    ItemsMapper itemsMapper;

    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsCustomMapper.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {
        Items items = itemsMapper.selectByPrimaryKey(id);
        if(items==null){
            throw new CustomException("查询的商品为空！!");
        }
        //中间对商品信息进行业务处理
        ItemsCustom itemsCustom = null;
        if(items != null) {
            itemsCustom = new ItemsCustom();
            BeanUtils.copyProperties(items, itemsCustom);
        }
        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsQueryVo itemsQueryVo) throws Exception {
        //添加业务校验
        //更新商品信息，要求必须传入id
        itemsQueryVo.getItemsCustom().setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsQueryVo.getItemsCustom());
    }

    @Override
    public void deleteItems(ItemsQueryVo itemsQueryVo) throws Exception {
        //删除商品信息
        itemsCustomMapper.deleteItems(itemsQueryVo);
    }
}
