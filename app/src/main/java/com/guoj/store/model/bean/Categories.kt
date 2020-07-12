package com.guoj.store.model.bean

data class Categories(
    val code: Int,
    val data: List<Data>,
    val message: String,
    val success: Boolean

) {
    data class Data(
        val id: Int,
        val title: String

    ) {
        override fun toString(): String {
            return "Data(id=$id, title='$title')"
        }
    }
    override fun toString(): String {
        return "Categories(code=$code, data=$data, message='$message', success=$success)"
    }
}

