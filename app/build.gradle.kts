plugins {
    id("java")
    id("io.github.ben-manes.versions") version "0.66.0"
    application
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application { mainClass.set("hexlet.code.Main") }

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}



tasks.test {
    useJUnitPlatform()
}