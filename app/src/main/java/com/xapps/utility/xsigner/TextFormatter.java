package com.xapps.utility.xsigner;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.graphics.Typeface;

public class TextFormatter {
    
    public static SpannableStringBuilder formatText(String input) {
        return formatText(input, null, null);
    }

    public static SpannableStringBuilder formatText(String input, String partToFormat, String style) {
        SpannableStringBuilder formattedText = new SpannableStringBuilder(input);
        
        if (partToFormat != null && style != null) {
            int startIndex = formattedText.toString().indexOf(partToFormat);
            if (startIndex != -1) {
                int endIndex = startIndex + partToFormat.length();

                if (style.startsWith("bold")) {
                    formattedText.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("italic")) {
                    formattedText.setSpan(new StyleSpan(Typeface.ITALIC), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("underline")) {
                    formattedText.setSpan(new UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("!C#")) {
                    int color = parseColorHex(style.substring(3));
                    formattedText.setSpan(new ForegroundColorSpan(color), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("BC#")) {
                    int boldColor = parseColorHex(style.substring(3));
                    formattedText.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    formattedText.setSpan(new ForegroundColorSpan(boldColor), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        return formattedText;
    }

    public static SpannableStringBuilder formatText(String input, String partToFormat, String style, int color) {
        SpannableStringBuilder formattedText = new SpannableStringBuilder(input);
        
        if (partToFormat != null && style != null) {
            int startIndex = formattedText.toString().indexOf(partToFormat);
            if (startIndex != -1) {
                int endIndex = startIndex + partToFormat.length();

                if (style.startsWith("bold")) {
                    formattedText.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("italic")) {
                    formattedText.setSpan(new StyleSpan(Typeface.ITALIC), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("underline")) {
                    formattedText.setSpan(new UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("!C#")) {
                    int colorish = color;
                    formattedText.setSpan(new ForegroundColorSpan(colorish), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("BC")) {
                    int boldColor = color;
                    formattedText.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    formattedText.setSpan(new ForegroundColorSpan(boldColor), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        return formattedText;
    }
    
    public static SpannableStringBuilder formatText(SpannableStringBuilder input, String partToFormat, String style, int color) {
        SpannableStringBuilder formattedText = new SpannableStringBuilder(input);
        
        if (partToFormat != null && style != null) {
            int startIndex = formattedText.toString().indexOf(partToFormat);
            if (startIndex != -1) {
                int endIndex = startIndex + partToFormat.length();

                if (style.startsWith("bold")) {
                    formattedText.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("italic")) {
                    formattedText.setSpan(new StyleSpan(Typeface.ITALIC), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("underline")) {
                    formattedText.setSpan(new UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("!C#")) {
                    int colorish = color;
                    formattedText.setSpan(new ForegroundColorSpan(colorish), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (style.startsWith("BC")) {
                    int boldColor = color;
                    formattedText.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    formattedText.setSpan(new ForegroundColorSpan(boldColor), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        return formattedText;
    }

    private static int parseColorHex(String colorHex) {
        if (colorHex.length() == 6) {
            try {
                return Color.parseColor("#" + colorHex);
            } catch (IllegalArgumentException e) {
                // Handle invalid color hex value
                return Color.BLACK;
            }
        } else {
            // Handle invalid color hex length
            return Color.BLACK;
        }
    }
}