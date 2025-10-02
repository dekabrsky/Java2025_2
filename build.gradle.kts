plugins {
    id("java")
    id("application")
}

group = "ru.urfu"
version = "1.0-SNAPSHOT"

application {
    mainClass = "ru.urfu.Main"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.jfree:jfreechart:1.5.6")
    implementation("org.jfree:jfreechart:1.5.6")
}

tasks.test {
    useJUnitPlatform()
}
