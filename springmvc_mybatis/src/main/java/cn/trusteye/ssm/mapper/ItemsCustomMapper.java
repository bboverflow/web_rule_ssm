package cn.trusteye.ssm.mapper;


import cn.trusteye.ssm.po.ItemsCustom;
import cn.trusteye.ssm.vo.ItemsQueryVo;

import java.util.List;

public interface ItemsCustomMapper {
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    public void deleteItems(ItemsQueryVo itemsQueryVo) throws Exception;
}