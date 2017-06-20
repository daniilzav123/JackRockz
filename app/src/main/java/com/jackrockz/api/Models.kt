package com.jackrockz.api

import java.util.*

// Authentication Token Model
class AuthenticationTokenModel (
        val authentication_token: TokenModel
)
class TokenModel (
        val token: String
)

//City Model
class CitiesModel(
        val cities: List<CityModel>
)
class CityModel(
        val id: Int,
        val name: String,
        val phone: String,
        val email: String,
        val image: HashImage?
)
class HashImage(
        val default: String,
        val square: String,
        val medium: String,
        val large: String
)


//Event Model
class EventsModel(
        val events: List<EventModel>
)
class EventModel(
        val id: Int,
        val title: String,
        val subtitle: String?,
        val description: String,
        val guestlist_count: Int,
        val image: HashImage?
)