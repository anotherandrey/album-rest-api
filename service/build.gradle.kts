import org.springframework.boot.gradle.tasks.bundling.BootJar

description = "service"

dependencies {
    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks {
    withType<BootJar> {
        enabled = false
    }
}
