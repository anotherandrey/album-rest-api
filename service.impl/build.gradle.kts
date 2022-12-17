import org.springframework.boot.gradle.tasks.bundling.BootJar

description = "service.impl"

dependencies {
    implementation(project(":service"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("commons-io:commons-io:2.11.0")
}

tasks {
    withType<BootJar> {
        enabled = false
    }
}
