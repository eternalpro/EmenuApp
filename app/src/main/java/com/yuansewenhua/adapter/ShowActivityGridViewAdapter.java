package com.yuansewenhua.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuansewenhua.dao.Foods;
import com.yuansewenhua.emenu.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by YangJD on 2014/11/25.
 */
public class ShowActivityGridViewAdapter extends BaseAdapter {

    private List<Foods> foodsList;
    private Context context;

    public ShowActivityGridViewAdapter(Context context,List<Foods> foodsList){
        this.foodsList = foodsList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return foodsList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return foodsList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        InputStream inputStream;
        Foods currentFood = this.foodsList.get(i);
        View cell = LayoutInflater.from(context).inflate(R.layout.gridcell,null);
        try {
            inputStream = this.context.getAssets().open(currentFood.getSmallimagepath());
            ((ImageView) cell.findViewById(R.id.foodImage)).setImageDrawable(Drawable.createFromStream(inputStream, null));
            inputStream.close();
            ((TextView)cell.findViewById(R.id.name)).setText(currentFood.getName());
            ((TextView)cell.findViewById(R.id.price)).setText(String.format("￥%s元",currentFood.getPrice()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cell;
    }
}
