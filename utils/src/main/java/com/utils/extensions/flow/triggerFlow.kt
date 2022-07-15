package com.utils.extensions.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/**
 * Created by Thanh Quang on 13/04/2022.
 */
@FlowPreview
fun triggerFlow(trigger: Trigger, flowProvider: (() -> Flow<Any>)? = null) =
    trigger.triggerEvent
        .flatMapConcat { flowProvider?.invoke() ?: flowOf(Unit) }

class Trigger {
    val triggerEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 64)

    fun trigger() {
        triggerEvent.tryEmit(Unit)
    }
}
