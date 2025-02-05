package com.example.firstappunitconvertor

import android.icu.text.AlphabeticIndex.Bucket.LabelType
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstappunitconvertor.ui.theme.FirstappunitconvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstappunitconvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    UnitConvertor()

                }

            }
        }
    }
}
@Composable
fun UnitConvertor(){
    var inputValue by remember { mutableStateOf("")}
    var outputValue by  remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("meters")}
    var outputUnit by remember { mutableStateOf("meters")}
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false)}
    val conversionFactor = remember { mutableStateOf(1.00)}
    val oConversionFactor = remember { mutableStateOf(1.00)}
    
    val CustomTextstyle =TextStyle(fontFamily = FontFamily.Default, fontSize = 25.sp, fontWeight = FontWeight.Bold,color = Color.Black)
            


    fun convertunits(){
        //if the value is not a number or invalid then it takes 0 as default
        val inputValueDouble=inputValue.toDoubleOrNull() ?: 0.0
        val result =(inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }
    Column(modifier=Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment =Alignment.CenterHorizontally )
    {
         Text(text = "UNIT CONVERTOR",style=CustomTextstyle )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertunits()
        }, label = { Text(text = "Enter the Value",fontWeight = FontWeight.Bold)})




        Row {
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "down arrow")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem({ Text(text = "centimetres") }, onClick = {
                        iExpanded=false
                        inputUnit="centimetres"
                        conversionFactor.value=0.01
                        convertunits()

                    }
                    )
                    DropdownMenuItem(text = { Text(text = "meters") }, onClick = {
                        iExpanded=false
                        inputUnit="metres"
                        conversionFactor.value=1.0
                        convertunits()
                    }
                    )
                    DropdownMenuItem(text = { Text(text = "millimeters") }, onClick = {
                        iExpanded=false
                        inputUnit="millimetres"
                        conversionFactor.value=0.001
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "kilometers") }, onClick = {
                        iExpanded=false
                        inputUnit="kilometres"
                        conversionFactor.value=2.0
                        convertunits()
                    })
                }
            }

            Spacer(modifier = Modifier.width(16. dp))
            Box {
                Button(onClick = { oExpanded=true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "down arrow")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "centimeters") },
                        onClick = {
                            oExpanded=false
                            outputUnit="centimeters"
                            oConversionFactor.value=0.01
                            convertunits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "meters") },
                        onClick = {
                            oExpanded=false
                            outputUnit="metres"
                            oConversionFactor.value=1.00
                            convertunits()

                        }
                    )
                    DropdownMenuItem(text = { Text(text = "millimeters") },
                        onClick = {
                            oExpanded=false
                            outputUnit="millimetres"
                            oConversionFactor.value=0.001
                            convertunits()
                        })
                    DropdownMenuItem(text = { Text(text = "kilometers") },
                        onClick = {
                            oExpanded=false
                            outputUnit="kilometres"
                            oConversionFactor.value=2.00
                            convertunits()
                        })
                }
            }


        }
        Text(text="Result:$outputValue $outputUnit",style=CustomTextstyle)
    }

}


@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview(){
    UnitConvertor()

}


