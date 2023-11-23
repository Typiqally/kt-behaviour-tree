package com.tpcly.behaviourtree.sandbox

interface TreeNodeSb<in S> {
    fun execute(state: S? = null)
}