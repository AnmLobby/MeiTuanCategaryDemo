package com.stx.xhb.meituancategorydemo.model;

import cn.bmob.v3.BmobObject;

/**
 * Author: Mr.xiao on 2017/5/23
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:菜单项实体类
 */
public class CategoryTab extends BmobObject {
    private String type;
    private String iconUrl;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }



    public CategoryTab(String type, String iconUrl) {
        this.type = type;
        this.iconUrl = iconUrl;
    }





}
