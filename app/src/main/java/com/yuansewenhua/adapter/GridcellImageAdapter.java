package com.yuansewenhua.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuansewenhua.emenu.R;
import com.yuansewenhua.emenu.ShowActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fangshuai on 2014-11-24-0024.
 */
public class GridcellImageAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> mData;
    private LayoutInflater mInflater;// 动态布局映射

    public GridcellImageAdapter(Context context, List<Map<String, Object>> mData) {
        this.context = context;
        this.mData = mData;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        convertView = mInflater.inflate(R.layout.gridcell, null);
//
//        TextView name = (TextView) convertView.findViewById(R.id.nameText);
//        name.setText(mData.get(position).get(ShowActivity.NAME_KEY).toString());
//
//        TextView price = (TextView) convertView.findViewById(R.id.priceText);
//        price.setText(mData.get(position).get(ShowActivity.PRICE_KEY).toString());
//
//        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
//
//        imageView.setImageBitmap((android.graphics.Bitmap) mData.get(position).get(ShowActivity.IMAGE_KEY));
////        imageView.setImageDrawable((Drawable)mData.get(position).get(ShowActivity.IMAGE_KEY));
//        return convertView;
        return null;
    }
}
