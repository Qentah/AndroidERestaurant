package fr.isen.henry.erestaurant.models

data class ApiPrice(
    val id: String,
    val id_pizza: String,
    val id_size: String,
    val price: String,
    val create_date: String,
    val update_date: String,
    val size: ApiSize
)
