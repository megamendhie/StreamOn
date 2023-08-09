package com.megamendhie.core.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Mendhie on 8/8/2023
 * Copyright (c) 2022 Crop2Cash. All rights reserved.
 */
data class AddFavoriteResponse(
    val success: Boolean,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String
)
