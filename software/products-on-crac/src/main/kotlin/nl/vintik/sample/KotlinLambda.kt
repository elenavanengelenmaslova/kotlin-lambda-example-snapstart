package nl.vintik.sample

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import nl.vintik.sample.model.Product
import nl.vintik.sample.model.Product.Companion.productTable
import nl.vintik.sample.util.logger

@Suppress("UNUSED")
class KotlinLambda : RequestHandler<Map<String, String>, Product?> {
    override fun handleRequest(event: Map<String, String>, context: Context): Product? {
        logger().info("SnapStart on CRaC")
        return ProductsController(ProductsService(productTable)).find("1")
    }
    companion object {
        @Suppress("UNUSED")
        private val priming = PrimingResource()
    }
}