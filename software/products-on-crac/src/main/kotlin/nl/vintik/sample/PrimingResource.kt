package nl.vintik.sample

import nl.vintik.sample.model.Product.Companion.productTable
import nl.vintik.sample.util.logger
import org.crac.Context
import org.crac.Core
import org.crac.Resource

class PrimingResource : Resource {
    private val productsController: ProductsController = ProductsController(ProductsService(productTable))

    init {
        logger().info("register priming")
        Core.getGlobalContext().register(this)
    }

    override fun beforeCheckpoint(context: Context<out Resource>) {
        logger().info("beforeCheckpoint hook")
        productsController.find("1")
    }

    override fun afterRestore(context: Context<out Resource>) {
        println("afterRestore hook")
    }
}
