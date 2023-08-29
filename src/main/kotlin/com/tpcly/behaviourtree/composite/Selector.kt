package com.tpcly.behaviourtree.composite

import com.tpcly.behaviourtree.Composite
import com.tpcly.behaviourtree.Status

class Selector(private val random: Boolean) : Composite() {
    override fun execute(): Status {
        val children = if (random) {
            children.shuffled()
        } else {
            children
        }

        for (child in children) {
            val result = child.execute()

            if (result == Status.SUCCESS) {
                return result
            }
        }

        return Status.FAILURE
    }
}