/*------------------------------------------------------------------*\
**                                                                  **
**        kompat-json-lib: Kompatibility for net.sf.json-lib        **
**        Kotlin Compatibility Project (Kompat)                     **
**        Copyright 2017-2018, Alex Westphal                        **
**        https://github.com/ahwnz/kompat-json-lib                  **
**                                                                  **
\*------------------------------------------------------------------*/
package nz.ahw.kompat.jsonlib

import net.sf.json.JSONNull
import net.sf.json.JSONObject as SFJSONObject

class JSONObject(override val json: SFJSONObject = SFJSONObject()): JSON {

    override fun unwrap(): SFJSONObject = json

    fun clear() { json.clear() }

    operator fun compareTo(other: Any) = json.compareTo(other)

    operator fun contains(key: String): Boolean = json.containsKey(key)
    operator fun contains(pair: Pair<String, Any>): Boolean =
            pair.let { (key, value) -> json.containsKey(key) && json[key] == value }

    fun containsKey(key: String): Boolean = json.containsKey(key)
    fun containsValue(value: Any): Boolean = json.containsValue(value)

    fun discard(key: String): JSONObject { json.discard(key); return this }

    fun element(key: String, value: Boolean): JSONObject { json.element(key, value); return this }
    fun element(key: String, value: Double): JSONObject { json.element(key, value); return this }
    fun element(key: String, value: Int): JSONObject { json.element(key, value); return this }
    fun element(key: String, value: JSONArray): JSONObject { json.element(key, value); return this }
    fun element(key: String, value: JSONNull): JSONObject { json.element(key, value); return this }
    fun element(key: String, value: JSONObject): JSONObject { json.element(key, value); return this }
    fun element(key: String, value: Long): JSONObject { json.element(key, value); return this }
    fun element(key: String, value: String): JSONObject { json.element(key, value); return this }

    override fun equals(other: Any?): Boolean = when(other) {
        is JSON -> json == other.unwrap()
        else -> json == other
    }

    operator inline fun <reified T> get(key: String): T = when(T::class) {
        Any::class -> json.get(key) as T
        Boolean::class -> json.getBoolean(key) as T
        Double::class -> json.getDouble(key) as T
        Int::class -> json.getInt(key) as T
        JSONArray::class -> JSONArray(json.getJSONArray(key)) as T
        JSONObject::class -> JSONObject(json.getJSONObject(key)) as T
        Long::class -> json.getLong(key) as T
        String::class -> json.getString(key) as T
        else -> throw IllegalArgumentException("Unsupported type to extract from JSON: ${T::class.java.name}")
    }

    fun getAny(key: String): Any = json.get(key)
    fun getBoolean(key: String): Boolean = json.getBoolean(key)
    fun getDouble(key: String): Double = json.getDouble(key)
    fun getInt(key: String): Int = json.getInt(key)
    fun getJSONArray(key: String): JSONArray = JSONArray(json.getJSONArray(key))
    fun getJSONObject(key: String): JSONObject = JSONObject(json.getJSONObject(key))
    fun getLong(key: String): Long = json.getLong(key)
    fun getString(key: String): String = json.getString(key)

    override fun hashCode(): Int = json.hashCode()

    val keys: Set<String> get() = json.keys as Set<String>

    operator fun minus(key: String): JSONObject { remove(key); return this }
    operator fun minus(keys: Iterable<String>): JSONObject { removeAll(keys); return this}

    operator fun minusAssign(key: String) { remove(key) }
    operator fun minusAssign(keys: Iterable<String>) { removeAll(keys) }

    operator fun plus(pair: Pair<String, Any>): JSONObject = pair.let { (key, value) -> put(key, value); this }
    operator fun plus(keyValues: Iterable<Pair<String, Any>>): JSONObject = plus(keyValues.toMap())
    operator fun plus(map: Map<String, Any>): JSONObject { putAll(map); return this }

    operator fun plusAssign(pair: Pair<String, Any>) { pair.let { (key, value) -> put(key, value) } }
    operator fun plusAssign(keyValues: Iterable<Pair<String, Any>>) { plus(keyValues.toMap()) }
    operator fun plusAssign(map: Map<String, Any>) { putAll(map) }

    fun put(key: String, value: Any) = json.put(key, value)
    fun putAll(map: Map<String, Any>) = json.putAll(map)

    fun remove(key: String) = json.remove(key)
    fun removeAll(keys: Iterable<String>) { for(key in keys) remove(key) }

    operator fun set(key: String, value: Boolean) { element(key, value) }
    operator fun set(key: String, value: Double) { element(key, value) }
    operator fun set(key: String, value: Int) { element(key, value) }
    operator fun set(key: String, value: JSONArray) { element(key, value) }
    operator fun set(key: String, value: JSONNull) { element(key, value) }
    operator fun set(key: String, value: JSONObject) { element(key, value) }
    operator fun set(key: String, value: Long) { element(key, value) }
    operator fun set(key: String, value: String) { element(key, value) }

    val size: Int get() = json.size

    inline fun <reified T> toBean(): T = SFJSONObject.toBean(json, T::class.java) as T
    override fun toString(): String = json.toString()

    val values: Collection<Any> get() = json.values as Collection<Any>

    companion object {
        val empty: JSONObject get() = JSONObject()
    }
}