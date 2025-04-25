package com.danzucker.jetpack_compose_learning.performanceoptimization

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadImageFromAsset(
    private val context: Context
) {
    suspend fun loadImageFromAsset(fileName: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            val inputBytes = context.assets.open(fileName).use { inputStream ->
                inputStream.readBytes()
            }

            BitmapFactory.decodeByteArray(inputBytes, 0, inputBytes.size)
        }
    }
}