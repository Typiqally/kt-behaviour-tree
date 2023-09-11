package com.tpcly.behaviourtree.action

import com.tpcly.behaviourtree.Action
import com.tpcly.behaviourtree.Status

class Condition(
    override val name: String = "",
    val predicate: (blackboard: MutableMap<String, Any>) -> Boolean
) : Action(name) {
    override fun action(blackboard: MutableMap<String, Any>): Status {
        return when (predicate(blackboard)) {
            true -> Status.SUCCESS
            false -> Status.FAILURE
        }
    }
}