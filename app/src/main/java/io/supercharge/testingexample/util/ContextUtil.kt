@file:JvmName("ContextUtil")

package io.supercharge.testingexample.util

import android.content.Context
import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Convenience variable to get an ApplicationContext
 */
var applicationContext: Context
    get() = ContextHolder.context
    set(value) {
        ContextHolder.context = value
    }

private object ContextHolder {
    var context by WeakReferenceDelegate()
}

private class WeakReferenceDelegate : ReadWriteProperty<ContextHolder, Context> {

    private var weakReference: WeakReference<Context>? = null

    override fun getValue(thisRef: ContextHolder, property: KProperty<*>): Context =
        weakReference?.get() ?: throw IllegalStateException("Application context must be set!")

    override fun setValue(thisRef: ContextHolder, property: KProperty<*>, value: Context) {
        weakReference = WeakReference(value)
    }
}
