plugins {
    id("java")
}

group = "com.distribuida"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-core:6.1.8")
    implementation("org.springframework:spring-context:6.1.8")
    implementation("org.springframework:spring-web:6.1.8")

    implementation("com.h2database:h2:2.2.224")
    implementation("org.hibernate:hibernate-core:6.5.2.Final")
    implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")

    implementation("com.sparkjava:spark-core:2.9.4")
    implementation("com.google.code.gson:gson:2.11.0")
}

sourceSets {
    main {
        output.setResourcesDir(file("${buildDir}/classes/java/main"))
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}