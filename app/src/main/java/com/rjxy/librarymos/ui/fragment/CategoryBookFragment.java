package com.rjxy.librarymos.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.rjxy.librarymos.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by lovec on 2016/9/22.
 */
public class CategoryBookFragment extends Fragment {

    private View view;
    private GridView gridView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_categorybook, null);

        init();
        return view;
    }

    public static CategoryBookFragment newInstance() {

        Bundle args = new Bundle();

        CategoryBookFragment fragment = new CategoryBookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /*
 * 初始化
 * */
    private void init() {
        intEvent();
    }

    /*
    *
    * */
    private void intEvent() {
        gridView = (GridView) view.findViewById(R.id.GridView);

  /*      String[] arrText = new String[]{
                "京东商品", "滴滴出行", "话费流量",
                "油卡充值", "火车票", "飞机票",
                "京东优选", "游戏卡", "Q币"
        };*/

        ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();

        /*for (int i = 1; i < 10; i++) {}*/
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1.put("ItemImage", R.drawable.s4404535);
            map1.put("ItemText","傳記名著");
            meumList.add(map1);
            HashMap<String, Object> map2 = new HashMap<String, Object>();
            map2.put("ItemImage", R.drawable.s4404535);
            map2.put("ItemText","出版小說");
            meumList.add(map2);
            HashMap<String, Object> map3 = new HashMap<String, Object>();
            map3.put("ItemImage", R.drawable.s4404535);
            map3.put("ItemText","人文社科");
            meumList.add(map3);
            HashMap<String, Object> map4 = new HashMap<String, Object>();
            map4.put("ItemImage", R.drawable.s4404535);
            map4.put("ItemText","生活時尚");
            meumList.add(map4);
            HashMap<String, Object> map5 = new HashMap<String, Object>();
            map5.put("ItemImage", R.drawable.s4404535);
            map5.put("ItemText","經濟管理");
            meumList.add(map5);
            HashMap<String, Object> map6 = new HashMap<String, Object>();
            map6.put("ItemImage", R.drawable.s4404535);
            map6.put("ItemText","外文原著");
            meumList.add(map6);
            HashMap<String, Object> map7 = new HashMap<String, Object>();
            map7.put("ItemImage", R.drawable.s4404535);
            map7.put("ItemText","健康教育");
            meumList.add(map7);
            HashMap<String, Object> map8 = new HashMap<String, Object>();
            map8.put("ItemImage", R.drawable.s4404535);
            map8.put("ItemText","成功励志");
            meumList.add(map8);
            HashMap<String, Object> map9 = new HashMap<String, Object>();
            map9.put("ItemImage", R.drawable.s4404535);
            map9.put("ItemText","政治军事");
            meumList.add(map9);


        SimpleAdapter saMenuItem = new SimpleAdapter(getContext(),
                meumList, //数据源
                R.layout.meunitem, //xml实现
                new String[]{"ItemImage", "ItemText"}, //对应map的Key
                new int[]{R.id.ItemImage, R.id.ItemText});  //对应R的Id

//添加Item到网格中
        gridView.setAdapter(saMenuItem);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            public void onItemClick (AdapterView<?> arg0, View arg1, int arg2, long arg3){
                                       /*         if (arg2==0) {
                                                    Intent intent=new Intent(MainActivity.this,GaiShu.class);
                                                    startActivity(intent);
                                                    finish();
                                                }else if (arg2==1) {
                                                    Intent intent=new Intent(MainActivity.this,MainCampusLife.class);
                                                    startActivity(intent);
                                                    finish();
                                                }else if (arg2==2) {
                                                    Intent intent=new Intent(MainActivity.this,FengCai.class);
                                                    startActivity(intent);
                                                    finish();
                                                }else if (arg2==3) {
                                                    Intent intent=new Intent(MainActivity.this,NewsActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }else if (arg2==4) {
                                                    Intent intent=new Intent(MainActivity.this,MainTrafficAssist.class);
                                                    startActivity(intent);
                                                    finish();
                                                }else if (arg2==5) {
                                                    Intent intent=new Intent(MainActivity.this,PhoneListActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }*/
                                            }
                                        }

        );
    }






}
