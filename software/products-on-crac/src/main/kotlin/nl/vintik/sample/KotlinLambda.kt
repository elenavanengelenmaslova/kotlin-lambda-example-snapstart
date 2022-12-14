package nl.vintik.sample

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import nl.vintik.sample.model.Product
import nl.vintik.sample.model.Product.Companion.productTable
import nl.vintik.sample.model.ProductRequest
import nl.vintik.sample.util.logger
import org.crac.Core
import org.crac.Resource

@Suppress("UNUSED")
class KotlinLambda : RequestHandler<ProductRequest, Product?>, Resource {
    private val productsController = ProductsController(ProductsService(productTable))

    init {
        //register priming
        Core.getGlobalContext().register(this)
    }

    override fun handleRequest(event: ProductRequest, context: Context): Product? {
        logger().info("SnapStart and CRaC")
        return productsController.find(event.id)
    }

    override fun beforeCheckpoint(context: org.crac.Context<out Resource>?) {
        //beforeCheckpoint hook
        runCatching {
            productsController.find("i dont exist")
        }
    }

    override fun afterRestore(context: org.crac.Context<out Resource>?) {
       //afterRestore hook
    }
}