package com.megamendhie.core.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Mendhie on 8/8/2023
 * Copyright (c) 2022 Crop2Cash. All rights reserved.
 */
class Converters {
    companion object{
        @JvmStatic
        @TypeConverter
        fun genreIdsFromJson(json: String?): List<Int>
                = if(json==null) listOf() else Gson().fromJson(json, object : TypeToken<List<Int>>(){}.type)

        @JvmStatic
        @TypeConverter
        fun genreIdsToJson(data: List<Int>)
                = Gson().toJson(data)
    }
}