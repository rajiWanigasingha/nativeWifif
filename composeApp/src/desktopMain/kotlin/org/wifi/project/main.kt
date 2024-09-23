package org.wifi.project

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import kotlinx.coroutines.launch
import org.wifi.main.Notify
import org.wifi.project.ui.Command
import org.wifi.project.ui.ConnInfo
import org.wifi.project.ui.components.AlertDialogComponent
import org.wifi.project.ui.components.SendNotification
import org.wifi.project.ui.page.MainPage
import org.wifi.project.ui.theme.AppTheme
import kotlin.time.Duration.Companion.milliseconds

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "nativeWifi",
        resizable = false,
        state = WindowState(width = 350.dp, height = 700.dp)
    ) {

        var theme by remember { mutableStateOf(true) }

        var connected by remember { mutableStateOf<ConnInfo?>(null) }
        val allConnections = remember { mutableStateListOf<ConnInfo>() }
        var openDialog by remember { mutableStateOf(false) }
        var connName by remember { mutableStateOf("") }
        var loadRefreshBtn by remember { mutableStateOf(false) }

        val toaster = rememberToasterState()


        fun getConnections() {

            loadRefreshBtn = !loadRefreshBtn

            connected = null
            allConnections.clear()

            val result: List<ConnInfo> = Command().executeCommand()

            result.forEach { conn ->
                if (conn.state == "yes") {
                    connected = conn
                } else {
                    allConnections.add(conn)
                }
            }

            toaster.show(
                message = "Load Wifi Data",
                type = ToastType.Success,
                duration = 2000.milliseconds,
            )

            loadRefreshBtn = !loadRefreshBtn
        }

        LaunchedEffect(Unit) {
            launch {
                getConnections()
            }
        }

        AppTheme(
            darkTheme = theme
        ) {
            Toaster(state = toaster, darkTheme = true)

            if (openDialog){
                AlertDialogComponent(
                    disMissReq = {
                        openDialog = !openDialog
                    },
                    connectionName = connName,
                    getConn = {
                        getConnections()
                    }
                )
            }

            MainPage(
                changeTheme = {
                    theme = !theme
                },
                theme = theme,
                connected = connected,
                allConnections = allConnections,
                refreshEvent = {
                    getConnections()
                },
                disconnect = {
                    connected?.let { Command().executeCommandDisconnect(connectionName = it.connection) }
                    Thread.sleep(2000)
                    getConnections()
                },
                connect = { name ->
                    val connectionSuccess = Command().executeCommandConnect(connectionName = name)
                    Thread.sleep(2000)
                    if (connectionSuccess) {
                        getConnections()
                    }else{
                        openDialog = !openDialog
                        connName = name
                    }
                },
                loadRefreshBtn = loadRefreshBtn
            )
        }
    }
}
