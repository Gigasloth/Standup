package com.gigasloth.standup

import android.content.res.Resources
import android.util.Log
import androidx.annotation.RawRes
import com.google.gson.GsonBuilder
import com.gigasloth.standup.JsonResourceReader
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter
import java.io.Writer
import java.lang.Exception
import java.nio.charset.StandardCharsets
import javax.inject.Inject

/**
 * An object for reading from a JSON resource file and constructing an object from that resource file using Gson.
 */
class JsonResourceReader @Inject constructor(
    private val gson: Gson,
    private val resources: Resources
) {

    companion object {
        private val LOGTAG = JsonResourceReader::class.java.simpleName
    }

    /**
     * Read from a resources file and create a [JsonResourceReader] object that will allow the creation of other
     * objects from this resource.
     *
     * @param id The id for the resource to load, typically held in the raw/ folder.
     * @param type The type of the object to build.
     *
     * @return An object of type T, with member fields populated using Gson.
     */
    fun <T> readToClass(@RawRes resourceId: Int, type: Class<T>): T {
        val resourceReader = resources.openRawResource(resourceId)
        val writer: Writer = StringWriter()
        try {
            val reader = BufferedReader(InputStreamReader(resourceReader, StandardCharsets.UTF_8))
            var line = reader.readLine()
            while (line != null) {
                writer.write(line)
                line = reader.readLine()
            }
        } catch (e: Exception) {
            Log.e(LOGTAG, "Unhandled exception while using JSONResourceReader", e)
        } finally {
            try {
                resourceReader.close()
            } catch (e: Exception) {
                Log.e(LOGTAG, "Unhandled exception while using JSONResourceReader", e)
            }
        }
        val jsonString = writer.toString()

        return gson.fromJson(jsonString, type)
    }
}