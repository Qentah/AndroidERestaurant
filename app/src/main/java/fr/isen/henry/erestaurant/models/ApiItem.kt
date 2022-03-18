package fr.isen.henry.erestaurant.models

data class ApiItem(
    val id: String,
    val name_fr: String,
    val name_en: String,
    val id_category: String,
    val categ_name_fr: ApiCat,
    val categ_name_en: ApiCat,
    val images: List<String>,
    val ingredients: List<ApiIngredient>,
    val prices: List<ApiPrice>
)
