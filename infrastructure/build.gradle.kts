
plugins {
    application
}

dependencies {
    // https://mvnrepository.com/artifact/software.amazon.awscdk/aws-cdk-lib
    implementation("software.amazon.awscdk:aws-cdk-lib:2.96.2")
    // https://mvnrepository.com/artifact/software.constructs/constructs
    implementation("software.constructs:constructs:10.2.70")
}

application {
    mainClass.set("nl.vintik.sample.infra.InfrastructureAppKt")
}

tasks.named("run") {
    dependsOn(":products:packageDistribution")
}
repositories {
    mavenCentral()
}
