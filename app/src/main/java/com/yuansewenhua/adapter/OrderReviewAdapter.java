package com.yuansewenhua.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Binder;
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
import com.yuansewenhua.emenu.customview.OrderReviewTextView;

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
    private OrderReviewTextView textView;


    public OrderReviewAdapter(Context context, OrderReviewTextView textView) {
        this.context = context;
        this.textView = textView;
        orderList = textView.getGoodsList();
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
        GoodsForOrder item = this.orderList.get(i);
        //这是临时的单排显示item显示样式，包含增减菜品数量按钮
        //取得条目中包含的两个按钮（减少和增加）
        Button btnjia = (Button)itemView.findViewById(R.id.jia);
        Button btnjian  = (Button)itemView.findViewById(R.id.jian);
        //取得条目中包含的TextView
        TextView textView = (TextView)itemView.findViewById(R.id.orderName);
        textView.setText(item.getName() + " ×" + item.getCount());
        //点击上箭头，条目中的菜品数量加1
        btnjia.setOnClickListener(new BtnjiaClickListener(item));
        //点击下箭头，条目中的菜品数量减1
        btnjian.setOnClickListener(new BtnjianClickListener(item));

        return itemView;
    }

    /**
     * 增加按钮事件
     */
    class BtnjiaClickListener implements View.OnClickListener {
        private GoodsForOrder item;

        BtnjiaClickListener(GoodsForOrder item) {
            this.item = item;
        }

        @Override
        public void onClick(View v) {
            item.setCount(item.getCount() + 1);
            notifyDataSetChanged();// 通知数据刷新
            textView.setText();
        }
    }

    /**
     * 减少按钮事件
     */
    class BtnjianClickListener implements View.OnClickListener{
        private GoodsForOrder item;
        private

        BtnjianClickListener(GoodsForOrder item) {
            this.item = item;
        }

        @Override
        public void onClick(View v) {

            if(item.getCount() <= 1){
                new AlertDialog.Builder(OrderReviewAdapter.this.context)
                        .setTitle("注意")
                        .setMessage("确认从订单中清楚此项吗？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clearItem(item);
                                notifyDataSetChanged(); // 通知数据刷新
                                textView.setText();
                            }
                        })
                        .setNegativeButton("否", null)
                        .show();
            }else{
                item.setCount(item.getCount() - 1);
                textView.setText();
            }
            notifyDataSetChanged(); // 通知数据刷新
        }

        /**
         * 清除条目
         * @param item
         */
        private void clearItem(GoodsForOrder item) {
            orderList.remove(item);
        }

    }
}
