package nl.vintik.sample

class ProductsController(
    private val productsService: ProductsService
) {
    fun execute() = productsService.findProduct("1")
}