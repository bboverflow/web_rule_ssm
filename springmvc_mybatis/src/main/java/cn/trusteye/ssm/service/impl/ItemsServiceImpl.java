package cn.trusteye.ssm.service.impl;

import cn.trusteye.ssm.mapper.ItemsCustomMapper;
import cn.trusteye.ssm.mapper.ItemsMapper;
import cn.trusteye.ssm.po.Items;
import cn.trusteye.ssm.po.ItemsCustom;
import cn.trusteye.ssm.service.ItemsService;
import cn.trusteye.ssm.vo.ItemsQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
        //中间对商品信息进行业务处理
        ItemsCustom itemsCustom = new ItemsCustom();
        BeanUtils.copyProperties(items,itemsCustom);
        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        //添加业务校验
        //更新商品信息，要求必须传入id
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }
}
