package org.alter.plugins.content.skills.slayer.data

import org.alter.game.model.combat.SlayerAssignment
import org.alter.game.model.entity.Npc
import org.alter.api.Skills
import org.alter.api.cfg.*

/**
 * @author Alycia <https://github.com/alycii>
 */
class SlayerData(private val assignmentsByMaster: Map<SlayerMaster, List<Assignment>>) {
    fun getAssignmentsForMaster(master: SlayerMaster): List<Assignment> {
        return assignmentsByMaster[master] ?: emptyList()
    }
}

data class Assignment(
    val assignment: SlayerAssignment,
    val amount: IntRange = 0..0,
    val requirement: List<Requirement> = emptyList()
)

enum class SlayerMaster(val id: Int, val identifier: String, val defaultAmount: IntRange) {
    TURAEL(Npcs.TURAEL, identifier = "Turael", defaultAmount = 15..50),
    SPRIA(Npcs.SPRIA, identifier = "Spria", defaultAmount = 20..100),
    VANNAKA(Npcs.VANNAKA, identifier = "Vannaka", defaultAmount = 60..120),
    MAZCHNA(Npcs.MAZCHNA, identifier = "Mazchna", defaultAmount = 40..70),
    CHAELDAR(Npcs.CHAELDAR, identifier = "Chaeldar", defaultAmount = 20..100),


}

// TODO: Note, I only added data for monsters that we currently have definitions for.
//TODO: We will also need to add weights so some tasks occur more frequently than others.
val slayerData = SlayerData(
    mapOf(
        SlayerMaster.TURAEL to listOf(
            Assignment(assignment = SlayerAssignment.CRAWLING_HAND),
            Assignment(assignment = SlayerAssignment.CAVE_CRAWLER),
            Assignment(assignment = SlayerAssignment.JELLY),
            Assignment(assignment = SlayerAssignment.CAVE_SLIME),
            Assignment(assignment = SlayerAssignment.PYREFIEND),
            Assignment(assignment = SlayerAssignment.WATERFIEND),
            Assignment(assignment = SlayerAssignment.WILDDOG),
            Assignment(assignment = SlayerAssignment.TORTURED_SOUL),
            Assignment(assignment = SlayerAssignment.MOURNER),
            Assignment(assignment = SlayerAssignment.DEATHSPAWN),

            /*Example for level requirments
            Assignment(
                assignment = SlayerAssignment.COCKATRICE,
                requirement = listOf(
                    SkillRequirement(skill = Skills.SLAYER, level = 25),
                    SkillRequirement(skill = Skills.DEFENCE, level = 20),
                )),*/

        ),
        SlayerMaster.SPRIA to listOf(),

        ))