package nl.vintik.sample.model

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema
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

        //TODO: use introspection
        val schema: StaticTableSchema<Product> = TableSchema.builder(Product::class.java)
            .newItemSupplier { Product() }
            .addAttribute(
                StaticAttribute
                    .builder(Product::class.java, String::class.java)
                    .tags(StaticAttributeTags.primaryPartitionKey())
                    .name("id")
                    .getter { it.id }
                    .setter { product: Product, id: String -> product.id = id }
                    .build())
            .addAttribute(
                StaticAttribute
                    .builder(Product::class.java, String::class.java)
                    .getter { it.name }
                    .setter { product: Product, name: String -> product.name = name }
                    .name("name")
                    .build()
            )
            .addAttribute(
                StaticAttribute
                    .builder(Product::class.java, Float::class.java)
                    .name("price")
                    .getter { it.price }
                    .setter { product: Product, price: Float -> product.price = price }
                    .build()
            )
            .build()

        val dynamoDbAsyncClient: DynamoDbEnhancedAsyncClient = DynamoDbEnhancedAsyncClient.builder()
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