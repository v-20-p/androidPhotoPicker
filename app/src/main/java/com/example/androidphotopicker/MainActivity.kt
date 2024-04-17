package com.example.androidphotopicker

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.androidphotopicker.ui.theme.AndroidPhotoPickerTheme
import java.util.jar.Pack200.Packer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPhotoPickerTheme {




                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var imageUri=remember{
                        mutableStateOf<Uri?>(null)

                    }
                    var imageUris=remember{
                        mutableStateOf<List<Uri>>(emptyList())
                    }
                    var launcherForSingleMedia= rememberLauncherForActivityResult(
                        ActivityResultContracts.PickVisualMedia()
                    ){uri:Uri? ->
                        imageUri.value=uri
                    }
                    LazyColumn{
                        item{
                            Row {
                                Button(onClick = {
                                    launcherForSingleMedia.launch((
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                            ))
                                }) {
                                    Text(text = "single image")

                                }
                            }
                            Button(onClick = {
                                launcherForSingleMedia.launch((
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                        ))
                            }) {
                                Text(text = "multiple image")
                            }
                        }
                        item{
                            AsyncImage(model = imageUri.value, contentDescription =null,
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        item{
                            AsyncImage(model = imageUris.value, contentDescription =null,
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )

                        }
                    }







                }
            }
        }
    }
    fun checklocationPermisson(){
        if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )!=PackageManager.PERMISSION_GRANTED
            ){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
        }else{
            Toast.makeText(this,"Perrd",Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidPhotoPickerTheme {
        Greeting("Android")
    }
}
