package nl.vintik.sample.model

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient

@DynamoDbBean
data class Product(
    @get:DynamoDbPartitionKey
    var id: String = "",
    var name: String = "",
    var price: Float = 0.0F
) {

    companion object {
        const val TABLE_NAME = "Products-SnapStart-Example"

        val schema: TableSchema<Product> = TableSchema.fromClass(Product::class.java)

        private val dynamoDbAsyncClient: DynamoDbEnhancedAsyncClient = DynamoDbEnhancedAsyncClient.builder()
            .dynamoDbClient(
                DynamoDbAsyncClient.builder()
                    .region(Region.EU_WEST_1)
                    .build()
            ).build()

        val productTable: DynamoDbAsyncTable<Product> = dynamoDbAsyncClient.table(
            TABLE_NAME,
            schema
        )
    }

}