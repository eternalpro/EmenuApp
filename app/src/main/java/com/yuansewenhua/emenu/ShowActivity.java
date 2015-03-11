package com.yuansewenhua.emenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.yuansewenhua.dao.DrinksDao;
import com.yuansewenhua.dao.FoodsDao;
import com.yuansewenhua.dao.Foodstype;
import com.yuansewenhua.dao.FoodstypeDao;
import com.yuansewenhua.emenu.customview.EMFlipper;
import com.yuansewenhua.emenu.customview.LeftButtons;
import com.yuansewenhua.emenu.customview.OrderReviewTextView;
import com.yuansewenhua.emenu.customview.PopWinCheckOrder;
import com.yuansewenhua.emenu.customview.TableCell;
import com.yuansewenhua.emenu.customview.TableViewFor9V2;
import com.yuansewenhua.listener.DataCellLongClickListener;
import com.yuansewenhua.listener.TableCellOnClickListener;
import com.yuansewenhua.utils.CommonUtils;
import com.yuansewenhua.utils.DBUtils;
import com.yuansewenhua.utils.NetUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public class ShowActivity extends Activity {

    private EMFlipper flipper;
    private TextView productionView;
    private LeftButtons leftButtons; //左侧所有按钮
    private FoodsDao foodsDao;
    private FoodstypeDao foodstypeDao;
    private DrinksDao drinksDao;

    private static final int PAGE_SIZE = 9; // 每页个数
    private int page = 1;   // 当前第几页
    private long pageCount; // 多少页
    //这个Handler用于分页和读取系统介绍
    private Handler mainHandler;

    private static final int FIRST_PAGE_MSG = 1;
    private static final int PRE_PAGE = 2;
    private static final int NEXT_PAGE = 3;
    private static final int PRODUCTION_SERVER = 4;


    // 界面初始化操作
    private void init() {
        // 初始化flipper和手势操作
        this.flipper = (EMFlipper) this.findViewById(R.id.flipper);
        this.productionView = (TextView) this.findViewById(R.id.rightsidetextview);
        OrderReviewTextView orderReviewTextView = (OrderReviewTextView) findViewById(R.id.orderreviewtextview);
        //设置所有单元格中的点击事件
        TableCellOnClickListener tableCellOnClickListener = new TableCellOnClickListener(orderReviewTextView);
        ((TableViewFor9V2) flipper.getChildAt(0)).setClickListener(tableCellOnClickListener);
        ((TableViewFor9V2) flipper.getChildAt(1)).setClickListener(tableCellOnClickListener);
        //装载左侧选菜按钮
        this.leftButtons = new LeftButtons(this);
        ((FrameLayout) this.findViewById(R.id.buttons)).addView(leftButtons);
        List<Foodstype> foodstypeList = this.foodstypeDao.queryBuilder().list();
        this.leftButtons.addFoodsTypeList(foodstypeList);
        this.leftButtons.setAllButtonsOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flipper.isFlipping()) {
                    return;
                }
                leftButtons.selectedButtonBefore();
                view.setClickable(false);
                ((Button) view).setTextColor(Color.RED);
                changeFlipperDataSource(((Button) view).getText().toString());
            }
        });

        this.flipper.setPageHandler(this.mainHandler);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //这个Handler用于分页和读取系统介绍
        mainHandler = new Handler() {
            // 在Handler中获取消息，重写handleMessage()方法
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case FIRST_PAGE_MSG:
                        flipper.resetFlipper(pageCount);
                        break;
                    case PRE_PAGE:
                        page--;
                        prePage();
                        break;
                    case NEXT_PAGE:
                        page++;
                        nextPage();
                        break;
                    case PRODUCTION_SERVER:
                        changeRightSideTextByServer(msg);
                        break;
                    default:
                        break;
                }
            }
        };
        // 初始化数据库查询
        foodsDao = DBUtils.getDaoSession(this).getFoodsDao();
        foodstypeDao = DBUtils.getDaoSession(this).getFoodstypeDao();
        drinksDao = DBUtils.getDaoSession(this).getDrinksDao();
        this.firstPageHandler("热菜");
        init();

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.getProductionFromServer();
    }

    /**
     * 初始化Flipper中的内容，包括创建表格等内容，速度较慢，
     * 所以单独创建线程，运行，完成后再更新UI，加快UI显示速度。
     */
    private void firstPageHandler(final String typeTitle) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                firstPagination(typeTitle, flipper);
                mainHandler.sendEmptyMessage(FIRST_PAGE_MSG);
            }
        });
    }

    /**
     * 种类切换效果
     *
     * @param typeTitle 分类名称
     */
    private void changeFlipperDataSource(final String typeTitle) {
        this.firstPagination(typeTitle, flipper);
        ObjectAnimator visToInvis = ObjectAnimator.ofFloat(this.flipper, "rotationX", 0f, 90f);
        final ObjectAnimator invisToVis = ObjectAnimator.ofFloat(flipper, "rotationX", -90f, 0f);
        visToInvis.setDuration(300);
        invisToVis.setDuration(300);
        visToInvis.setInterpolator(new DecelerateInterpolator());
        visToInvis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                flipper.resetFlipper(pageCount);
            }
        });
        invisToVis.setInterpolator(new AccelerateInterpolator());
        visToInvis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                invisToVis.start();

            }
        });
        visToInvis.start();
    }

    /**
     * 加载初次分页
     */
    private void firstPagination(String typeTitle, EMFlipper flipper) {
        this.pageCount = CommonUtils.calcPageCount(foodsDao, typeTitle);
        this.page = 1;
        //只加载前两页
        for (int i = 0; i < 2; i++) {
            TableViewFor9V2 flipperPage = (TableViewFor9V2) flipper.getChildAt(i);
            if (typeTitle.equals("酒水")) {
                flipperPage.setData(this.drinksDao
                        .queryBuilder()
                        .limit(PAGE_SIZE).offset(i * PAGE_SIZE)
                        .list());
            } else {
                flipperPage.setData(this.foodsDao
                        .queryBuilder()
                        .limit(PAGE_SIZE).offset(i * PAGE_SIZE)
                        .where(CommonUtils.getWhereConditionByType(typeTitle))
                        .list());
            }
        }
    }

    /**
     * 加载上一页
     */
    private void prePage() {
        if (page > 1) {
            List preData =
                    this.foodsDao
                            .queryBuilder()
                            .limit(PAGE_SIZE)
                            .offset((page - 2) * PAGE_SIZE)
                            .orderAsc(FoodsDao.Properties.Id)
                            .list();
            ((TableViewFor9V2) flipper.getChildAt(1)).setDataAndRefresh(preData);
        }
    }

    /**
     * 加载下一页
     */
    private void nextPage() {
        if (page < pageCount) {
            List nextData = this.foodsDao
                    .queryBuilder()
                    .limit(PAGE_SIZE)
                    .offset((page) * PAGE_SIZE)
                    .orderAsc(FoodsDao.Properties.Id)
                    .list();
            ((TableViewFor9V2) this.flipper.getChildAt(0)).setDataAndRefresh(nextData);
        }
    }

    /**
     * 从服务端获取自定义介绍信息
     */
    private void getProductionFromServer() {
        //这个方法中包含网络操作，从4.0以上版本，安卓禁止在主线程中调用网络连接方法，所以必须启用新线程。
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 声明一个面向句柄的消息类，这个消息将在获取服务器资源后送出。
                 * 每次OnStart时会启动这个线程，这样只要服务器数据库中的值有变更，Pad端也会同样改变。
                 */
                Message message = Message.obtain();
                //消息类型为外部声明的常量
                message.what = PRODUCTION_SERVER;
                try {
                    //勒令服务端从数据库中system表中取得production的值，然后把这个值装入消息。
                    String productionText = NetUtils.getProductionFromServer(ShowActivity.this, "http://192.168.1.160:9999/emenu/system/key/introduction");
                    message.obj = productionText;
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    //当经历异常情况，把这个异常放入消息。
                    e.printStackTrace();
                    message.obj = e;
                }
                //发送消息。
                mainHandler.sendMessage(message);
            }
        }).start();
    }

    private void changeRightSideTextByServer(Message msg) {
        if (msg.obj instanceof TimeoutException) {
            Toast.makeText(ShowActivity.this, "等待服务器数据超时，请检查网络环境", Toast.LENGTH_SHORT).show();
        } else if (msg.obj instanceof Exception) {
            Toast.makeText(ShowActivity.this, "无法与服务器取得连接，请检查网络环境", Toast.LENGTH_SHORT).show();
        } else {
            productionView.setText((String) msg.obj);
            productionView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            productionView.setTypeface(CommonUtils.getCustomTypeface(ShowActivity.this, "emenu.ttf"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_language_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
