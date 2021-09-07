package com.yk.confirmalert;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @ClassName: ConfirmDialog
 * @Description: 确认弹窗
 * @Author: YangKuan
 * @Date: 2021/3/30 18:11
 */
public abstract class ConfirmDialog extends Dialog implements View.OnClickListener {

    private TextView tvConfirm;//确认按钮
    private TextView tvCancel;//取消按钮

    public ConfirmDialog(@NonNull Context context) {
        super(context, R.style.AlphaBaseDialogTheme);
        init();
    }

    public ConfirmDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected ConfirmDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_confirm);
        windowConfig();
        tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);

        tvConfirm.setOnClickListener(ConfirmDialog.this);
        tvCancel.setOnClickListener(ConfirmDialog.this);
    }

    private void windowConfig() {
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.BaseDialogWindowAnim);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
            window.setGravity(Gravity.CENTER);
        }
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        try {
            if (cancel) {
                findViewById(R.id.fl_container).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
            } else {
                findViewById(R.id.fl_container).setOnClickListener(null);
            }
        } catch (Exception e) {

        }
        super.setCanceledOnTouchOutside(cancel);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_confirm) {
            //确认
            onConfirm(ConfirmDialog.this);
        } else if (id == R.id.tv_cancel) {
            //取消
            onCancel(ConfirmDialog.this);
        }
    }

    /**
     * 点击弹窗区域外弹窗是否消失
     *
     * @param cancel true设置为点击区域外外弹消失、false设置为点击区域外弹窗不消失
     * @return
     */
    public ConfirmDialog setCanceledOutside(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        return ConfirmDialog.this;
    }

    /**
     * 设置背景样式
     *
     * @param background 背景样式
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ConfirmDialog setBackground(Drawable background) {
        findViewById(R.id.fl_container).setBackground(background);
        return ConfirmDialog.this;
    }

    /**
     * 设置背景样式
     *
     * @param resid 资源id
     * @return
     */
    public ConfirmDialog setBackgroundResource(@DrawableRes int resid) {
        findViewById(R.id.fl_container).setBackgroundResource(resid);
        return ConfirmDialog.this;
    }

    /**
     * 设置背景色
     *
     * @param color 色值
     * @return
     */
    public ConfirmDialog setBackgroundColor(@ColorInt int color) {
        findViewById(R.id.fl_container).setBackgroundColor(color);
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗的基准标高 阴影
     *
     * @param elevation 高度(单位px)
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ConfirmDialog setElevation(float elevation) {
        findViewById(R.id.fl_container).setElevation(elevation);
        return ConfirmDialog.this;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return 返回当前弹窗对象
     */
    public ConfirmDialog setDialogTitle(CharSequence title) {
        TextView view = findViewById(R.id.tv_title);
        if (view != null && title != null) {
            view.setText(title);
        }
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗标题文字字号
     *
     * @param dip 文字字号,单位dip
     * @return
     */
    public ConfirmDialog setDialogTitleSize(float dip) {
        TextView view = findViewById(R.id.tv_title);
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dip);
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗标题文字字号
     *
     * @param unit 字号的单位
     * @param size 字号
     * @return
     */
    public ConfirmDialog setDialogTitleSize(int unit, float size) {
        TextView view = findViewById(R.id.tv_title);
        view.setTextSize(unit, size);
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗标题文字颜色
     *
     * @param color 色值
     * @return
     */
    public ConfirmDialog setDialogTitleColor(@ColorInt int color) {
        TextView view = findViewById(R.id.tv_title);
        view.setTextColor(color);
        return ConfirmDialog.this;
    }

    /**
     * 设置提示内容
     *
     * @param text 提示内容
     * @return 返回当前弹窗对象
     */
    public ConfirmDialog setDialogContent(CharSequence text) {
        TextView view = findViewById(R.id.tv_content);
        if (view != null && text != null) {
            view.setText(text);
        }
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗内容文字字号
     *
     * @param dip 文字字号,单位dip
     * @return
     */
    public ConfirmDialog setDialogContentSize(float dip) {
        TextView view = findViewById(R.id.tv_content);
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dip);
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗内容文字字号
     *
     * @param unit 字号的单位
     * @param size 字号
     * @return
     */
    public ConfirmDialog setDialogContentSize(int unit, float size) {
        TextView view = findViewById(R.id.tv_title);
        view.setTextSize(unit, size);
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗内容文字颜色
     *
     * @param color 色值
     * @return
     */
    public ConfirmDialog setDialogContentColor(@ColorInt int color) {
        TextView view = findViewById(R.id.tv_content);
        view.setTextColor(color);
        return ConfirmDialog.this;
    }

    /**
     * 设置取消按钮的文字
     *
     * @param text 取消按钮文字
     * @return
     */
    public ConfirmDialog setCancelText(CharSequence text) {
        if (tvCancel != null) {
            tvCancel.setText(text);
        }
        return ConfirmDialog.this;
    }

    /**
     * 设置取消按钮的文字颜色
     *
     * @param color 取消按钮文字颜色
     * @return
     */
    public ConfirmDialog setCancelTextColor(@ColorInt int color) {
        if (tvCancel != null) {
            tvCancel.setTextColor(color);
        }
        return ConfirmDialog.this;
    }

    /**
     * 设置取消按钮的文字字号
     *
     * @param size 字号(单位dip)
     * @return
     */
    public ConfirmDialog setCancelTextSize(float size) {
        if (tvCancel != null) {
            tvCancel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        }
        return ConfirmDialog.this;
    }

    /**
     * 设置取消按钮的文字字号
     *
     * @param unit 字号的单位
     * @param size 字号
     * @return
     */
    public ConfirmDialog setCancelTextSize(int unit, float size) {
        if (tvCancel != null) {
            tvCancel.setTextSize(unit, size);
        }
        return ConfirmDialog.this;
    }

    /**
     * 设置确认按钮的文字
     *
     * @param text 确认按钮文字
     * @return
     */
    public ConfirmDialog setConfirmText(CharSequence text) {
        if (tvConfirm != null) {
            tvConfirm.setText(text);
        }
        return ConfirmDialog.this;
    }

    /**
     * 设置确定按钮文字字号
     *
     * @param size 字号(单位dip)
     * @return
     */
    public ConfirmDialog setConfirmTextSize(float size) {
        if (tvConfirm != null) {
            tvConfirm.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        }
        return ConfirmDialog.this;
    }

    /**
     * 设置确定按钮文字字号
     *
     * @param unit 字号的单位
     * @param size 字号
     * @return
     */
    public ConfirmDialog setConfirmTextSize(int unit, float size) {
        if (tvConfirm != null) {
            tvConfirm.setTextSize(unit, size);
        }
        return ConfirmDialog.this;
    }

    /**
     * 设置确认按钮的文字颜色
     *
     * @param color 确认按钮文字颜色
     * @return
     */
    public ConfirmDialog setConfirmTextColor(@ColorInt int color) {
        if (tvConfirm != null) {
            tvConfirm.setTextColor(color);
        }
        return ConfirmDialog.this;
    }

    /**
     * 确认按钮回调事件
     *
     * @param dialog 弹窗对象
     */
    public void onConfirm(ConfirmDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 取消按钮点击事件
     *
     * @param dialog 弹窗对象
     */
    public void onCancel(ConfirmDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    /* --------------------- 过时方法 --------------------- */

    /**
     * 设置弹窗标题文字字号
     *
     * @param dip 文字字号,单位dip
     * @return
     */
    @Deprecated
    public ConfirmDialog setTitleTextSize(float dip) {
        TextView view = findViewById(R.id.tv_title);
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dip);
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗标题文字字号
     *
     * @param unit 字号的单位
     * @param size 字号
     * @return
     */
    @Deprecated
    public ConfirmDialog setTitleTextSize(int unit, float size) {
        TextView view = findViewById(R.id.tv_title);
        view.setTextSize(unit, size);
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗标题文字颜色
     *
     * @param color 色值
     * @return
     */
    @Deprecated
    public ConfirmDialog setTitleTextColor(@ColorInt int color) {
        TextView view = findViewById(R.id.tv_title);
        view.setTextColor(color);
        return ConfirmDialog.this;
    }

    /**
     * 设置提示内容
     *
     * @param text 提示内容
     * @return 返回当前弹窗对象
     */
    @Deprecated
    public ConfirmDialog setContent(CharSequence text) {
        TextView view = findViewById(R.id.tv_content);
        if (view != null && text != null) {
            view.setText(text);
        }
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗内容文字字号
     *
     * @param dip 文字字号,单位dip
     * @return
     */
    @Deprecated
    public ConfirmDialog setContentTextSize(float dip) {
        TextView view = findViewById(R.id.tv_content);
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dip);
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗内容文字字号
     *
     * @param unit 字号的单位
     * @param size 字号
     * @return
     */
    @Deprecated
    public ConfirmDialog setContentTextSize(int unit, float size) {
        TextView view = findViewById(R.id.tv_title);
        view.setTextSize(unit, size);
        return ConfirmDialog.this;
    }

    /**
     * 设置弹窗内容文字颜色
     *
     * @param color 色值
     * @return
     */
    @Deprecated
    public ConfirmDialog setContentTextColor(@ColorInt int color) {
        TextView view = findViewById(R.id.tv_content);
        view.setTextColor(color);
        return ConfirmDialog.this;
    }

}
