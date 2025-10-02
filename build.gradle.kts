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
    implementation("org.jfree:jfreechart:1.5.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
