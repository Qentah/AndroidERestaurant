package fr.isen.henry.erestaurant.models

data class ApiData(
    val name_fr: ApiCat,
    val name_en: ApiCat,
    val items: List<ApiItem>
)
