package dream.api.dmf.cn.dreaming.activity.mineactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BankBean;

public class WeChatActivity extends BaseMvpActivity<presenter> implements Contract.Iview {
    TextView mTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send)
    TextView tvSend;
    private String mUid;
    private String mShell;
    private String method = "wechat";
    private String mName;
    private String mCode;
    private SharedPreferences sharedPreferences;
    private String mName1;
    private String mNameCode;

    @Override
    public void getThisData() {

    }

    @Override
    public void getInitData() {
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        mName1 = sharedPreferences.getString(UserApi.WECHAT, "");
        mNameCode = sharedPreferences.getString(UserApi.WECHATCode, "");
        etName = findViewById(R.id.et_name);
        etCode= findViewById(R.id.et_code);
        etName.setText(mName1);
        etCode.setText(mNameCode);
        mTitle = findViewById(R.id.tv_title);
        mTitle.setText("微信");
        mTitle.setTextSize(16);
    }

    @Override
    public int getView() {
        return R.layout.activity_we_chat;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
        if (object instanceof BankBean) {
            BankBean bankBean = (BankBean) object;
            if (bankBean.error.equals("0")) {
                Toast.makeText(mContext, "绑定成功", Toast.LENGTH_LONG).show();

                finish();
            } else {
                Toast.makeText(mContext, bankBean.msg, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_img, R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_img:

                break;
            case R.id.tv_send:
                HashMap<String,Object> headmap=new HashMap<>();
                HashMap<String,Object> map=new HashMap<>();
                mName = etName.getText().toString().trim();
                mCode = etCode.getText().toString().trim();
                sharedPreferences.edit().putString(UserApi.WECHAT,mName).commit();
                sharedPreferences.edit().putString(UserApi.WECHATCode,mCode).commit();
                map.put("uid",mUid);
                map.put("shell",mShell);
                map.put("wechathm",mName);
                map.put("wechat",mCode);
                map.put("method",method);
                mPresenter.postData(UserApi.getBACK,headmap,map,BankBean.class);
                break;
        }
    }
}
