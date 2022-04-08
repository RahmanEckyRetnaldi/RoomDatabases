package com.naky.roomdatabases

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.naky.roomdatabases.entity.SampleEntity
import com.naky.roomdatabases.ui.theme.RoomDatabasesTheme
import com.naky.roomdatabases.viewmodel.SampleViewModel
import com.naky.roomdatabases.viewmodel.SampleViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDatabasesTheme {
                CallDatabase()
                }
            }
        }
}

val date = SimpleDateFormat("dd-MM-yyyy")
val strDate : String = date.format(Date())

val insertSampleData = listOf(
    SampleEntity(1,"Sample 1", "Naky 1", "Image url 1", strDate),
    SampleEntity(2,"Sample 2", "Naky 2", "Image url 2", strDate),
    SampleEntity(3,"Sample 3", "Naky 3", "Image url 3", strDate),
    SampleEntity(4,"Sample 4", "Naky 4", "Image url 4", strDate),
    SampleEntity(5,"Sample 5", "Naky 5", "Image url 5", strDate),
    SampleEntity(6,"Sample 6", "Naky 6", "Image url 6", strDate),
    SampleEntity(7,"Sample 7", "Naky 7", "Image url 7", strDate),
    SampleEntity(8,"Sample 8", "Naky 8", "Image url 8", strDate),
    SampleEntity(9,"Sample 9", "Naky 9", "Image url 9", strDate),
    SampleEntity(10,"Sample 10", "Naky 10", "Image url 10", strDate)



)

@Composable
fun CallDatabase(){
    val context = LocalContext.current
    val sampleViewModel : SampleViewModel = viewModel(
        factory = SampleViewModelFactory(context.applicationContext as Application)
    )
    sampleViewModel.addSample(insertSampleData)
}
