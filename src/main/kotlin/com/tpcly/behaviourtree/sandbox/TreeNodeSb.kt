package com.tpcly.behaviourtree.sandbox

interface TreeNodeSb<in S> {
    fun execute(state: S)
}

fun TreeNodeSb<Any>.execute() {
    execute(Any())
}