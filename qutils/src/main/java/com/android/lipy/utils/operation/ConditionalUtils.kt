package com.android.lipy.utils.operation

/**
 * Judging parameters according to conditions
 * include [allNotNull]、 [anyNull]、 [allTrue]、 [anyTrue]、 [allFalse]、 [anyFalse]
 */
@Suppress("UNUSED", "MemberVisibilityCanBePrivate")
object ConditionalUtils {
    /**
     * Return true if all variables are not NULL
     */
    fun <T> allNotNull(vararg values: T?): Boolean {
        return values.all { it != null }
    }

    /**
     * Call action if all variables are not NULL
     */
    fun <T, R> allNotNull(vararg values: T?, action: (List<T>) -> R?): R? {
        val list = ArrayList<T>()
        values.forEach {
            if (it == null) {
                return null
            }

            list.add(it)
        }

        return action(list)
    }

    /**
     * Call action if all variables are not NULL, Prevent Java action recognition failure
     */
    fun <T, R> allNotNull(action: (List<T>) -> R?, vararg values: T?): R? {
        val list = ArrayList<T>()
        values.forEach {
            if (it == null) {
                return null
            }

            list.add(it)
        }

        return action(list)
    }

    /**
     * Return true if any variable is NULL
     */
    fun <T> anyNull(vararg values: T?): Boolean {
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
    fun allTrue(vararg values: Boolean): Boolean {
        return values.all { it }
    }

    /**
     * Call action if all variables are true
     */
    fun <R> allTrue(vararg values: Boolean, action: () -> R?): R? {
        return if (values.any { it }) action() else null
    }

    /**
     * Call action if all variables are true, Prevent Java action recognition failure
     */
    fun <R> allTrue(action: () -> R?, vararg values: Boolean): R? {
        return if (values.any { it }) action() else null
    }

    /**
     * Return true if any variable is true
     */
    fun anyTrue(vararg values: Boolean): Boolean {
        return values.any { it }
    }

    /**
     * Call action if any variable is true
     */
    fun <R> anyTrue(vararg values: Boolean, action: () -> R?): R? {
        return if (values.any { it }) action() else null
    }

    /**
     * Call action if any variable is true, Prevent Java action recognition failure
     */
    fun <R> anyTrue(action: () -> R?, vararg values: Boolean): R? {
        return if (values.any { it }) action() else null
    }

    /**
     * Return true if all variables are false
     */
    fun allFalse(vararg values: Boolean): Boolean {
        return values.all { !it }
    }

    /**
     * Call action if all variables are false
     */
    fun <R> allFalse(vararg values: Boolean, action: () -> R?): R? {
        return if (values.any { !it }) action() else null
    }

    /**
     * Call action if all variables are false, Prevent Java action recognition failure
     */
    fun <R> allFalse(action: () -> R?, vararg values: Boolean): R? {
        return if (values.any { !it }) action() else null
    }

    /**
     * Return true if any variable is false
     */
    fun anyFalse(vararg values: Boolean): Boolean {
        return values.any { !it }
    }

    /**
     * Call action if any variable is false
     */
    fun <R> anyFalse(vararg values: Boolean, action: () -> R?): R? {
        return if (values.any { !it }) action() else null
    }

    /**
     * Call action if any variable is false, Prevent Java action recognition failure
     */
    fun <R> anyFalse(action: () -> R?, vararg values: Boolean): R? {
        return if (values.any { !it }) action() else null
    }
}