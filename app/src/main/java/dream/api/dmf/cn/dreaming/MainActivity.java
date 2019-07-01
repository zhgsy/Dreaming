package dream.api.dmf.cn.dreaming;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.ButterKnife;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.fragment.CenterFragment;
import dream.api.dmf.cn.dreaming.fragment.HomeFragment;
import dream.api.dmf.cn.dreaming.fragment.MineFragment;
import dream.api.dmf.cn.dreaming.fragment.RewardFragment;
import dream.api.dmf.cn.dreaming.fragment.TradingFragment;

public class MainActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

   private BottomTabBar mTabbar;
    private SharedPreferences sharedPreferences;
    private String usern;

    @Override
    public int getView() {

        return R.layout.activity_main;
    }


    @Override
    protected presenter createP() {
        return new presenter();
    }


    @Override
    public void getData(Object object) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getThisData() {

    }

    @Override
    public void getInitData() {

        mTabbar = findViewById(R.id.mfragment);
        mTabbar.init(getSupportFragmentManager())
                .setImgSize(50, 50)
                .setFontSize(8)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("首页", R.drawable.hoem_butn, HomeFragment.class)
                .addTabItem("钱包", R.drawable.trading_butn, TradingFragment.class)
                .addTabItem("商城", R.drawable.center_butn, CenterFragment.class)
                .addTabItem("奖励", R.drawable.reward_butn, RewardFragment.class)
                .addTabItem("我的", R.drawable.mine_butn, MineFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
    }
    @Override
    public void onBackPressed() {
        exit();
    }
    private long exitTime = 0;
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(mContext, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }


}
