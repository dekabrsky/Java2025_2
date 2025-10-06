plugins {
    id("java")
    id("application")
}

group = "ru.urfu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.jfree:jfreechart:1.5.3")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "ru.urfu.Main"
}