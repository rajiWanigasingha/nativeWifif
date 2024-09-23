package org.wifi.project.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.wifi.project.ui.Command
import org.wifi.project.ui.ConnInfo
import org.wifi.project.ui.theme.JetBrainsFontBold
import org.wifi.project.ui.theme.JetBrainsFontMedium
import org.wifi.project.ui.theme.JetBrainsFontSemiBold


@Composable
fun AllConnection(
    connected: ConnInfo?,
    allConnections: MutableList<ConnInfo>,
    disconnect: () -> Unit,
    connect: (name:String) -> Unit
) {
    Box {
        Column(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
        ) {

            if (connected != null) {

                Row {
                    Text(
                        "Connected Wifi",
                        style = JetBrainsFontSemiBold(MaterialTheme.colorScheme.onBackground)
                    )
                }
                Spacer(Modifier.size(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth()
                            .border(
                                width = 1.dp,
                                brush = Brush.linearGradient(
                                    listOf(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.secondary
                                    )
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .shadow(8.dp),
                        colors = CardDefaults.cardColors(
                            contentColor = MaterialTheme.colorScheme.onBackground,
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        connected.connection,
                                        style = JetBrainsFontMedium(Color.Unspecified)
                                    )
                                }
                                Column {
                                    TextButton(onClick = {
                                        disconnect()
                                    }) {
                                        Text("Disconnect", color = MaterialTheme.colorScheme.error)
                                    }
                                }
                            }
                        }
                    }

                }

                Divider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            }

            Row {
                Text(
                    "All Connections",
                    style = JetBrainsFontSemiBold(MaterialTheme.colorScheme.onBackground)
                )
            }
            Spacer(Modifier.size(15.dp))

            Row {
                Column(
                    Modifier.verticalScroll(rememberScrollState())
                ) {
                    if (allConnections.isNotEmpty()) {
                        allConnections.forEach { conn ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ElevatedCard(
                                    modifier = Modifier.fillMaxWidth()
                                        .border(
                                            width = 1.dp,
                                            brush = Brush.linearGradient(
                                                listOf(
                                                    MaterialTheme.colorScheme.secondary,
                                                    MaterialTheme.colorScheme.tertiary
                                                )
                                            ),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .shadow(8.dp),
                                    colors = CardDefaults.cardColors(
                                        contentColor = MaterialTheme.colorScheme.onBackground,
                                        containerColor = MaterialTheme.colorScheme.background
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier.padding(
                                            horizontal = 20.dp,
                                            vertical = 10.dp
                                        )
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column {
                                                Text(
                                                    conn.connection,
                                                    style = JetBrainsFontMedium(Color.Unspecified)
                                                )
                                            }
                                            Column {
                                                TextButton(onClick = {
                                                    connect(conn.connection)
                                                }) {
                                                    Text(
                                                        "Connect",
                                                        color = MaterialTheme.colorScheme.primary
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            Spacer(Modifier.size(15.dp))
                        }
                    } else {

                       Column(
                           modifier = Modifier.fillMaxWidth().padding(20.dp),
                           horizontalAlignment = Alignment.CenterHorizontally
                       ) {
                           Row {
                               Text("No Connection Available" , style = JetBrainsFontSemiBold(MaterialTheme.colorScheme.secondary))
                           }
                       }

                    }
                }
            }
        }
    }
}