/*------------------------------------------------------------------*\
**                                                                  **
**        kompat-json-lib: Kompatibility for net.sf.json-lib        **
**        Kotlin Compatibility Project (Kompat)                     **
**        Copyright 2017-2018, Alex Westphal                        **
**        https://github.com/ahwnz/kompat-json-lib                  **
**                                                                  **
\*------------------------------------------------------------------*/
package nz.ahw.kompat.jsonlib

import net.sf.json.JSONArray as SFJSONArray
import net.sf.json.JSONObject as SFJSONObject

val JSONNull = net.sf.json.JSONNull.getInstance()

// Wrapping  -----------------------------------------------------------------------------------------------------------

fun SFJSONArray.wrap(): JSONArray = JSONArray(this)
fun SFJSONObject.wrap(): JSONObject = JSONObject(this)


// JSON Building  ------------------------------------------------------------------------------------------------------

fun jsonArrayOf(vararg values: Any): JSONArray {
    val jsonArray = JSONArray()
    jsonArray += values
    return jsonArray
}

fun jsonArrayOf(vararg pairs: Pair<Int, Any>): JSONArray {
    val jsonArray = JSONArray()
    for((index, value) in pairs) jsonArray.add(index, value)
    return jsonArray
}

fun jsonObjectOf(vararg pairs: Pair<String, Any>): JSONObject {
    val jsonObject = JSONObject()
    for((key, value) in pairs) jsonObject.put(key, value)
    return jsonObject
}

// Misc Extensions  ----------------------------------------------------------------------------------------------------

fun String.toJSONArray(): JSONArray = JSONArray(SFJSONArray.fromObject(this))
fun String.toJSONObject(): JSONObject = JSONObject(SFJSONObject.fromObject(this))

fun <T> Array<T>.toJSON(): JSONArray = JSONArray(SFJSONArray.fromObject(this))

fun <T> List<T>.toJSON(): JSONArray = JSONArray(SFJSONArray.fromObject(this))

fun <T> Map<String, T>.toJSON(): JSONObject = JSONObject(SFJSONObject.fromObject(this))