import java.io.Serializable

data class ResponseData (
    val data: List<Datum>
):Serializable

data class Datum (
    val name_fr: String,
    val name_en: String,
    val items: List<Item>
):Serializable

data class Item (
    val id: String,
    val name_fr: String,
    val name_en: String,
    val id_category: String,
    val categ_name_fr: String,
    val categ_name_en: String,
    val images: List<String>,
    val ingredients: List<Ingredient>,
    val prices: List<Price>
):Serializable

data class Panier(
    var content:MutableList<PanierItem>
)

data class PanierItem (
    var name_fr: String,
    var quantity: Int,
    val unit_price: Float,
    val image : String
):Serializable


data class Ingredient (
    val id: String,
    val id_shop: String,
    val name_fr: String,
    val name_en: String,
    val create_date: String,
    val update_date: String,
    val id_pizza: String
):Serializable

data class Price (
    val id: String,
    val id_pizza: String,
    val id_size: String,
    val price: String,
    val create_date: String,
    val update_date: String,
    val size: String
):Serializable

