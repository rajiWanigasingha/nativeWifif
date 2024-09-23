package org.wifi.project.ui

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.logging.Logger
import kotlin.concurrent.thread

@Serializable
data class ConnInfo(
    val connection: String,
    val device: String,
    val state: String,
)

class Command {

    private val logger = Logger.getLogger("Command")

    fun executeCommand(): List<ConnInfo> {
        // Use ProcessBuilder instead of Runtime.exec()
        val processBuilder = ProcessBuilder(
            "bash",
            "-c",
            "nmcli -t -f SSID,DEVICE,ACTIVE device wifi list | jq -R 'split(\":\") | {connection: .[0], device: .[1], state: .[2]}' | jq -s"
        )

        // Start the process
        val process = processBuilder.start()

        // Capture the output
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val output = StringBuilder()
        reader.useLines { lines ->
            lines.forEach { output.append(it).append("\n") }
        }

        // Wait for the process to complete
        process.waitFor()

        // Log the output
        logger.info(output.toString())

        return Json.decodeFromString(output.toString())
    }


    fun executeCommandDisconnect(
        connectionName: String
    ) {
        val processBuilder = ProcessBuilder("bash", "-c", "nmcli c down '${connectionName}'")
        processBuilder.start()
    }


    fun executeCommandConnect(
        connectionName: String
    ): Boolean {

        try {
            val processBuilder =
                ProcessBuilder("bash", "-c", "nmcli device wifi connect '${connectionName}'")
            val process = processBuilder.start()

            val stderr = BufferedReader(InputStreamReader(process.errorStream))
            val errorOutput = stderr.lines().toList()

            if (errorOutput.isNotEmpty()) {
                errorOutput.forEach { err ->
                    if (err == "Error: Connection activation failed: Secrets were required, but not provided.") {
                        throw SecreteNotProvidedException()
                    }
                }
            }


            return true

        } catch (err: SecreteNotProvidedException) {
            return false
        } catch (err: Exception) {
            logger.info(err.message)
            throw err
        }

    }

    fun executeCommandConnectWithPassword(
        connectionName: String,
        password: String
    ): Boolean {
        try {
            val processBuilder =
                ProcessBuilder("bash", "-c", "nmcli device wifi connect '${connectionName}' password '${password}'")
            val process = processBuilder.start()

            val stderr = BufferedReader(InputStreamReader(process.errorStream))
            val errorOutput = stderr.lines().toList()

            if (errorOutput.isNotEmpty()) {
                errorOutput.forEach { err ->
                    if (err == "Error: Connection activation failed: Secrets were required, but not provided.") {
                        throw SecreteNotProvidedException()
                    }
                }
            }

            return true
        } catch (err: SecreteNotProvidedException) {
            return false
        }  catch (err: Exception) {
            logger.info(err.message)
            throw err
        }
    }

}




class SecreteNotProvidedException() : Exception()