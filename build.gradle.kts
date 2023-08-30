plugins {
    kotlin("jvm") version "1.9.0"
    `maven-publish`
}

group = "com.tpcly"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.7")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven("https://maven.tpcly.com/releases") {
            name = "Release"
            credentials {
                username = System.getenv("PUBLISH_USERNAME")
                password = System.getenv("PUBLISH_PASSWORD")
            }
        }
    }
    repositories {
        maven("https://maven.tpcly.com/snapshots") {
            name = "Snapshot"
            credentials {
                username = System.getenv("PUBLISH_USERNAME")
                password = System.getenv("PUBLISH_PASSWORD")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }
}
