package com.app.ola.core.api.general

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

data class GetIdeasResponseModel(
        @SerializedName("response") val ideas: List<IdeaModel>
)