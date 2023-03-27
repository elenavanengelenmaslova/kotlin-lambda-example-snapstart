package nl.vintik.sample

import nl.vintik.sample.model.Product

class ProductsController(
    private val productsService: ProductsService
) {
    fun find(id: String) = productsService.findProduct(id)

    fun resetClient() = Product.resetClient()
}