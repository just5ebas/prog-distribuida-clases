plugins {
    id("java")
}

group = "com.distribuida"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation( project(":ejemplo01_cdi"))
}

tasks.test {
    useJUnitPlatform()
}