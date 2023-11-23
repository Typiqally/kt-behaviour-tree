package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.sandbox.*
import kotlin.test.Test

internal class SandboxTests {

    @Test
    fun testSandbox() {
        val tree = compositeSb<RootModelSb> {
            +actionSb { println("abc") }
            +actionSb<ChildModelSb> { println("wow ${it.childData}") }
            +actionNullableSb<ChildModelSb> { println("wow ${it?.childData}") }
            +compositeSb {

            }
        }
        val model = object : RootModelSb {
            override val rootData: String = "someRootData"
            override val childData: String = "someChildData"
        }

        tree.execute(model)
    }
}
