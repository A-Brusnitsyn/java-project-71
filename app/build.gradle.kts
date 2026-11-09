plugins {
    id("java")
    id("io.github.ben-manes.versions") version "0.66.0"
    id("com.diffplug.spotless") version "7.0.2"
    id("jacoco")
    application
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application { mainClass.set("hexlet.code.App") }

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}
spotless {
    java {
        target("src/**/*.java") // Выбирает все Java-файлы
        googleJavaFormat("1.36.1")

        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

jacoco {
    toolVersion = "0.8.14" // Актуальная версия
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    // Исключаем точку входа из отчёта
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude("hexlet/code/App.class")
            }
        })
    )
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                counter = "LINE"
                minimum = "0.80".toBigDecimal() // Порог 80% (настройте под свой проект)
            }
        }
    }
    // Исключаем точку входа из проверки
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude("hexlet/code/App.class")
            }
        })
    )
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}

tasks.test {
    useJUnitPlatform()
}