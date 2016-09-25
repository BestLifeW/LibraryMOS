package com.rjxy.librarymos.ui.activity;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.MyViewPagerAdapter;
import com.rjxy.librarymos.ui.fragment.AboutMeFragment;
import com.rjxy.librarymos.ui.fragment.CategoryBookFragment;
import com.rjxy.librarymos.ui.fragment.HotBookFragment;
import com.rjxy.librarymos.ui.fragment.SubscribeFrament;


public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private long mExitTime;
    private TabLayout mTabLayout;
    private int[] tabIcons = {R.drawable.imageview_homeselector, R.drawable.imageview_categoryselector, R.drawable.imageview_subscriptionselector, R.drawable.imageview_aboutmeselector};
    private ViewPager mViewPager;
    private ImageView img_title;
    private static final String TAG = "HomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(HotBookFragment.newInstance(), "热门图书");
        viewPagerAdapter.addFragment(CategoryBookFragment.newInstance(), "类别选择");
        viewPagerAdapter.addFragment(SubscribeFrament.newInstance(), "订阅啦");
        viewPagerAdapter.addFragment(AboutMeFragment.newInstance(), "关于我");//添加Fragment
        mViewPager.setAdapter(viewPagerAdapter);//设置适配器


        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab());//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.setupWithViewPager(mViewPager);

        setupTabIcons();
        initEvent();
        //此处是个大坑，每次进来 都不会默认选择tab1页，用这个方法迂回一下。找了十年！
        mViewPager.setCurrentItem(1);
        mViewPager.setCurrentItem(0);
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
        mTabLayout.getTabAt(2).setCustomView(getTabView(2));
        mTabLayout.getTabAt(3).setCustomView(getTabView(3));
    }



    private void initEvent() {
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                ChangeViewPager(position);
                ChangeTitle(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    //改变主题标题内容
    private void ChangeTitle(int position) {
        switch (position) {
            case 0:
                toolbar.setTitle("热门图书");
                break;
            case 1:
                toolbar.setTitle("类别选择");
                break;
            case 2:
                toolbar.setTitle("订阅详情");
                break;
            case 3:
                toolbar.setTitle("关于我");
                break;
            default:
                break;
        }
    }

    //跳转viewpager
    private void ChangeViewPager(int position) {
        mViewPager.setCurrentItem(position);
    }

    //菜单项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);//指定Toolbar上的视图文件
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                this.finish();//真正实现回退功能的代码
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        img_title = (ImageView) view.findViewById(R.id.img_title);
        img_title.setImageResource(tabIcons[position]);
        return view;
    }


    //点击两次退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
