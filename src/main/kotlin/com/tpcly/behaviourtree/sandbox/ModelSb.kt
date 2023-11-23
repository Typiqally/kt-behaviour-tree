package com.tpcly.behaviourtree.sandbox

interface RootModelSb : ChildModelSb {
    val rootData: String
}

interface ChildModelSb {
    val childData: String
}