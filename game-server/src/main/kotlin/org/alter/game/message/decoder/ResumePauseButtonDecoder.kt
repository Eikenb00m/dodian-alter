package org.alter.game.message.decoder

import org.alter.game.message.MessageDecoder
import org.alter.game.message.impl.ResumePauseButtonMessage

/**
 * @author Tom <rspsmods@gmail.com>
 */
class ResumePauseButtonDecoder : MessageDecoder<ResumePauseButtonMessage>() {

    override fun decode(opcode: Int, opcodeIndex: Int, values: HashMap<String, Number>, stringValues: HashMap<String, String>): ResumePauseButtonMessage {
        val hash = values["hash"]!!.toInt()
        val slot = values["slot"]!!.toInt()
        return ResumePauseButtonMessage(interfaceId = hash shr 16, component = hash and 0xFFFF, slot = if (slot >= 0xFFFF) -1 else slot)
    }
}