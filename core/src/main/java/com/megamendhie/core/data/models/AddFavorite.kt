package com.megamendhie.core.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Mendhie on 8/8/2023
 * Copyright (c) 2022 Crop2Cash. All rights reserved.
 */
data class AddFavorite(
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("media_id")
    val mediaId: Int,
    val favorite: Boolean
)
