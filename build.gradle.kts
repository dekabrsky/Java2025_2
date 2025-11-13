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
    implementation("org.jfree:jfreechart:1.5.6")
    implementation("com.google.inject:guice:5.1.0")
    implementation("org.telegram:telegrambots:6.9.0")
    implementation("org.xerial:sqlite-jdbc:3.7.2")
    implementation("com.j256.ormlite:ormlite-core:6.1")
    implementation("com.j256.ormlite:ormlite-jdbc:6.1")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "ru.urfu.Main"
}