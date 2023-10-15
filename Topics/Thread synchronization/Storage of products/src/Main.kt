class Storage {
    @Volatile
    var productCount = 0

    @Synchronized
    fun addProduct() {
        productCount++
    }
}