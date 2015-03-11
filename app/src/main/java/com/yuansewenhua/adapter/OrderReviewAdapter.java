package com.yuansewenhua.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.yuansewenhua.dao.Drinks;
import com.yuansewenhua.dao.Foods;
import com.yuansewenhua.dto.GoodsForOrder;
import com.yuansewenhua.emenu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YangJD on 2014/12/18.
 */
public class OrderReviewAdapter extends BaseAdapter {

    private Context context;
    private List<GoodsForOrder> orderList;

    public OrderReviewAdapter(Context context) {
        this.context = context;
        orderList = new ArrayList<GoodsForOrder>();
    }

    public void setData(List<GoodsForOrder> orderList) {
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return this.orderList.size();
//        if (this.orderList.size() % 2 == 0) {
//            return this.orderList.size() / 2;
//        } else {
//            return this.orderList.size() / 2 + 1;
//        }

    }

    @Override
    public Object getItem(int i) {
        return this.orderList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.orderList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //取得条目界面
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.orderiten, null);
        //取得条目内容
        final GoodsForOrder item = this.orderList.get(i);
        //这是临时的单排显示item显示样式，包含增减菜品数量按钮
        //取得条目中包含的两个按钮（减少和增加）
        Button btnjia = (Button)itemView.findViewById(R.id.jia);
        Button btnjian  = (Button)itemView.findViewById(R.id.jian);
        //取得条目中包含的TextView
        TextView textView = (TextView)itemView.findViewById(R.id.orderName);
        textView.setText(item.getName() + " ×" + item.getCount());
        //点击上箭头，条目中的菜品数量加1
        btnjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setCount(item.getCount()+1);
                ((TextView)view.findViewById(R.id.orderName)).setText(item.getName() + " ×" + item.getCount());
            }
        });
        //点击下箭头，条目中的菜品数量减1
        btnjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setCount(item.getCount()-1);
                ((TextView)view.findViewById(R.id.orderName)).setText(item.getName() + " ×" + item.getCount());
            }
        });

        //以下是双排显示时的item样式
//        TextView leftTextView = (TextView) itemView.findViewById(R.id.itemleft);
//        TextView rightTextView = (TextView) itemView.findViewById(R.id.itemright);
//        GoodsForOrder left = this.orderList.get(i * 2);
//        if (left.getCount() <= 1) {
//            leftTextView.setText(left.getName());
//        } else {
//            leftTextView.setText(left.getName() + "×" + left.getCount());
//        }
//        GoodsForOrder right;
//        try {
//            right = this.orderList.get(i * 2 + 1);
//        } catch (IndexOutOfBoundsException e) {
//            return itemView;
//        }
//        if (right.getCount() <= 1) {
//            rightTextView.setText(right.getName());
//        } else {
//            rightTextView.setText(right.getName() + "×" + right.getCount());
//        }
        return itemView;
    }
}
