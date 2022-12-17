pluginManagement {
    repositories {
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
    }
}

rootProject.name = "album-rest-api"

include("db")
include("openapi")
include("service")
include("service.impl")
include("web")
