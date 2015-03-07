package com.yuansewenhua.emenu.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.yuansewenhua.dao.Drinks;
import com.yuansewenhua.dao.Foods;
import com.yuansewenhua.dao.Orders;
import com.yuansewenhua.dao.Porelationships;
import com.yuansewenhua.dto.GoodsForOrder;
import com.yuansewenhua.utils.CommonUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YangJD on 2014/12/14.
 */
public class OrderReviewTextView extends TextView implements View.OnClickListener {

    private List<GoodsForOrder> goodsList;
    private PopWinCheckOrder pop;

    public void init() {
        this.goodsList = new ArrayList<GoodsForOrder>();
        this.setTypeface(CommonUtils.getCustomTypeface(this.getContext(), "emenu.ttf"));
        this.setOnClickListener(this);
        pop = new PopWinCheckOrder(this.getContext(),this.goodsList);
    }

    public <T> void addOne(T entity) {
        GoodsForOrder oneGoods;
        if (entity instanceof Foods) {
            oneGoods = new GoodsForOrder();
            oneGoods.setType("Foods");
            oneGoods.setId(((Foods) entity).getId());
            oneGoods.setBigimagepath(((Foods) entity).getBigimagepath());
            oneGoods.setSmallimagepath(((Foods) entity).getSmallimagepath());
            oneGoods.setName(((Foods) entity).getName());
            oneGoods.setPrice(((Foods) entity).getPrice());
            oneGoods.setIsenable(((Foods) entity).getIsenable());
            oneGoods.setCount(1);
        } else if (entity instanceof Drinks) {
            oneGoods = new GoodsForOrder();
            oneGoods.setType("Drinks");
            oneGoods.setId(((Drinks) entity).getId());
            oneGoods.setBigimagepath(((Drinks) entity).getBigimagepath());
            oneGoods.setSmallimagepath(((Drinks) entity).getSmallimagepath());
            oneGoods.setName(((Drinks) entity).getName());
            oneGoods.setPrice(((Drinks) entity).getPrice());
            oneGoods.setIsenable(((Drinks) entity).getIsenable());
            oneGoods.setCount(1);
        } else {
            return;
        }
        boolean alreadyInThere = false;
        for (GoodsForOrder g : this.goodsList) {
            if (oneGoods.getId() == g.getId() && oneGoods.getType().equalsIgnoreCase(g.getType())) {
                g.setCount(g.getCount() + 1);
                alreadyInThere = true;
                break;
            }
        }
        if (!alreadyInThere) {
            this.goodsList.add(oneGoods);
        }
        String text = "";
        int sequence = 1;
        for (GoodsForOrder g : this.goodsList) {
            if (g.getCount() > 1) {
                text += " " + sequence + "." + g.getName() + "×" + g.getCount();
                this.setText(text);
            } else {
                text += " " + sequence + "." + g.getName();
                this.setText(text);
            }
            sequence++;
        }
    }

    public OrderReviewTextView(Context context) {
        super(context);
        init();
    }

    public OrderReviewTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OrderReviewTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public void onClick(View view) {
        if (pop.isShowing()) {
            pop.dismiss();
        } else {
            pop.calcCount();
            pop.setFocusable(true);
            pop.showAsDropDown(this);
        }
    }
}
