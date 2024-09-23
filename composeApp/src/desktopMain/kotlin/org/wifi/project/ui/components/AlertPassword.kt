package org.wifi.project.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.wifi.project.ui.Command
import org.wifi.project.ui.theme.JetBrainsFontBold

@Composable
fun AlertDialogComponent(
    disMissReq: () -> Unit,
    connectionName: String,
    getConn: () -> Unit
) {

    var textFieldValue by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Dialog(
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
        ),
        onDismissRequest = {
            disMissReq()
        },
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().border(0.5.dp , color = MaterialTheme.colorScheme.secondary , shape = RoundedCornerShape(10.dp)),
            backgroundColor = MaterialTheme.colorScheme.scrim,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Row {
                    Text("Enter Your Wifi Password" , style = JetBrainsFontBold(MaterialTheme.colorScheme.onBackground ,16.sp))
                }
                Spacer(Modifier.size(8.dp))
                Row {
                    OutlinedTextField(
                        value = textFieldValue,
                        onValueChange = {
                            textFieldValue = it
                        },
                        placeholder = {
                            Text("Your Password")
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
                            focusedTextColor = MaterialTheme.colorScheme.onBackground
                        ),
                        isError = error,
                        supportingText = {
                            if (!error){
                                Text("Enter your router password")
                            }else {
                                Text("Wrong Password, Please try again" , color = MaterialTheme.colorScheme.error)
                            }
                        }
                    )
                }
                Spacer(Modifier.size(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Column {
                        TextButton(
                            onClick = {
                                disMissReq()
                            }
                        ) {
                            Text("Dismiss", color = MaterialTheme.colorScheme.error)
                        }
                    }
                    Spacer(Modifier.size(8.dp))
                    Column {
                        TextButton(
                            onClick = {
                                val success = Command().executeCommandConnectWithPassword(connectionName , password = textFieldValue)

                                if (success){
                                    Thread.sleep(2000)
                                    getConn()
                                    disMissReq()
                                }else {
                                    error = true
                                    textFieldValue = ""
                                }


                            }
                        ) {
                            Text("Confirm" , color = Color.Green)
                        }
                    }
                }
            }
        }
    }
}