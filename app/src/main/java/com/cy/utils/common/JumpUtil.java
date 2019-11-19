package com.cy.utils.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.longmao.zhuawawa.R;
import com.longmao.zhuawawa.bean.WXloginDoBean;
import com.longmao.zhuawawa.logic.StatusManager;
import com.longmao.zhuawawa.ui.HomeActivity;
import com.longmao.zhuawawa.ui.PlayActivity;
import com.longmao.zhuawawa.ui.WebViewActivity;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class JumpUtil {
    private static Intent intent;

    public static Intent getJumpIntent(Context context, String uri) {
        LogUtil.i("JumpUtil getDataString==" + uri);
        if (TextUtils.isEmpty(uri)) {
            intent = new Intent(context, HomeActivity.class);
            return intent;
        }

        return intent;
    }
}
