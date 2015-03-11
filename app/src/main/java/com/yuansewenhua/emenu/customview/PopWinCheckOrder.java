package com.yuansewenhua.emenu.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yuansewenhua.adapter.OrderReviewAdapter;
import com.yuansewenhua.dao.Drinks;
import com.yuansewenhua.dao.Foods;
import com.yuansewenhua.dto.GoodsForOrder;
import com.yuansewenhua.emenu.R;
import com.yuansewenhua.utils.NetUtils;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionInitializer;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

/**
 * Created by YangJD on 2014/12/15.
 */
public class PopWinCheckOrder extends PopupWindow implements View.OnClickListener {

    private Context context;
    private List<GoodsForOrder> orderList;
    private View contentView;
    private TextView title;
    private ImageView btnCheckOrder;
    private ImageView btnClosePopwin;
    private ImageView btnSendOrder;
    private ObjectAnimator visToInvis;
    private ObjectAnimator invisToVis;

    public PopWinCheckOrder(Context context, List<GoodsForOrder> orderList) {
        super(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.context = context;
        contentView = LayoutInflater.from(context).inflate(R.layout.popwin_order, null);
        this.setContentView(contentView);
        this.orderList = orderList;
        ListView orderListView = (ListView) contentView.findViewById(R.id.orderlistview);
        title = (TextView) contentView.findViewById(R.id.poptitle);
        this.btnCheckOrder = (ImageView) contentView.findViewById(R.id.btnCheckOrderOK);
        this.btnSendOrder = (ImageView) contentView.findViewById(R.id.btnSendOrder);
        this.btnCheckOrder.setOnClickListener(this);
        this.btnClosePopwin = (ImageView)contentView.findViewById(R.id.btn_closepopwin);
        OrderReviewAdapter adapter = new OrderReviewAdapter(this.context);
        adapter.setData(this.orderList);
        orderListView.setAdapter(adapter);
        this.btnSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NioSocketConnector socketConnector = new NioSocketConnector();
                socketConnector.setHandler(new NetUtils());
                ConnectFuture connectFuture = socketConnector.connect(new InetSocketAddress("192.168.31.244",8899));
                connectFuture.awaitUninterruptibly();
                connectFuture.getSession().getCloseFuture().awaitUninterruptibly();
                socketConnector.dispose();
            }
        });
        this.btnClosePopwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopWinCheckOrder.this.dismiss();
            }
        });
    }

    public void calcCount() {
        int i = 0;
        for (GoodsForOrder g : orderList) {
            i += g.getCount();
        }
        title.setText("共 计  " + i + "  道 菜 品");
    }

    @Override
    public void onClick(final View view) {
        //翻转画面显示发送订单画面
        this.visToInvis = ObjectAnimator.ofFloat(this.contentView,"rotationY",0f,90f);
        this.invisToVis = ObjectAnimator.ofFloat(this.contentView,"rotationY",-90f,0f);
        this.visToInvis.setDuration(300);
        this.invisToVis.setDuration(300);
        this.visToInvis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                contentView.findViewById(R.id.popwin_checkorder).setVisibility(View.INVISIBLE);
                contentView.findViewById(R.id.popwin_sendorder).setVisibility(View.VISIBLE);
                contentView.findViewById(R.id.popwin_sendorder).requestFocus();
                invisToVis.start();
            }
        });
        this.visToInvis.start();

    }

    public static void main(String[] args){
        NioSocketConnector socketConnector = new NioSocketConnector();
        DefaultIoFilterChainBuilder chain = socketConnector.getFilterChain();
        ProtocolCodecFilter filter = new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
        chain.addLast("objectFilter",filter);
        socketConnector.setHandler(new NetUtils());
        ConnectFuture connectFuture = socketConnector.connect(new InetSocketAddress("192.168.31.244",8899));
        connectFuture.awaitUninterruptibly();
        connectFuture.getSession().getCloseFuture().awaitUninterruptibly();
        socketConnector.dispose();
    }
}
