plugins {
    id("java")
    id("org.springframework.boot") version "3.0.0" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
}

allprojects {
    group = "org.gorshkovdev"
    version = "1.0-SNAPSHOT"

    repositories {
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    dependencies {
        implementation("org.apache.commons:commons-lang3")

        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        /* test */
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        testCompileOnly("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok")
    }

    tasks {
        withType<JavaCompile> {
            java.sourceCompatibility = JavaVersion.VERSION_17
        }

        withType<Test> {
            useJUnitPlatform()
        }
    }
}
