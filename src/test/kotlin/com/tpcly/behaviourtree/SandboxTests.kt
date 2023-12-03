package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.sandbox.RootModelSb
import com.tpcly.behaviourtree.sandbox.actionSb
import com.tpcly.behaviourtree.sandbox.compositeSb
import kotlin.test.Test

internal class SandboxTests {

    @Test
    fun testSandbox() {
        val tree = compositeSb {
            +actionSb { println("abc") }
            +actionSb<Any> { state -> state.toString() }
            +compositeSb {

            }
        }
        val model = object : RootModelSb {
            override val rootData: String = "someRootData"
            override val childData: String = "someChildData"
        }

        tree.execute(Any())
    }
}
