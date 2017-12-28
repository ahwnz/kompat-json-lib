/*------------------------------------------------------------------*\
**                                                                  **
**        kompat-json-lib: Kompatibility for net.sf.json-lib        **
**        Kotlin Compatibility Project (Kompat)                     **
**        Copyright 2017-2018, Alex Westphal                        **
**        https://github.com/ahwnz/kompat-json-lib                  **
**                                                                  **
\*------------------------------------------------------------------*/
package nz.ahw.kompat.jsonlib

import net.sf.json.JSON as SFJSON

import java.io.Writer

interface JSON {

    val json: SFJSON

    fun unwrap(): SFJSON

    val isArray: Boolean get() = json.isArray

    val isEmpty: Boolean get() = json.isEmpty

    fun toString(indentFactor: Int): String = json.toString(indentFactor)

    fun toString(indentFactor: Int, indent: Int): String = json.toString(indentFactor, indent)

    fun write(writer: Writer): Writer = json.write(writer)
}