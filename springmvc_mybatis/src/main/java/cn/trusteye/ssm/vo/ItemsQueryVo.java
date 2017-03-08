package cn.trusteye.ssm.vo;

import cn.trusteye.ssm.po.ItemsCustom;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Rayman on 2017/2/22.
 */
public class ItemsQueryVo {
    //商品信息扩展类
    @Valid
    private ItemsCustom itemsCustom;

    private List<Integer> items_id;

    //批量商品信息
    private List<ItemsCustom> itemsList;

    public ItemsCustom getItemsCustom() {
        return itemsCustom;
    }

    public void setItemsCustom(ItemsCustom itemsCustom) {
        this.itemsCustom = itemsCustom;
    }

    public List<Integer> getItems_id() {
        return items_id;
    }

    public void setItems_id(List<Integer> items_id) {
        this.items_id = items_id;
    }

    public List<ItemsCustom> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemsCustom> itemsList) {
        this.itemsList = itemsList;
    }

}
