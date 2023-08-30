package com.tpcly.behaviourtree.action

import com.tpcly.behaviourtree.Action
import com.tpcly.behaviourtree.Status

class Perform(override val name: String = "", val func: () -> Unit) : Action(name) {
    override fun action(): Status {
        func()
        return Status.SUCCESS
    }
}