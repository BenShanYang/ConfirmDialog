package com.yk.confirmalert;

import android.app.Dialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ClassName: ConfirmDialog
 * @Description: 确认弹窗
 * @Author: YangKuan
 * @Date: 2021/3/30 18:11
 */
public abstract class ConfirmDialog extends Dialog implements View.OnClickListener {
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
        findViewById(R.id.tv_confirm).setOnClickListener(ConfirmDialog.this);
        findViewById(R.id.tv_cancel).setOnClickListener(ConfirmDialog.this);
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
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_confirm) {
            //确认
            onConfirm(ConfirmDialog.this);
        } else if (id == R.id.tv_cancel) {
            //取消
            if (isShowing()) {
                dismiss();
            }
        }
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
    public ConfirmDialog setContentTextColor(@ColorInt int color) {
        TextView view = findViewById(R.id.tv_content);
        view.setTextColor(color);
        return ConfirmDialog.this;
    }

    public abstract void onConfirm(ConfirmDialog dialog);

}
