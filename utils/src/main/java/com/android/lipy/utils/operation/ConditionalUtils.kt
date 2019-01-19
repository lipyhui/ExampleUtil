package com.android.lipy.utils.operation

/**
 * Judging parameters according to conditions
 * include [allNotNull]、 [anyNull]、 [allTrue]、 [anyTrue]、 [allFalse]、 [anyFalse]
 */
@Suppress("UNUSED")
object ConditionalUtils {
    /**
     * Return true if all variables are not NULL
     */
    fun <T> allNotNull(vararg values: T?): Boolean? {
        return values.all { it != null }
    }

    /**
     * Call action if all variables are not NULL
     */
    fun <T, R> allNotNull(vararg values: T?, action: (Array<out T?>) -> R?): R? {
        return if (values.all { it != null }) return action(values) else null
    }

    /**
     * Call action if all variables are not NULL, Prevent Java action recognition failure
     */
    fun <T, R> allNotNull(action: (Array<out T?>) -> R?, vararg values: T?): R? {
        return if (values.all { it != null }) return action(values) else null
    }

    /**
     * Return true if any variable is NULL
     */
    fun <T> anyNull(vararg values: T?): Boolean? {
        return values.any { it == null }
    }

    /**
     * Call action if any variable is NULL
     */
    fun <T, R> anyNull(vararg values: T?, action: () -> R?): R? {
        return if (values.any { it == null }) action() else null
    }

    /**
     * Call action if any variable is NULL, Prevent Java action recognition failure
     */
    fun <T, R> anyNull(action: () -> R?, vararg values: T?): R? {
        return if (values.any { it == null }) action() else null
    }

    /**
     * Return true if all variables are true
     */
    fun allTrue(vararg values: Boolean?): Boolean? {
        return values.all { it == true }
    }

    /**
     * Call action if all variables are true
     */
    fun <T, R> allTrue(vararg values: T?, action: () -> R?): R? {
        return if (values.any { it == true }) action() else null
    }

    /**
     * Call action if all variables are true, Prevent Java action recognition failure
     */
    fun <T, R> allTrue(action: () -> R?, vararg values: T?): R? {
        return if (values.any { it == true }) action() else null
    }

    /**
     * Return true if any variable is true
     */
    fun <T> anyTrue(vararg values: T?): Boolean? {
        return values.any { it == true }
    }

    /**
     * Call action if any variable is true
     */
    fun <T, R> anyTrue(vararg values: T?, action: () -> R?): R? {
        return if (values.any { it == true }) action() else null
    }

    /**
     * Call action if any variable is true, Prevent Java action recognition failure
     */
    fun <T, R> anyTrue(action: () -> R?, vararg values: T?): R? {
        return if (values.any { it == true }) action() else null
    }

    /**
     * Return true if all variables are false
     */
    fun allFalse(vararg values: Boolean?): Boolean? {
        return values.all { it == false }
    }

    /**
     * Call action if all variables are false
     */
    fun <T, R> allFalse(vararg values: T?, action: () -> R?): R? {
        return if (values.any { it == false }) action() else null
    }

    /**
     * Call action if all variables are false, Prevent Java action recognition failure
     */
    fun <T, R> allFalse(action: () -> R?, vararg values: T?): R? {
        return if (values.any { it == false }) action() else null
    }

    /**
     * Return true if any variable is false
     */
    fun <T> anyFalse(vararg values: T?): Boolean? {
        return values.any { it == false }
    }

    /**
     * Call action if any variable is false
     */
    fun <T, R> anyFalse(vararg values: T?, action: () -> R?): R? {
        return if (values.any { it == false }) action() else null
    }

    /**
     * Call action if any variable is false, Prevent Java action recognition failure
     */
    fun <T, R> anyFalse(action: () -> R?, vararg values: T?): R? {
        return if (values.any { it == false }) action() else null
    }
}