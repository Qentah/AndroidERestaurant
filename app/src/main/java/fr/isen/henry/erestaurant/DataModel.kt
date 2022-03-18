data class ResponseData (
    val data: List<Datum>
)

data class Datum (
    val name_fr: NameCat,
    val name_en: NameCat,
    val items: List<Item>
)

data class Item (
    val id: String,
    val name_fr: String,
    val name_en: String,
    val id_category: String,
    val categ_name_fr: NameCat,
    val categ_name_en: NameCat,
    val images: List<String>,
    val ingredients: List<Ingredient>,
    val prices: List<Price>
)

enum class NameCat {
    Desserts,
    Entrées,
    Plats
}

data class Ingredient (
    val id: String,
    val id_shop: String,
    val name_fr: String,
    val name_en: String,
    val create_date: String,
    val update_date: String,
    val id_pizza: String
)

data class Price (
    val id: String,
    val id_pizza: String,
    val id_size: String,
    val price: String,
    val create_date: String,
    val update_date: String,
    val size: Size
)

enum class Size {
    Grande,
    Moyenne,
    Petite
}
