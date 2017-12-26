package com.stx.xhb.meituancategorydemo;

import android.app.Application;

import com.stx.xhb.meituancategorydemo.utils.ScreenUtil;

import cn.bmob.v3.Bmob;

/**
 * Author: Mr.xiao on 2017/5/24
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ScreenUtil.init(this);
        Bmob.initialize(this, "3294a0f092543dc76c82b6b04134ac6f");
    }
}
