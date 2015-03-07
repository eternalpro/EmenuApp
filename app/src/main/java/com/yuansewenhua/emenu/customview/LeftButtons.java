package com.yuansewenhua.emenu.customview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yuansewenhua.dao.Foodstype;
import com.yuansewenhua.emenu.R;
import com.yuansewenhua.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YangJD on 2014/12/3.
 */
public class LeftButtons extends LinearLayout{

    private List<Button> buttonList;
    private Context context;
    private LayoutParams params;
    private Typeface typeface;
    private ColorStateList colorStateList;

    public LeftButtons(Context context) {
        super(context);
        this.context = context;
        this.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        this.setOrientation(VERTICAL);
        this.buttonList = new ArrayList<Button>();
        //创建一个布局参数，所有按钮都会用到
        this.params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        this.params.setMargins(0, CommonUtils.translateDpToPx(context, 4), 0, 0);
        //加载自定义字体
        this.typeface = CommonUtils.getCustomTypeface(context, "emenu.ttf");
        //加载一组颜色(为了让按下时的字体颜色跟平常的颜色不同)
        this.colorStateList = this.getResources().getColorStateList(R.color.left_buttons);
        this.init();
    }

    /**
     * 初始化5只系统选菜按钮（热菜凉菜，荤菜素菜，清真菜）
     */
    public void init() {
        //如果包含View则全部清空
        this.removeAllViews();
        //创建热菜按钮
        Button btnRecai = new Button(this.context);
        btnRecai.setBackgroundResource(R.drawable.bg_button);
        btnRecai.setTextColor(Color.RED);
        btnRecai.setText("热菜");
        btnRecai.setTypeface(this.typeface);
        btnRecai.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        this.addView(btnRecai, params);
        this.buttonList.add(btnRecai);
        //创建凉菜按钮
        Button btnLiangcai = new Button(this.context);
        btnLiangcai.setBackgroundResource(R.drawable.bg_button);
        btnLiangcai.setTextColor(this.colorStateList);
        btnLiangcai.setText("凉菜");
        btnLiangcai.setTypeface(this.typeface);
        btnLiangcai.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        this.addView(btnLiangcai, params);
        this.buttonList.add(btnLiangcai);
        //创建荤菜按钮
        Button btnHuncai = new Button(this.context);
        btnHuncai.setBackgroundResource(R.drawable.bg_button);
        btnHuncai.setTextColor(this.colorStateList);
        btnHuncai.setText("荤菜");
        btnHuncai.setTypeface(this.typeface);
        btnHuncai.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        this.addView(btnHuncai, params);
        this.buttonList.add(btnHuncai);
        //创建素菜按钮
        Button btnSucai = new Button(this.context);
        btnSucai.setBackgroundResource(R.drawable.bg_button);
        btnSucai.setTextColor(this.colorStateList);
        btnSucai.setText("素菜");
        btnSucai.setTypeface(this.typeface);
        btnSucai.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        this.addView(btnSucai, params);
        this.buttonList.add(btnSucai);
        //创建清真菜按钮
        Button btnQingzhen = new Button(this.context);
        btnQingzhen.setBackgroundResource(R.drawable.bg_button);
        btnQingzhen.setTextColor(this.colorStateList);
        btnQingzhen.setText("清真菜");
        btnQingzhen.setTypeface(this.typeface);
        btnQingzhen.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        this.addView(btnQingzhen, params);
        this.buttonList.add(btnQingzhen);
    }

    public void selectedButtonBefore(){
        for (Button button : this.buttonList) {
            button.setTextColor(this.colorStateList);
            button.setClickable(true);
        }
    }

    /**
     * 把数据库中自定义的类型追加到系统类型的下方，最后再追加酒水类型
     * @param typeList 数据库中自定义的类型列表
     */
    public void addFoodsTypeList(List<Foodstype> typeList) {
        for (Foodstype fType : typeList) {
            Button button = new Button(this.context);
            button.setBackgroundResource(R.drawable.bg_button);
            button.setText(fType.getTitle());
            button.setTextColor(this.colorStateList);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            button.setTypeface(this.typeface);
            this.addView(button, this.params);
            this.buttonList.add(button);
        }
        //创建酒水按钮
        Button btnDrink = new Button(this.context);
        btnDrink.setBackgroundResource(R.drawable.bg_button);
        btnDrink.setTextColor(this.colorStateList);
        btnDrink.setText("酒水");
        btnDrink.setTypeface(this.typeface);
        btnDrink.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        this.addView(btnDrink, params);
        this.buttonList.add(btnDrink);
    }

    public List<Button> getButtonList() {
        return this.buttonList;
    }

    public void setAllButtonsOnClickListener(OnClickListener listener) {
        for (Button button : this.buttonList) {
            button.setOnClickListener(listener);
        }
    }

}
