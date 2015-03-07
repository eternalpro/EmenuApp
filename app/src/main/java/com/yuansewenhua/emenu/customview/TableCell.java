package com.yuansewenhua.emenu.customview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuansewenhua.dao.Drinks;
import com.yuansewenhua.dao.Foods;
import com.yuansewenhua.emenu.R;
import com.yuansewenhua.utils.CommonUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by YangJD on 2014/12/10.
 * 九宫格中的一个格
 */
public class TableCell<T> extends FrameLayout {

    private ImageView backGroundView;
    private ImageView pictureView;
    private TextView priceTextView;
    private TextView nameTextView;
    private String price = "";
    private String name = "";
    private Typeface typeface;
    private boolean isEmpty = false;
    private String type;
    private long mid;
    private T data;

    public TableCell(Context context) {
        super(context);
        this.init();
        this.drawMe();
    }

    public TableCell(Context context, boolean isEmpty) {
        super(context);
        this.init();
        this.isEmpty = isEmpty;
        this.drawMe();
    }

    public TableCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
        this.drawMe();
    }

    public TableCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
        this.drawMe();
    }

    public void init() {
        Context context = this.getContext();
        this.backGroundView = new ImageView(context);
        this.pictureView = new ImageView(context);
        this.priceTextView = new TextView(context);
        this.nameTextView = new TextView(context);
        this.typeface = CommonUtils.getCustomTypeface(context, "nameandprice.ttf");
    }

    public void drawMe() {
        Context context = this.getContext();
        //生成并添加一个半透明背景，当单元格设置为是空（isEmpty==true）的时候，生成一个背景已足够！
        this.backGroundView.setAlpha(0.5f);
        this.backGroundView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.backGroundView.setImageResource(R.drawable.bg_preview);
        this.addView(this.backGroundView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        if (this.isEmpty) {
            return;
        }

        //生成并添加一个上下格式的LinearLayout，
        // 这个布局与半透明背景重叠并置于背景上用于存放图片和文字。
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams linearLayoutParam = new LayoutParams(-1, -1);
        int dp = CommonUtils.translatePxToDp(context, 12);
        linearLayoutParam.setMargins(dp, dp, dp, dp);
        this.addView(linearLayout, linearLayoutParam);

        //生成并向上下布局中的上部加入图片容器
        LinearLayout.LayoutParams linearParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 5f);
        this.pictureView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        linearLayout.addView(this.pictureView, linearParam);

        //生成并向上下布局中的下部添加布局（左右线性布局）
        LinearLayout textLinearLayout = new LinearLayout(context);
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(textLinearLayout, new LinearLayout.LayoutParams(-1, 0, 1f));

        //设置并添加菜名TextView
        this.nameTextView.setTypeface(this.typeface);
        this.nameTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f);
        this.nameTextView.getPaint().setFakeBoldText(true);
        this.nameTextView.setTextColor(Color.BLACK);
        this.nameTextView.setGravity(Gravity.BOTTOM);
        textLinearLayout.addView(this.nameTextView, new LinearLayout.LayoutParams(0, -1, 1f));

        //设置并添加价格TextView
        this.priceTextView.setTypeface(this.typeface);
        this.priceTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
        this.priceTextView.getPaint().setFakeBoldText(true);
        this.priceTextView.setTextColor(Color.BLACK);
        this.priceTextView.setGravity(Gravity.BOTTOM);
        textLinearLayout.addView(this.priceTextView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, -1));
    }


    public void setPrice(String price) {
        this.price = price;
        if (price == null || "".equals(price)) {
            this.priceTextView.setText("");
        } else {
            this.priceTextView.setText(String.format("%s元", this.price));
        }
    }

    public void setName(String name) {
        this.name = name;
        this.nameTextView.setText(name);
    }

    /**
     * 设置单元格背景，该背景永远都是半透明
     *
     * @param drawId R.Draw.ID
     */
    public void setBackGround(int drawId) {
        this.backGroundView.setImageResource(drawId);
    }

    /**
     * 设置单元格上方显示的图片
     *
     * @param assestFileName 存放与Assets目录下的图片文件名称
     */
    public void setPicture(String assestFileName) {
        Drawable draw = null;
        try {
            InputStream in = this.getContext().getAssets().open(assestFileName);
            draw = Drawable.createFromStream(in, null);
            this.pictureView.setImageDrawable(draw);
            in.close();
        } catch (Exception e) {
            this.pictureView.setImageDrawable(null);
        }
    }

    /**
     * 设置该单元格内是否包含内容。
     *
     * @param isEmpty true不包含内容只有背景；false加载图片和两个textView
     */
    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
        this.removeAllViews();
        this.drawMe();
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }


    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void initial() {
        this.setType("");
        this.setMid(-1);
        this.setPicture(null);
        this.setName("");
        this.setPrice("");
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        if (data instanceof Foods) {
            this.type = "Foods";
        } else if (data instanceof Drinks) {
            this.type = "Drinks";
        } else {
            this.type = "";
        }
    }
}
