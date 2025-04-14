package com.danzucker.jetpack_compose_learning.performanceoptimization.mainsafety

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

/**
 * If your code contain a blocking call / blocking operation, and it is not a suspend function,
 * You have the responsibility of switching to the right thread in other to execute the blocking call.
 * And sticking to the concept of main safety
 */

class BitmapCompressor(
    private val context: Context
) {

    suspend fun compressImage(
        contentUri: Uri,
        compressionThreshold: Long
    ): Bitmap? {

        return withContext(Dispatchers.IO) {

            val inputBytes = context
                .contentResolver
                .openInputStream(contentUri)?.use { inputStream ->
                    inputStream.readBytes()
                } ?: return@withContext null

            val bitmap = BitmapFactory.decodeByteArray(inputBytes, 0, inputBytes.size)

            // Dispatcher.Default is used for CPU-intensive work
            withContext(Dispatchers.Default) {
                var outputBytes: ByteArray
                var quality = 100

                do {
                    ByteArrayOutputStream().use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                        outputBytes = outputStream.toByteArray()
                        quality -= (quality * 0.1).roundToInt()
                    }
                } while (outputBytes.size > compressionThreshold && quality > 5)

                BitmapFactory.decodeByteArray(outputBytes, 0, outputBytes.size)
            }

        }
    }
}