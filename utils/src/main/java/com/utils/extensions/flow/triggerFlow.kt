package com.utils.extensions.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/**
 * Created by Thanh Quang on 13/04/2022.
 */
@FlowPreview
fun <T> triggerFlow(trigger: Trigger, flowProvider: () -> Flow<T>) =
    trigger.triggerEvent
        .flatMapConcat { flowProvider.invoke() }

fun triggerFlow(trigger: Trigger) = trigger.triggerEvent
    .mapToUnit()

class Trigger {
    val triggerEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 64)

    fun trigger() {
        triggerEvent.tryEmit(Unit)
    }
}
