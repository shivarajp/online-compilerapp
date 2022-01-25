package com.masai.onlinecompiler.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import java.util.HashMap
import java.util.regex.Pattern


class CodeEditText(context: Context?, attrs: AttributeSet?) :
    AppCompatEditText(context!!, attrs) {
    // add line number
    private val rect: Rect = Rect()
    private val paint: Paint = Paint()

    override fun onDraw(canvas: Canvas) {
        var baseline = baseline //
        for (i in 0 until 1) {
            canvas.drawText("" + (i + 1), rect.left.toFloat(), baseline.toFloat(), paint)
            baseline += lineHeight
        }
        super.onDraw(canvas)
    }

    companion object {
        //high light key words
        private val PATTERN_NUMBERS = Pattern.compile(
            "\\b(\\d*[.]?\\d+)\\b"
        )
        private val PATTERN_PREPROCESSOR = Pattern.compile(
            "^[\t ]*(#define|#undef|#if|#ifdef|#ifndef|#else|#elif|#endif|" +
                    "#error|#pragma|#extension|#version|#line|#include)\\b",
            Pattern.MULTILINE
        )
        private val PATTERN_KEYWORDS = Pattern.compile(
            "\\b(" +
                    "and|del|from|not|while|as|elif|global|or|with|assert|else|if|pass|yield|break|" +
                    "except|import|print|class|exec|in|raise|continue|finally|is|return|def|for|lambda|try" +
                    ")\\b"
        )
        private val PATTERN_BUILTINS = Pattern.compile(
            ("\\b(radians|degrees|sin|cos|tan|asin|acos|atan|pow|" +
                    "exp|log|sqrt|inversesqrt|abs|sign|floor|ceil|fract|mod|" +
                    "min|max|length|Math|System|out|printf|print|println|" +
                    "console|Arrays|Array|vector|List|list|ArrayList|Map|HashMap|" +
                    "dict|java|util|lang|import|from|in|charset|lang|href|name|" +
                    "target|onclick|onmouseover|onmouseout|accesskey|code|codebase|" +
                    "width|height|align|vspace|hspace|border|name|archive|mayscript|" +
                    "alt|shape|coords|target|nohref|size|color|face|src|loop|bgcolor|" +
                    "background|text|vlink|alink|bgproperties|topmargin|leftmargin|" +
                    "marginheight|marginwidth|onload|onunload|onfocus|onblur|stylesrc|" +
                    "scroll|clear|type|value|valign|span|compact|pluginspage|pluginurl|" +
                    "hidden|autostart|playcount|volume|controls|controller|mastersound|" +
                    "starttime|endtime|point-size|weight|action|method|enctype|onsubmit|" +
                    "onreset|scrolling|noresize|frameborder|bordercolor|cols|rows|" +
                    "framespacing|border|noshade|longdesc|ismap|usemap|lowsrc|naturalsizeflag|" +
                    "nosave|dynsrc|controls|start|suppress|maxlength|checked|language|onchange|" +
                    "onkeypress|onkeyup|onkeydown|autocomplete|prompt|for|rel|rev|media|direction|" +
                    "behaviour|scrolldelay|scrollamount|http-equiv|content|gutter|defer|event|" +
                    "multiple|readonly|cellpadding|cellspacing|rules|bordercolorlight|" +
                    "bordercolordark|summary|colspan|rowspan|nowrap|halign|disabled|accesskey|" +
                    "tabindex|id)\\b")
        )
        private val PATTERN_COMMENTS = Pattern.compile(
            "/\\*(?:.|[\\n\\r])*?\\*/|//.*"
        )

        fun setHighLight(str: String?): SpannableString {
            val ss = SpannableString(str)
            val ptn_color: MutableMap<Pattern, String> = HashMap()
            ptn_color[PATTERN_COMMENTS] = "#076421"
            ptn_color[PATTERN_NUMBERS] = "#c29810"
            ptn_color[PATTERN_KEYWORDS] = "#781ebe"
            //ptn_color.put(PATTERN_BUILTINS, "#0a0733");
            ptn_color[PATTERN_PREPROCESSOR] = "#7c4204"
            for (ptn: Pattern in ptn_color.keys) {
                val colorStr = ptn_color[ptn]
                val matcher = ptn.matcher(ss)
                while (matcher.find()) {
                    ss.setSpan(
                        ForegroundColorSpan(Color.parseColor(colorStr)),
                        matcher.start(),
                        matcher.end(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
            return ss
        }
    }

    init {
        paint.style = Paint.Style.FILL
        paint.color = Color.GRAY
        paint.textSize = 30f
    }
}