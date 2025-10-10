plugins {
    id("java")
    id("application")
    kotlin("jvm")
}

group = "ru.urfu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.jfree:jfreechart:1.5.3")
    testImplementation(kotlin("test"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "ru.urfu.MainKt"
}

kotlin {
    jvmToolchain(22)
}
