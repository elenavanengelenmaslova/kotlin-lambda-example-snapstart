package nl.vintik.sample

class ProductsController(
    private val productsService: ProductsService
) {
    fun find(id: String) = productsService.findProduct(id)
}