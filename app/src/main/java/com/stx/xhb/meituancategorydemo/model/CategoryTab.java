package com.stx.xhb.meituancategorydemo.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Author: Mr.xiao on 2017/5/23
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:菜单项实体类
 */
public class CategoryTab extends BmobObject {
    private String type;
    private BmobFile typeIcon;
    public BmobFile getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(BmobFile typeIcon) {
        this.typeIcon = typeIcon;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CategoryTab(String type, BmobFile typeIcon) {
        this.type = type;
        this.typeIcon = typeIcon;
    }





}
