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

class JSONArray(override val json: SFJSONArray = SFJSONArray()): JSON, Iterable<Any> {

    override fun unwrap(): SFJSONArray = json

    fun add(value: Any) = json.add(value)
    fun add(index: Int, value: Any) = json.add(index, value)
    fun addAll(collection: Collection<Any>) = json.addAll(collection)

    fun clear() { json.clear() }

    operator fun compareTo(other: Any): Int = json.compareTo(other)

    operator fun contains(value: Any): Boolean = json.contains(value)

    fun discard(index: Int): JSONArray { json.discard(index); return this }

    fun element(value: Boolean): JSONArray { json.element(value); return this }
    fun element(value: Double): JSONArray { json.element(value); return this }
    fun element(value: Int): JSONArray { json.element(value); return this }
    fun element(value: JSONArray): JSONArray { json.element(value); return this }
    fun element(value: JSONNull): JSONArray { json.element(value.json); return this }
    fun element(value: JSONObject): JSONArray { json.element(value); return this }
    fun element(value: Long): JSONArray { json.element(value); return this }
    fun element(value: String): JSONArray { json.element(value); return this }

    override fun equals(other: Any?): Boolean = when(other) {
        is JSON -> json == other.unwrap()
        else -> json == other
    }

    operator inline fun <reified T> get(index: Int): T = when(T::class) {
        Any::class -> json.get(index) as T
        Boolean::class -> json.getBoolean(index) as T
        Double::class -> json.getDouble(index) as T
        Int::class -> json.getInt(index) as T
        JSONArray::class -> JSONArray(json.getJSONArray(index)) as T
        JSONObject::class -> JSONObject(json.getJSONObject(index)) as T
        Long::class -> json.getLong(index) as T
        String::class -> json.getString(index) as T
        else -> throw IllegalArgumentException("Unsupported type to extract from JSON: ${T::class.java.name}")
    }

    fun getAny(index: Int): Any = json[index]!!
    fun getBoolean(index: Int): Boolean = json.getBoolean(index)
    fun getDouble(index: Int): Double = json.getDouble(index)
    fun getInt(index: Int): Int = json.getInt(index)
    fun getJSONArray(index: Int): JSONArray = JSONArray(json.getJSONArray(index))
    fun getJSONObject(index: Int): JSONObject = JSONObject(json.getJSONObject(index))
    fun getLong(index: Int): Long = json.getLong(index)
    fun getString(index: Int): String = json.getString(index)

    override fun hashCode(): Int = json.hashCode()

    fun indexOf(value: Any): Int = json.indexOf(value)

    val indices: IntRange get() = 0 until size

    override fun iterator(): Iterator<Any> = json.iterator() as Iterator<Any>

    operator fun minus(index: Int): JSONArray { remove(index); return this }
    operator fun minus(collection: Collection<Any>): JSONArray { removeAll(collection); return this }

    operator fun minusAssign(index: Int) { remove(index) }
    operator fun minusAssign(collection: Collection<Any>) { removeAll(collection) }

    operator fun plus(value: Boolean): JSONArray = element(value)
    operator fun plus(value: Double): JSONArray = element(value)
    operator fun plus(value: Int): JSONArray = element(value)
    operator fun plus(value: JSONArray): JSONArray = element(value)
    operator fun plus(value: JSONNull): JSONArray = element(value)
    operator fun plus(value: JSONObject): JSONArray = element(value)
    operator fun plus(value: Long): JSONArray = element(value)
    operator fun plus(value: String): JSONArray = element(value)
    operator fun plus(values: Array<out Any>): JSONArray { json.addAll(values); return this }
    operator fun plus(values: Iterable<Any>): JSONArray { json.addAll(values); return this }

    operator fun plusAssign(value: Boolean) { element(value) }
    operator fun plusAssign(value: Double) { element(value) }
    operator fun plusAssign(value: Int) { element(value) }
    operator fun plusAssign(value: JSONArray) { element(value) }
    operator fun plusAssign(value: JSONNull) { element(value) }
    operator fun plusAssign(value: JSONObject) { element(value) }
    operator fun plusAssign(value: Long) { element(value) }
    operator fun plusAssign(value: String) { element(value) }
    operator fun plusAssign(values: Array<out Any>) { json.addAll(values) }
    operator fun plusAssign(values: Iterable<Any>) { json.addAll(values) }

    fun remove(index: Int): Any = json.remove(index)
    fun removeAll(collection: Collection<Any>): Boolean = json.removeAll(collection)
    fun retainAll(collection: Collection<Any>): Boolean = json.retainAll(collection)

    operator fun set(index: Int, value: Boolean) { add(index, value) }
    operator fun set(index: Int, value: Double) { add(index, value) }
    operator fun set(index: Int, value: Int) { add(index, value) }
    operator fun set(index: Int, value: JSONArray) { add(index, value) }
    operator fun set(index: Int, value: JSONNull) { add(index, value.json) }
    operator fun set(index: Int, value: JSONObject) { add(index, value) }
    operator fun set(index: Int, value: Long) { add(index, value) }
    operator fun set(index: Int, value: String) { add(index, value) }

    val size: Int get() = json.size

    fun toArray(): Array<Any> = SFJSONArray.toArray(json) as Array<Any>
    fun toList(): List<Any> = SFJSONArray.toCollection(json).toList() as List<Any>
    override fun toString(): String = json.toString()

    companion object {
        val empty: JSONArray get() = JSONArray()
    }
}
