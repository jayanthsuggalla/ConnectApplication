package com.example.connectapp

data class UserResponsive(

    var data: ArrayList<Data>,
    var page: Int,
    var perPage: Int,
    var support: Support,
    var total: Int,
    var totalPages: Int
)
