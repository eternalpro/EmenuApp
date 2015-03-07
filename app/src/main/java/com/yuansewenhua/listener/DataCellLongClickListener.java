package com.yuansewenhua.listener;

import android.view.View;
import android.widget.TextView;

import com.yuansewenhua.emenu.customview.OrderReviewTextView;
import com.yuansewenhua.emenu.customview.TableCell;

/**
 * Created by YangJD on 2014-12-15.
 */
public class DataCellLongClickListener implements View.OnLongClickListener {

    OrderReviewTextView orderReviewTextView;

    public DataCellLongClickListener(OrderReviewTextView orderReviewTextView) {
        this.orderReviewTextView = orderReviewTextView;
    }

    @Override
    public boolean onLongClick(View view) {
        TableCell cell = (TableCell)view;
        this.orderReviewTextView.addOne(cell.getData());
        return true;
    }
}
