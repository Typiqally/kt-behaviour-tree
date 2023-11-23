package com.tpcly.behaviourtree.sandbox

fun compositeSb(init: CompositeSb<Any>.() -> Unit) = initNode(CompositeSb(), init)

@JvmName("compositeWithState")
fun <S> compositeSb(init: CompositeSb<S>.() -> Unit) = initNode(CompositeSb(), init)

fun actionSb(func: () -> Unit) = object : TreeNodeSb<Any> {
    override fun execute(state: Any?) {
        func()
    }
}

@JvmName("actionWithState")
fun <S> actionSb(func: (state: S) -> Unit) = object : TreeNodeSb<S> {
    override fun execute(state: S?) {
        state?.let { func(it) }
    }
}

fun <S> actionNullableSb(func: (state: S?) -> Unit) = object : TreeNodeSb<S> {
    override fun execute(state: S?) {
        func(state)
    }
}

private fun <T : TreeNodeSb<S>, S> initNode(node: T, init: T.() -> Unit): T {
    node.init()
    return node
}