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
    implementation("org.springframework:spring-orm:6.1.8")
    implementation("org.springframework.data:spring-data-jpa:3.3.0")
    implementation("org.hibernate.orm:hibernate-core:6.5.2.Final")
    implementation("com.h2database:h2:2.2.224")
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