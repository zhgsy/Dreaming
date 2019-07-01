package dream.api.dmf.cn.dreaming.activity.mineactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.LoadShareBean;
import dream.api.dmf.cn.dreaming.utils.BitmapUtil;
import dream.api.dmf.cn.dreaming.utils.ZXingUtils;

public class LoadDownActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.ivImg)
    ImageView ivImg;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private SharedPreferences sharedPreferences;
    private String mUid;
    private String mShell;
    private TextView mUl;

    @Override
    public void getThisData() {
        HashMap<String, Object> headmap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", mUid);
        map.put("shell", mShell);
        mPresenter.postData(UserApi.getTuiShare, headmap, map, LoadShareBean.class);
    }

    @Override
    public void getInitData() {
        mUl = findViewById(R.id.t_url);
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        findViewById(R.id.iv_back);

    }

    @Override
    public int getView() {
        return R.layout.activity_load_down;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
        if (object instanceof LoadShareBean) {
            LoadShareBean loadShareBean = (LoadShareBean) object;
            Bitmap qrImage = ZXingUtils.createQRImage(loadShareBean.url, 180, 180);
            qrImage.extractAlpha();
            ivImg.setImageBitmap(qrImage);
            mUl.setText(loadShareBean.url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    } private void shareBitmap() {
//        /** * 分享图片 */
        Bitmap bitmap =BitmapUtil.createBitmap(rl);
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("image/*");  //设置分享内容的类型
        share_intent.putExtra(Intent.EXTRA_STREAM, BitmapUtil.saveImageToGallery(mContext,bitmap));
        //创建分享的Dialog
        share_intent = Intent.createChooser(share_intent, "我要推广");
        startActivity(share_intent);

    }


    @OnClick({R.id.iv_back, R.id.iv_share, R.id.ivImg, R.id.rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                shareBitmap();
                break;

        }
    }
}
