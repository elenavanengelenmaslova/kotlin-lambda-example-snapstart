package nl.vintik.sample

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import nl.vintik.sample.model.Product
import nl.vintik.sample.model.Product.Companion.productTable

class KotlinLambda : RequestHandler<Map<String, String>, Product?> {
    override fun handleRequest(event: Map<String, String>, context: Context): Product? {
        return ProductsController(ProductsService(productTable)).find("1")
    }
}