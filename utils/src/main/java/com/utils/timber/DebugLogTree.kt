package com.utils.timber

import timber.log.Timber

/**
 * Created by Thanh Quang on 5/27/21.
 */
class DebugLogTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return String.format(
            ">>> %s:%s",
            super.createStackElementTag(element),
            element.lineNumber
        )
    }
}
