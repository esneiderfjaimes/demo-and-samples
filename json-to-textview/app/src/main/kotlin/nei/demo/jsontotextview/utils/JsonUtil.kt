package nei.demo.jsontotextview

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import nei.demo.jsontotextview.model.Model
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

fun Context.rawToModel(@RawRes id: Int): Model? {
    val json = readFromRaw(id)
    if (json.isBlank()) return null
    return jsonToModel(json)
}

fun jsonToModel(json: String): Model? {
    return try {
        Gson().fromJson(json, Model::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Context.readFromRaw(@RawRes id: Int): String {
    var string: String? = ""
    val stringBuilder = StringBuilder()
    val inputStream: InputStream = this.resources.openRawResource(id)
    val reader = BufferedReader(InputStreamReader(inputStream))
    while (true) {
        try {
            if (reader.readLine().also { string = it } == null) break
        } catch (e: IOException) {
            e.printStackTrace()
        }
        stringBuilder.append(string).append("\n")
    }
    inputStream.close()
    return stringBuilder.toString()
}