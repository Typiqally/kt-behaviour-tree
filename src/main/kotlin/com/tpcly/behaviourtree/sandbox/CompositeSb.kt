package com.tpcly.behaviourtree.sandbox

class CompositeSb<in S : Any> : TreeNodeSb<S> {
    private val children = mutableListOf<TreeNodeSb<Any>>()

    operator fun TreeNodeSb<@UnsafeVariance S>.unaryPlus() {
        children += this as TreeNodeSb<Any>
    }

    operator fun TreeNodeSb<@UnsafeVariance S>.unaryMinus() {
        children -= this as TreeNodeSb<Any>
    }

    override fun execute(state: S) {
        for (child in children) {
            child.execute(state)
        }
    }
}
