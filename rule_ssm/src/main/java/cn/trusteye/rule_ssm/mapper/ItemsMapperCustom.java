package cn.trusteye.rule_ssm.mapper;


import java.util.List;

import cn.trusteye.rule_ssm.po.ItemsCustom;
import cn.trusteye.rule_ssm.po.ItemsQueryVo;
import org.apache.ibatis.annotations.Param;

public interface ItemsMapperCustom {
    //商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}