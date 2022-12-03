import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("java")
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.openapi.generator") version "6.0.0"
}

group = "org.album"
version = "1.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
    maven { url = uri("https://plugins.gradle.org/m2/") }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    val data = arrayOf("jpa") // ...
    data.forEach {
        implementation("org.springframework.boot:spring-boot-starter-data-$it")
    }

    implementation("org.springframework.boot:spring-boot-starter-validation")

    /* postgresql, db driver */
    implementation("org.postgresql:postgresql")

    /* flyway core, db migration */
    implementation("org.flywaydb:flyway-core")

    /* swagger annotations for openapi */
    implementation("io.swagger.core.v3:swagger-annotations:2.2.2")

    implementation("org.apache.commons:commons-lang3")
    implementation("com.google.guava:guava:31.1-jre")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    /* test */
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
}

tasks {
    val openapiConfigOptions =
            mapOf("useTags" to "true", "useSpringBoot3" to "true", "snapshotVersion" to "true", "interfaceOnly" to "true", "openApiNullable" to "false")

    val generateAlbumApi by registering(GenerateTask::class) {
        generatorName.set("spring")
        inputSpec.set("$rootDir/src/main/resources/openapi/v1/album.yaml")
        outputDir.set("$rootDir")
        templateDir.set("$rootDir/src/main/resources/openapi")
        apiPackage.set("org.album.openapi.v1.album")
        modelPackage.set("org.album.openapi.v1.album")
        configOptions.set(openapiConfigOptions)
    }

    withType<JavaCompile> {
        dependsOn(generateAlbumApi)
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
