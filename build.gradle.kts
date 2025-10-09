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
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jfree:jfreechart:1.5.6")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "ru.urfu.Main"
}
kotlin {
    jvmToolchain(17)
}