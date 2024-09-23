package org.wifi.project.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nativewifi.composeapp.generated.resources.Res
import nativewifi.composeapp.generated.resources.moon
import nativewifi.composeapp.generated.resources.sun
import nativewifi.composeapp.generated.resources.wifi
import org.wifi.project.ui.ConnInfo
import org.wifi.project.ui.components.AllConnection
import org.wifi.project.ui.theme.JetBrainsFontSemiBold

@Composable
fun MainPage(
    changeTheme: () -> Unit,
    theme: Boolean,
    connected: ConnInfo?,
    allConnections: MutableList<ConnInfo>,
    refreshEvent: () -> Unit,
    disconnect: () -> Unit,
    connect: (name:String) -> Unit,
    loadRefreshBtn: Boolean
) {

    println(loadRefreshBtn)

    @Composable
    fun themeIcon(theme: Boolean) {
        return if (theme) {
            Icon(
                painterResource(Res.drawable.sun),
                contentDescription = "Sun",
                Modifier.size(25.dp),
                tint = Color.Unspecified
            )
        } else {
            Icon(
                painterResource(Res.drawable.moon),
                contentDescription = "Moon",
                Modifier.size(25.dp),
                tint = Color.Unspecified
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,

        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column {
                            Icon(
                                painterResource(Res.drawable.wifi),
                                contentDescription = "Logo",
                                Modifier.size(25.dp)
                            )
                        }
                        Spacer(Modifier.size(8.dp))
                        Column {
                            Text("NetWork", style = JetBrainsFontSemiBold(Color.Unspecified, 22.sp))
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {
                        changeTheme()
                    }) {
                        themeIcon(theme)
                    }
                }
            )
        },

        floatingActionButton = {

            if (!loadRefreshBtn){
                FloatingActionButton(
                    onClick = {
                        refreshEvent()
                    },

                    ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "refresh", Modifier.size(25.dp))
                }
            }else {
                FloatingActionButton(
                    onClick = {},

                    ) {
                    CircularProgressIndicator(modifier = Modifier.size(30.dp))
                }
            }


        }
    ) {
        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            AllConnection(
                connected = connected,
                allConnections = allConnections,
                disconnect = disconnect,
                connect = connect
            )
        }
    }
}