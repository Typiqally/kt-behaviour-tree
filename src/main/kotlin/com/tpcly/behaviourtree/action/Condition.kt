package com.tpcly.behaviourtree.action

import com.tpcly.behaviourtree.Action
import com.tpcly.behaviourtree.Status

class Condition(override val name: String = "", val predicate: () -> Boolean) : Action(name) {
    override fun action(): Status {
        return when (predicate()) {
            true -> Status.SUCCESS
            false -> Status.FAILURE
        }
    }
}