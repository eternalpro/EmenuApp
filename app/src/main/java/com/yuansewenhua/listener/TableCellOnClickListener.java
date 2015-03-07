package com.yuansewenhua.listener;

import android.view.View;

import com.yuansewenhua.emenu.customview.OrderReviewTextView;
import com.yuansewenhua.emenu.customview.TableCell;

/**
 * Created by YangJD on 2014/12/15.
 */
public class TableCellOnClickListener implements View.OnClickListener {

    OrderReviewTextView orderReviewTextView;

    public TableCellOnClickListener(OrderReviewTextView orderReviewTextView) {
        this.orderReviewTextView = orderReviewTextView;
    }
    @Override
    public void onClick(View view) {
        TableCell cell = (TableCell)view;
        this.orderReviewTextView.addOne(cell.getData());
    }


}
