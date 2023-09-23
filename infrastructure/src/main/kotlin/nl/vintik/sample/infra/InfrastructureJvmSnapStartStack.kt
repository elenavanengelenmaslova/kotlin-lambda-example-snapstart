package nl.vintik.sample.infra

import software.amazon.awscdk.*
import software.amazon.awscdk.services.dynamodb.Table
import software.amazon.awscdk.services.lambda.Code
import software.amazon.awscdk.services.lambda.Function
import software.amazon.awscdk.services.lambda.Runtime
import software.amazon.awscdk.services.lambda.SnapStartConf
import software.amazon.awscdk.services.logs.RetentionDays
import software.constructs.Construct

class InfrastructureJvmSnapStartStack(
    scope: Construct,
    id: String,
    props: StackProps,
) : Stack(scope, id, props) {
    init {
        val functionId = "lambdaJvmSnapStart4Compare"
        val productsTable = Table.fromTableArn(
            this,
            "dynamoTable",
            Fn.importValue("Products-SnapStart-ExampleTableArn")
        )
        val function =
            Function.Builder.create(this, functionId)
                .description("Kotlin Lambda JVM SnapStart Example")
                .handler("nl.vintik.sample.KotlinLambda::handleRequest")
                .runtime(Runtime.JAVA_17)
                .code(Code.fromAsset("../build/dist/function.zip"))
                .environment(
                    mapOf(
                        // Ensure lambda version is updated with latest lambda code
                        "CodeVersionString" to System.getenv(
                            "BUILD_NO"
                        ),
                    )
                )
                .snapStart(SnapStartConf.ON_PUBLISHED_VERSIONS)  //enable SnapStart
                .logRetention(RetentionDays.ONE_WEEK)
                .memorySize(512)
                .timeout(Duration.seconds(120))
                .build()

        // publish a version
        function.currentVersion

        productsTable.grantReadData(function)

        CfnOutput(
            this, "${functionId}-fn-arn",
            CfnOutputProps.builder()
                .value(function.functionArn)
                .description("The arn of the $functionId function")
                .exportName("${functionId}FnArn").build()
        )
    }
}