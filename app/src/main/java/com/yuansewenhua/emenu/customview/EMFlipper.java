package com.yuansewenhua.emenu.customview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.yuansewenhua.emenu.R;

/**
 * Created by YangJD on 2014/12/11.
 */
public class EMFlipper extends ViewFlipper implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private Animation inLeftToRight;
    private Animation outLeftToRight;
    private Animation inRightToLeft;
    private Animation outRightToLeft;
    private float x;
    private long pageCount = 0;
    private int currentPageNum = 1;
    private GestureDetector gestureDetector;
    private int duration = 300;
    private Handler pageHandler;

    public EMFlipper(Context context) {
        super(context);
        this.init();
    }

    public EMFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    private void init() {
        Context context = this.getContext();
        this.setOnTouchListener(this);
        this.gestureDetector = new GestureDetector(context, this);
        this.inLeftToRight = AnimationUtils.loadAnimation(context, R.anim.in_lefttoright);
        this.inLeftToRight.setDuration(duration);
        this.inRightToLeft = AnimationUtils.loadAnimation(context, R.anim.in_righttoleft);
        this.inRightToLeft.setDuration(duration);
        this.outLeftToRight = AnimationUtils.loadAnimation(context, R.anim.out_lefttoright);
        this.outLeftToRight.setDuration(duration);
        this.outRightToLeft = AnimationUtils.loadAnimation(context, R.anim.out_righttoleft);
        this.outRightToLeft.setDuration(duration);
        this.addView(new TableViewFor9V2(context));
        this.addView(new TableViewFor9V2(context));
        this.currentPageNum = 1;
    }

    /**
     * 把表格中的所有既存数据清掉数据
     */
    public void initial() {
        for (int i = 0; i < this.getChildCount(); i++) {
            ((TableViewFor9V2) this.getChildAt(i)).initial();
        }
    }

    public void resetFlipper(long pageCount) {
        this.setDisplayedChild(0);
        this.setPageCount(pageCount);
        this.setCurrentPageNum(1);
        for (int i = 0; i < this.getChildCount(); i++) {
            ((TableViewFor9V2) this.getChildAt(i)).refresh();
        }
    }

    /**
     * 事件终端处理,但事件是滑动时，就把滑动事件按住，自己处理，但是点击的时候，就得分发到子View中去。
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                this.x = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        // 左滑，下一页
        if (x - motionEvent2.getX() > 100 && Math.abs(v) > 100) {
            if (this.currentPageNum >= this.pageCount) {
                Toast.makeText(this.getContext(), "已到达最后一页", Toast.LENGTH_SHORT).show();
                return true;
            }
                this.setInAnimation(this.inRightToLeft);
                this.setOutAnimation(this.outRightToLeft);
            this.showNext();
            this.currentPageNum++;
            this.setInAnimation(null);
            this.setOutAnimation(null);
            this.pageHandler.sendEmptyMessage(3);
        }
        //右滑，上一页
        else if (motionEvent2.getX() - x > 100 && Math.abs(v) > 100) {
            if (this.currentPageNum <= 1) {
                Toast.makeText(this.getContext(), "已到达第一页", Toast.LENGTH_SHORT).show();
                return true;
            }
                this.setInAnimation(this.inLeftToRight);
                this.setOutAnimation(this.outLeftToRight);
            this.showPrevious();
            this.currentPageNum--;
            this.setInAnimation(null);
            this.setOutAnimation(null);
            this.pageHandler.sendEmptyMessage(2);
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.gestureDetector.onTouchEvent(motionEvent);
    }

    public void setPageHandler(Handler pageHandler) {
        this.pageHandler = pageHandler;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }
}
