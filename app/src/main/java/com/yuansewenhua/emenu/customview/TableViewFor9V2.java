package com.yuansewenhua.emenu.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.yuansewenhua.dao.Drinks;
import com.yuansewenhua.dao.Foods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YangJD on 2014/12/10.
 * 一个三行三列的九宫格
 */
public class TableViewFor9V2<T> extends TableLayout {
    //定义三行和三列的常量
    private final int COLS = 3;
    private final int ROWS = 3;
    //声明表格的单元格列表
    private TableCell[] cellArray;
    //声明表格的数据源列表
    private List<T> dataList;
    //用于判断该表格的泛型到底是Food还是Drink
    private T one;
    private OnLongClickListener longClickListener;
    private OnClickListener clickListener;

    public TableViewFor9V2(Context context) {
        super(context);
        this.drawMe();
    }

    public TableViewFor9V2(Context context, List<T> dataList) {
        super(context);
        this.dataList = dataList;
        this.drawMe();
        this.refresh();
    }

    public TableViewFor9V2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.drawMe();
    }

    /**
     * 把表格中的所有既存数据清掉数据
     */
    public void initial() {
        this.dataList.clear();
        for (TableCell cell : this.cellArray) {
            cell.initial();
        }
    }

    public void drawMe() {
        final Context context = this.getContext();
        this.cellArray = new TableCell[9];
        this.dataList = new ArrayList<T>();
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f);
        for (int i = 0, arrayIndex = 0; i < ROWS; i++) {
            TableRow row = new TableRow(context);
            for (int j = 0; j < COLS; j++) {
                TableCell cell = new TableCell(context);
                row.addView(cell, new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f));
                this.cellArray[arrayIndex++] = cell;
            }
            this.addView(row, layoutParams);
        }
    }


    private void setCellOnClickListener(OnClickListener onClickListener) {
        if (this.dataList.size() >= 9) {
            for (int i = 0; i < this.cellArray.length; i++) {
                this.cellArray[i].setOnClickListener(onClickListener);
            }
        } else {
            for (int i = 0; i < this.dataList.size(); i++) {
                this.cellArray[i].setOnClickListener(onClickListener);
            }
        }
    }

    private void setCellOnLongClickListener(OnLongClickListener onLongClickListener) {
        if (this.dataList.size() >= 9) {
            for (int i = 0; i < this.cellArray.length; i++) {
                this.cellArray[i].setOnLongClickListener(onLongClickListener);
            }
        } else {
            for (int i = 0; i < this.dataList.size(); i++) {
                this.cellArray[i].setOnLongClickListener(onLongClickListener);
            }
        }
    }

    public void setDataAndRefresh(List<T> dataList) {
        this.setData(dataList);
        this.refresh();
    }

    public void setData(List<T> dataList) {
        this.dataList = dataList;
    }

    public void refresh() {
        int maxDataSize = this.dataList.size();
        try {
            one = this.dataList.get(0);
        } catch (Exception e) {
            Log.w("刷新表格出错", "表格的数据源中没有数据");
            this.initial();
            return;
        }
        if (one instanceof Foods) {
            for (int i = 0; i < this.cellArray.length; i++) {
                TableCell cell = this.cellArray[i];
                try {
                    Foods food = (Foods) this.dataList.get(i);
                    cell.setMid(food.getId());
                    cell.setType("Foods");
                    cell.setPicture(food.getSmallimagepath());
                    cell.setName(food.getName());
                    cell.setPrice(food.getPrice());
                    cell.setData(food);
                } catch (IndexOutOfBoundsException e) {
                    cell.initial();
                    continue;
                }
            }
        } else if (one instanceof Drinks) {
            for (int i = 0; i < this.cellArray.length; i++) {
                TableCell cell = this.cellArray[i];
                try {
                    Drinks drink = (Drinks) this.dataList.get(i);
                    cell.setMid(drink.getId());
                    cell.setType("Drinks");
                    cell.setPicture(drink.getSmallimagepath());
                    cell.setName(drink.getName());
                    cell.setPrice(drink.getPrice());
                    cell.setData(drink);
                } catch (IndexOutOfBoundsException e) {
                    cell.initial();
                    continue;
                }
            }
        } else {
            return;
        }
        this.setCellOnLongClickListener(this.longClickListener);
        this.setCellOnClickListener(this.clickListener);
    }

    public void setLongClickListener(OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
