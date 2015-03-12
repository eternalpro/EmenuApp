package com.yuansewenhua.utils;

import android.graphics.drawable.BitmapDrawable;
import android.widget.PopupWindow;

/**
 * 与弹出框有关的工具类
 * Created by gefangshuai on 2015/3/12.
 */
public class PopupWindowUtils {

    /**
     * 点击外部区域关闭
      * @param popupWindow
     */
    public static void setCloseOnOutsideTouched(PopupWindow popupWindow) {
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
    }

}
