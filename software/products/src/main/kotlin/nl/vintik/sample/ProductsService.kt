package nl.vintik.sample

import nl.vintik.sample.model.Product
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.Key

class ProductsService(private val productTable: DynamoDbAsyncTable<Product>) {
    fun findProduct(id: String): Product? {
        return productTable.getItem(
            Key
                .builder()
                .partitionValue(id)
                .build()
        ).get()
    }
}