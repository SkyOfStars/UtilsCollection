package com.cy.utils.common;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * cy
 */
public final class TextUtil {

    private TextUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 修改文本字体颜色
     *
     * @param context
     * @param textView
     * @param text
     */
    public static void setTextColor(Context context, TextView textView, String text, int color, int start, int end) {

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(color), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableStringBuilder);
    }


    /**
     * TextView字体是否加粗
     *
     * @param view
     * @param isBold
     */
    public static void setTextBold(TextView view, boolean isBold) {
        if (isBold) {
            view.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//粗体
        } else {
            view.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//正常
        }


    }

    /**
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        return ((str == null) || (str.length() == 0));
    }

    /**
     * 字符串的值是否有效
     *
     * @param s
     * @return
     */
    public static boolean textIsValid(String s) {
        if (null != s && s.trim().length() != 0) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * @param params
     * @return
     */
    public static boolean paramsIsValid(String... params) {
        for (String param : params) {
            if (!textIsValid(param)) {
                return false;
            }
        }
        return true;
    }


}
