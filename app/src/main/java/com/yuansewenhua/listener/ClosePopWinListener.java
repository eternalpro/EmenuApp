package com.yuansewenhua.listener;

import android.view.View;
import android.widget.PopupWindow;

// 点击关闭后调用的事件，用于关闭当前弹出窗口
public class ClosePopWinListener implements View.OnClickListener {
        private PopupWindow popupWindow;

        public PopupWindow getPopupWindow() {
            return popupWindow;
        }

        public void setPopupWindow(PopupWindow popupWindow) {
            this.popupWindow = popupWindow;
        }

        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
        }
    }