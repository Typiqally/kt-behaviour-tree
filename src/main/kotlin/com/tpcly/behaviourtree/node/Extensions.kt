package com.tpcly.behaviourtree.node

fun <S> TreeNode<S>.inverted() = Inverter(name, this)