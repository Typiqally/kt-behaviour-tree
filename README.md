# ktBehaviourTree

Behaviour tree implementation, fully built with
Kotlin's [type-safe builders](https://kotlinlang.org/docs/type-safe-builders.html), which allow for creating
Kotlin-based domain-specific languages (DSLs).
Perfect for implementing systematic AI for games, robotics, logical procedures, etc.

The main idea behind Behavior Trees is to break down the decision-making process into a tree-like structure of nodes,
each representing a specific behavior or action.
For more information, please refer to the following references:

- [Behavior tree (artificial intelligence, robotics and control)](https://en.wikipedia.org/wiki/Behavior_tree_(artificial_intelligence,_robotics_and_control))
- [Behavior trees for AI: How they work](https://www.gamedeveloper.com/programming/behavior-trees-for-ai-how-they-work)

## Support

Behaviour tree implementations can vary between different libraries and contexts. The nodes and features currently
supported are:

- **Composites**
    - Sequence
    - Selector
- **Decorators**
    - Inverter
    - Succeeder
    - RepeatUntil
    - Gate
- **Actions**
    - Perform
    - Condition

## Usage

Due to the behaviour tree's DSL, it is a breeze to implement and reuse behaviour trees.
It is possible to create a tree from any node, be it a composite, decorator, or action node.

```kotlin
val helloWorld = sequence {
    +perform { print("Hello, ") }
    +perform { print("world") }
}

val root = sequence {
    +helloWorld
}
```

Notice the `+` operator, this is a mandatory operator to add children to the composite node, it supports any tree node
and therefore also
subtrees as shown in the example above. This operator is not necessary for the decorator and action nodes.

Executing the tree is as easy as calling the `execute` function, which returns a result for the entirety of the tree
call stack.

```kotlin
val result = root.execute()

// Output: Hello, world
```

The outcome comprises the status recorded at each individual node present in the call stack during that particular
execution.

```kotlin
private fun TreeNodeResult.print(indent: Int = 0) {
    println("${"\t".repeat(indent)}> ${parent.javaClass.simpleName} :${parent.name} ${status.name}")

    // Append children if they exist
    children?.forEach {
        it.print(indent + 1)
    }
}

result.print()

// Output: 
// > Sequence : SUCCESS
//   > Sequence : SUCCESS
//     > Perform : SUCCESS
//     > Perform : SUCCESS
```

### Action nodes

These nodes represent actual actions that the agent can perform. These could be things like moving, attacking, or
interacting with objects.

```kotlin
val example = action {
    println("Hello, I will return a success status")
    Status.SUCCESS
}
```

#### Condition

In addition to the generic action nodes, there are also helpers such as the condition action node.
It is responsible for turning a boolean result into a status, `true` being successful and `false` being failure.

```kotlin
fun Door.isReachable(): Boolean {
    // Check if the door is reachable, pathfinding etc.
    return true
}

val example = condition {
    Door.isReachable()
}
```

#### Perform

Furthermore, if you just want to perform a simple action without any logical statusses, you can opt for the perform
action node.

```kotlin
val example = perform {
    println("Hello, I will always succeed!")
}
```

### Composite nodes

#### Sequence

Sequences execute their child nodes in order until one fails or all succeed, similar to an `AND` operator.

```kotlin
val example = sequence {
    +condition {
        println("Hello, ")
        false
    }
    +perform { println("world") }
}

// Output: Hello,
```

The first condition returns a failure, thus the sequence exits early.

#### Selector

Selectors execute their child nodes in order until one succeeds or all fail, similar to an `OR` operator.

```kotlin
val example = selector {
    +condition {
        print("Hello, ")
        false
    }
    +perform { print("world") }
    +perform { print("example") }
}

// Output: Hello, world
```

The first condition returns a failure, thus the selector continues.
The second node will succeed, thus selector exits early and the third node is not be executed.

### Decorator nodes

Decorator nodes modify the behaviour of their child node in some way.

#### Inverter

Inverts the result of its child node (success becomes failure, and vice versa).

```kotlin
val example = sequence {
    +inverter {
        condition {
            println("Hello, ")
            false
        }
    }
    +perform { println("world") }
}

// Output: Hello, world
```

Using the sequence example and applying the inverter on the node that previously failed, we can alter its outcome to
achieve a success status. As a result, the second node within the sequence will also be executed.

#### RepeatUntil

Repeats the execution of its child node a certain number of times until the specified status is met.

```kotlin
fun Door.isReachable(): Boolean {
    // Check if the door is reachable, pathfinding etc.
    return false
}

val example = repeatUntil(Status.SUCCESS) {
    selector {
        +condition { Door.isReachable() }
        +condition {
            //Solve parkour, or something else
            Door.isReachable()
        }
    }
}
```

In this theoretical scenario, the agent will engage in a continuous loop, repeating its actions until the door becomes
reachable. If the door is initially not within reach, the agent will attempt to either navigate through a parkour
challenge or perform other relevant actions to enable access to the door. This looping behavior will persist
indefinitely until the objective of making the door reachable is achieved.

#### Succeeder

Similar to the `perform` action node, the succeeder will turn the status of any child into a success status.

```kotlin
val example = sequence {
    +succeeder {
        condition {
            println("Hello, ")
            false
        }
    }
    +perform { println("world") }
}

// Output: Hello, world
```

Regardless of what status the condition returns, it will always return a success status and therefore finish the entire
sequence.

#### Gate

Executes child only when condition is met, return status of the child if executed, otherwise failure.

```kotlin
val example = gate(predicate = { true }) {
  perform {
    println("Hello, world")
  }
}

// Status: Success (child return status),
// Output: Hello, world
```

```kotlin
val example = gate(predicate = { false }) {
    perform {
        println("Hello, world")
    }
}

// Status: Failure
// Output:
```

## License

This project is licensed under the terms of GPL v3. See [LICENSE](LICENSE) for details.