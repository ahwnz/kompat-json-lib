package nz.ahw.kompat.jsonlib

import net.sf.json.JSONNull as SFJSONNull

object JSONNull: JSON {
    override val json: SFJSONNull = SFJSONNull.getInstance()

    override fun unwrap(): SFJSONNull = json
}