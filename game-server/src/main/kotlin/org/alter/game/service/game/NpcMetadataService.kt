package org.alter.game.service.game

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.alter.game.Server
import org.alter.game.fs.DefinitionSet
import org.alter.game.fs.def.NpcDef
import org.alter.game.model.World
import org.alter.game.service.Service
import gg.rsmod.util.ServerProperties
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author Tom <rspsmods@gmail.com>
 */
class NpcMetadataService : Service {

    private lateinit var path: Path

    override fun init(server: Server, world: World, serviceProperties: ServerProperties) {
        path = Paths.get(serviceProperties.getOrDefault("path", "../data/cfg/npcs.yml"))
        if (!Files.exists(path)) {
            throw FileNotFoundException("Path does not exist. $path")
        }

        Files.newBufferedReader(path).use { reader ->
            load(world.definitions, reader)
        }
    }

    override fun postLoad(server: org.alter.game.Server, world: World) {
    }

    override fun bindNet(server: org.alter.game.Server, world: World) {
    }

    override fun terminate(server: org.alter.game.Server, world: World) {
    }

    private fun load(definitions: DefinitionSet, reader: BufferedReader) {
        val mapper = ObjectMapper(YAMLFactory())
        mapper.propertyNamingStrategy = PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

        val reference = object : TypeReference<List<Metadata>>() {}
        mapper.readValue<List<Metadata>>(reader, reference)?.let { metadataSet ->
            metadataSet.forEach { metadata ->
                val def = definitions.getNullable(NpcDef::class.java, metadata.id) ?: return@forEach
                def.examine = metadata.examine
            }
        }
    }

    private data class Metadata(val id: Int = -1, val examine: String? = null)
}