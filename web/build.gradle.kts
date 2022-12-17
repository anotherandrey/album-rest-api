description = "web"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(project(":db"))
    implementation(project(":openapi"))
    implementation(project(":service"))
    implementation(project(":service.impl"))
}
