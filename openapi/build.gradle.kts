import org.openapitools.generator.gradle.plugin.tasks.GenerateTask
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.openapi.generator") version "6.0.0"
}

dependencies {
    implementation(project(":service"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    /* swagger annotations for openapi */
    implementation("io.swagger.core.v3:swagger-annotations:2.2.2")
}

tasks {
    val openapiConfigOptions =
            mapOf("useTags" to "true", "useSpringBoot3" to "true", "snapshotVersion" to "true", "interfaceOnly" to "true", "openApiNullable" to "false")

    val generateImagesApi by registering(GenerateTask::class) {
        generatorName.set("spring")
        inputSpec.set("$projectDir/src/main/resources/openapi/v1/images.yaml")
        outputDir.set("$projectDir")
        templateDir.set("$projectDir/src/main/resources/openapi")
        apiPackage.set("org.gorshkovdev.openapi.v1.images")
        modelPackage.set("org.gorshkovdev.openapi.v1.images")
        configOptions.set(openapiConfigOptions)
    }

    withType<JavaCompile> {
        dependsOn(generateImagesApi)
    }

    withType<BootJar> {
        enabled = false
    }
}
