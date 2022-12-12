package nl.vintik.sample

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import nl.vintik.sample.model.Product
import nl.vintik.sample.model.Product.Companion.productTable
import nl.vintik.sample.util.logger
import org.crac.Core
import org.crac.Resource

@Suppress("UNUSED")
class KotlinLambda : RequestHandler<Map<String, String>, Product?>, Resource {
    private val productsController = ProductsController(ProductsService(productTable))

    init {
        logger().info("register priming")
        Core.getGlobalContext().register(this)
    }

    override fun handleRequest(event: Map<String, String>, context: Context): Product? {
        logger().info("SnapStart on CRaC")
        return productsController.find("1")
    }

    override fun beforeCheckpoint(context: org.crac.Context<out Resource>?) {
        logger().info("beforeCheckpoint hook")
        println("beforeCheckpoint hook")
        productsController.find("1")
    }

    override fun afterRestore(context: org.crac.Context<out Resource>?) {
        println("afterRestore hook")
    }
}