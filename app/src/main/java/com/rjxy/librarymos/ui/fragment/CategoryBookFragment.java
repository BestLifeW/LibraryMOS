package com.rjxy.librarymos.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.ui.activity.C_only;

import java.util.ArrayList;
import java.util.HashMap;


public class CategoryBookFragment extends Fragment {

    private View view;
    private GridView gridView;
    private Button C_all;

    public static String conly="";



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
        initView();
    }


    private void initView(){
        C_all=(Button)view.findViewById(R.id.allbook);
        C_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), com.rjxy.librarymos.ui.activity.C_all.class);
                startActivity(intent);
            }
        });
    }



    /*
    *
    * */
    private void intEvent() {
        gridView = (GridView) view.findViewById(R.id.GridView);


        ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();



            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1.put("ItemImage", R.drawable.s4404535);
            map1.put("ItemText","出版小說");//小说
            meumList.add(map1);
            HashMap<String, Object> map2 = new HashMap<String, Object>();
            map2.put("ItemImage", R.drawable.s4404535);
            map2.put("ItemText","传记名著");//历史
            meumList.add(map2);
            HashMap<String, Object> map3 = new HashMap<String, Object>();
            map3.put("ItemImage", R.drawable.s4404535);
            map3.put("ItemText","健康教育");//健康
            meumList.add(map3);
            HashMap<String, Object> map4 = new HashMap<String, Object>();
            map4.put("ItemImage", R.drawable.s4404535);
            map4.put("ItemText","人文社科");//科普
            meumList.add(map4);
            HashMap<String, Object> map5 = new HashMap<String, Object>();
            map5.put("ItemImage", R.drawable.s4404535);
            map5.put("ItemText","经典散文");//散文
            meumList.add(map5);
            HashMap<String, Object> map6 = new HashMap<String, Object>();
            map6.put("ItemImage", R.drawable.s4404535);
            map6.put("ItemText","美食文化");//美食
            meumList.add(map6);
            HashMap<String, Object> map7 = new HashMap<String, Object>();
            map7.put("ItemImage", R.drawable.s4404535);
            map7.put("ItemText","经济管理");//经济
            meumList.add(map7);
            HashMap<String, Object> map8 = new HashMap<String, Object>();
            map8.put("ItemImage", R.drawable.s4404535);
            map8.put("ItemText","成功励志");//成功励志
            meumList.add(map8);
            HashMap<String, Object> map9 = new HashMap<String, Object>();
            map9.put("ItemImage", R.drawable.s4404535);
            map9.put("ItemText","政治军事");//政治军事
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
                                                if (arg2==0) {
                                                    conly="小说";
                                                    Intent intent=new Intent(getContext(),C_only.class);
                                                    startActivity(intent);

                                                }else if (arg2==1) {
                                                    conly="历史";
                                                    Intent intent=new Intent(getContext(),C_only.class);
                                                    startActivity(intent);

                                                }else if (arg2==2) {
                                                    conly="健康";
                                                    Intent intent=new Intent(getContext(),C_only.class);
                                                    startActivity(intent);

                                                }else if (arg2==3) {
                                                    conly="科普";
                                                    Intent intent=new Intent(getContext(),C_only.class);
                                                    startActivity(intent);

                                                }else if (arg2==4) {
                                                    conly="散文";
                                                    Intent intent=new Intent(getContext(),C_only.class);
                                                    startActivity(intent);

                                                }else if (arg2==5) {
                                                    conly="美食";
                                                    Intent intent=new Intent(getContext(),C_only.class);
                                                    startActivity(intent);

                                                }else if (arg2==6) {
                                                    conly="经济";
                                                    Intent intent=new Intent(getContext(),C_only.class);
                                                    startActivity(intent);

                                                }else if (arg2==7) {
                                                    conly="励志";
                                                    Intent intent=new Intent(getContext(),C_only.class);
                                                    startActivity(intent);

                                                }else if (arg2==8) {
                                                    conly="政治军事";
                                                    Intent intent=new Intent(getContext(),C_only.class);
                                                    startActivity(intent);

                                                }
                                            }
                                        }

        );
    }






}
