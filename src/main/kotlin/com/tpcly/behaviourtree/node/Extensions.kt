package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.node.decorator.Inverter

fun TreeNode.inverted() = Inverter(name, this)