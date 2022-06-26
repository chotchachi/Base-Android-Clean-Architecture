package com.utils.extensions

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by Thanh Quang on 22/05/2022.
 */
fun Context.loadJSONFromAsset(jsonFile: String): String? {
    try {
        assets.open(jsonFile).use { `is` ->
            BufferedReader(InputStreamReader(`is`, "UTF-8")).use { reader ->
                val sb = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    sb.append(line)
                }
                return sb.toString()
            }
        }
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
}