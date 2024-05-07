package ru.je_dog.effective_mobile.test.core.feature.ext

import android.annotation.SuppressLint
import android.text.InputFilter
import android.text.Spanned
import android.view.MotionEvent
import android.widget.EditText

@SuppressLint("ClickableViewAccessibility")
fun EditText.setOnEndDrawableClickListener(action: () -> Unit){
    setOnTouchListener { _, event ->
        val rightDrawable = 2;

        if(event.action == MotionEvent.ACTION_UP) {
            if(event.rawX >= (right - compoundDrawables[rightDrawable].bounds.width())) {
                action()
                return@setOnTouchListener true
            }
        }

        false
    }
}

fun EditText.setInputFilterByRegex(
    regex: Regex = Regex("[А-Яа-я -]")
){
    val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
        val result = StringBuilder()

        source?.forEach {
            if (regex.matches(it.toString())){
                result.append(it)
            }
        }

        result.toString()
    }

    filters = arrayOf(inputFilter)
}