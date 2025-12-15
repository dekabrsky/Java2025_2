plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "ru.urfu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
    // кодировка компилятора
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.47.1.0")
    implementation("com.opencsv:opencsv:5.9")
    implementation("org.jfree:jfreechart:1.5.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.11.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
// кодировка JVM во время запуска
application {
    mainClass.set("ru.urfu.Main")
    applicationDefaultJvmArgs = listOf("-Dfile.encoding=UTF-8")
}

tasks.shadowJar {
    archiveBaseName.set("CountryStatsProject")
    archiveClassifier.set("")
    archiveVersion.set("1.0")
}