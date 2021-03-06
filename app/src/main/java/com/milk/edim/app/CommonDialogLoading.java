package com.milk.edim.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-9-13
 * Time: 下午4:04
 */
public class CommonDialogLoading extends Dialog {
    Context context;

    ImageView vLoading; // 圆型进度条
    Animation anim;// 动画

    public CommonDialogLoading(Context context) {
        super(context, R.style.HKDialogLoading);
        this.context = context;
    }

    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.PROGRESS_VISIBILITY_ON);
        super.onCreate(savedInstanceState);

        anim = AnimationUtils.loadAnimation(this.getContext(),
                R.anim.rotate_repeat);
        anim.setInterpolator(new LinearInterpolator());

        vLoading = new ImageView(context);
        vLoading.setImageResource(R.drawable.load);// 加载中的图片,建议圆形的

        // 布局
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;

        addContentView(vLoading, lp);

        setCanceledOnTouchOutside(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 按下了键盘上返回按钮
            this.hide();
            return true;
        }
        return false;
    }

    @Override
    public void show() {
        try{
            super.show();
            vLoading.startAnimation(anim);
        }catch (Exception e){}
    }
}




