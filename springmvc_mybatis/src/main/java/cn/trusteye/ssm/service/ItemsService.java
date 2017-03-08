package cn.trusteye.ssm.service;

import cn.trusteye.ssm.po.Items;
import cn.trusteye.ssm.po.ItemsCustom;
import cn.trusteye.ssm.vo.ItemsQueryVo;

import java.util.List;

/**
 * Created by Rayman on 2017/2/22.
 */
public interface ItemsService {
    // 商品查询列表
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    //根据id查询商品信息
    public ItemsCustom findItemsById(Integer id) throws Exception;

    //根据id修改商品信息
    public void updateItems(Integer id,ItemsQueryVo itemsQueryVo) throws Exception;

    //根据id删除商品信息
    public void deleteItems(ItemsQueryVo itemsQueryVo) throws Exception;
}
