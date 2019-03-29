package io.supercharge.testingexample.binding

import android.widget.Button
import androidx.databinding.BindingAdapter

@BindingAdapter("runnable")
fun bindRunnableToButton(view: Button, runnable: Runnable) {
    view.setOnClickListener { runnable.run() }
}