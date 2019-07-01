package dream.api.dmf.cn.dreaming.activity;

import android.content.Intent;
import android.widget.TextView;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;

public class SellNumActivity extends BaseMvpActivity<presenter> implements Contract.Iview {


    private TextView mReplay;
    private TextView mAmout;
    private TextView mprice;

    @Override
    public void getThisData() {

    }

    @Override
    public void getInitData() {
        mReplay = findViewById(R.id.reply);
        mAmout = findViewById(R.id.num);
        mprice = findViewById(R.id.prices);
        Intent intent = getIntent();
        String realpay = intent.getStringExtra("realpay");
        String amount = intent.getStringExtra("amount");
        String price = intent.getStringExtra("price");
        mReplay.setText(realpay);
        mAmout.setText(amount);
        mprice.setText(price);
    }

    @Override
    public int getView() {
        return R.layout.activity_sell_num;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {

    }
}
