package com.tpcly.behaviourtree.action

import com.tpcly.behaviourtree.Action
import com.tpcly.behaviourtree.Status

class Perform(
    override val name: String = "",
    val func: (blackboard: MutableMap<String, Any>) -> Unit
) : Action(name) {
    override fun action(blackboard: MutableMap<String, Any>): Status {
        func(blackboard)
        return Status.SUCCESS
    }
}