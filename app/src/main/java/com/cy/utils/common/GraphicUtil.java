package com.cy.utils.common;

import android.text.TextPaint;

/**
 * created by cy on 2020/1/16 0016.
 */
public final class GraphicUtil {
    /**
     * 描述：获取文字的像素宽.
     *
     * @param str the str
     * @param paint the paint
     * @return the string width
     */
    public static float getStringWidth(String str, TextPaint paint) {
        float strWidth  = paint.measureText(str);
        return strWidth;
    }
}
