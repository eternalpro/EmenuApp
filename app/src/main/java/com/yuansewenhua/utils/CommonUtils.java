package com.yuansewenhua.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

import com.yuansewenhua.dao.FoodsDao;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.query.WhereCondition;

/**
 * Created by YangJD on 2014/12/3.
 */
public class CommonUtils {

    public static int translateDpToPx(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int translatePxToDp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * //加载自定义字体
     *
     * @param context
     * @param ttfFileName 包含扩展名的字体名称，不用加“/”
     * @return 调用setTypeface()方法可以使用这个返回值
     */
    public static Typeface getCustomTypeface(Context context, String ttfFileName) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/" + ttfFileName);
    }

    /**
     * 计算总共有多少页（按照每页9条数据）
     * @param dao
     * @param type
     * @return
     */
    public static long calcPageCount(AbstractDao dao,String type){
        long count = dao.queryBuilder().where(getWhereConditionByType(type)).count();
        return count % 9 == 0 ? count / 9 : count / 9 + 1;
    }

    /**
     * 获取当前屏幕宽度
     * @param activity
     * @return
     */
    public static int calcScreenWidth(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取当前屏幕高度
     * @param activity
     * @return
     */
    public static int calcScreenHeight(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 该方法只适合本系统使用，耦合性极强，不建议理解和阅读
     *
     * @param type
     * @return
     */
    public static WhereCondition getWhereConditionByType(String type) {
        if (type.equals("热菜")) {
            return FoodsDao.Properties.Isliang.eq(false);
        } else if (type.equals("凉菜")) {
            return FoodsDao.Properties.Isliang.eq(true);
        } else if (type.equals("荤菜")) {
            return FoodsDao.Properties.Issu.eq(false);
        } else if (type.equals("素菜")) {
            return FoodsDao.Properties.Issu.eq(true);
        } else if (type.equals("清真菜")) {
            return FoodsDao.Properties.Isqingzhen.eq(true);
        } else {
            return FoodsDao.Properties.Typetitle.like("%" + type + "%");
        }
    }
}
