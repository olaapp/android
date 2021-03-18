package com.app.ola.core.api.users

data class UpdateTokenRequestModel(
        val key: String = "",
        val token: String = ""
)