package com.stx.xhb.meituancategorydemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.stx.xhb.meituancategorydemo.adapter.CagegoryViewPagerAdapter;
import com.stx.xhb.meituancategorydemo.adapter.EntranceAdapter;
import com.stx.xhb.meituancategorydemo.model.CategoryTab;
import com.stx.xhb.meituancategorydemo.utils.ScreenUtil;
import com.stx.xhb.meituancategorydemo.widget.IndicatorView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity {
    public static final int HOME_ENTRANCE_PAGE_SIZE = 10;//首页菜单单页显示数量
    private ViewPager entranceViewPager;
    private LinearLayout homeEntranceLayout;
    private List<CategoryTab> homeEntrances;
    private IndicatorView entranceIndicatorView;
    private String types;
    private String icons;
    private static final int UNDATE_TEXT=1;
    private  EntranceAdapter entranceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "3294a0f092543dc76c82b6b04134ac6f");
        initData();
        initView();

    }
    private void initView() {
        homeEntranceLayout = (LinearLayout) findViewById(R.id.home_entrance);
        entranceViewPager = (ViewPager) findViewById(R.id.main_home_entrance_vp);
        entranceIndicatorView = (IndicatorView) findViewById(R.id.main_home_entrance_indicator);
    }
    private void initData() {
        homeEntrances = new ArrayList<>();
        BmobQuery<CategoryTab> query = new BmobQuery<>();
        query.order("-createdAt");
       query.findObjects(this, new FindListener<CategoryTab>() {
           @Override
           public void onSuccess(List<CategoryTab> list) {
               for (int i = 0; i <list.size() ; i++) {
                   types=list.get(i).getType();
                   icons=list.get(i).getIconUrl();
                   homeEntrances.add(new CategoryTab(types,icons));
               }
               if (homeEntrances.size()==list.size()){
                   init();
               }else {

               }

           }
           @Override
           public void onError(int i, String s) {
           }
       });
    }

    private void init() {
        LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth() / 2.0f));
        //首页菜单分页
        FrameLayout.LayoutParams entrancelayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth() / 2.0f + 70));
        homeEntranceLayout.setLayoutParams(entrancelayoutParams);
        entranceViewPager.setLayoutParams(layoutParams12);
        LayoutInflater inflater = LayoutInflater.from(this);
        //将RecyclerView放至ViewPager中：
        int pageSize = HOME_ENTRANCE_PAGE_SIZE;
        //一共的页数等于 总数/每页数量，并取整。
        int pageCount = (int) Math.ceil(homeEntrances.size() * 1.0 / pageSize);
        List<View> viewList = new ArrayList<View>();
        for (int index = 0; index < pageCount; index++) {
            //每个页面都是inflate出一个新实例
            RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.item_home_entrance_vp, entranceViewPager, false);
            recyclerView.setLayoutParams(layoutParams12);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 5));
            entranceAdapter = new EntranceAdapter(MainActivity.this, homeEntrances, index, HOME_ENTRANCE_PAGE_SIZE);
            recyclerView.setAdapter(entranceAdapter);
            viewList.add(recyclerView);
        }
        CagegoryViewPagerAdapter adapter = new CagegoryViewPagerAdapter(viewList);
        entranceViewPager.setAdapter(adapter);
        entranceIndicatorView.setIndicatorCount(entranceViewPager.getAdapter().getCount());
        entranceIndicatorView.setCurrentIndicator(entranceViewPager.getCurrentItem());
        entranceAdapter.notifyDataSetChanged();
        entranceViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
        entranceIndicatorView.setCurrentIndicator(position);
            }
        });
    }
}
