package com.naky.roomdatabases

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.naky.roomdatabases.entity.SampleEntity
import com.naky.roomdatabases.ui.theme.RoomDatabasesTheme
import com.naky.roomdatabases.viewmodel.SampleViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
val strDate: String = date.format(Date())

val insertSampleData = listOf(
    SampleEntity(1, "Sample 1", "Naky 1", "Image url 1", strDate),
    SampleEntity(2, "Sample 2", "Naky 2", "Image url 2", strDate),
    SampleEntity(3, "Sample 3", "Naky 3", "Image url 3", strDate),
    SampleEntity(4, "Sample 4", "Naky 4", "Image url 4", strDate),
    SampleEntity(5, "Sample 5", "Naky 5", "Image url 5", strDate),
    SampleEntity(6, "Sample 6", "Naky 6", "Image url 6", strDate),
    SampleEntity(7, "Sample 7", "Naky 7", "Image url 7", strDate),
    SampleEntity(8, "Sample 8", "Naky 8", "Image url 8", strDate),
    SampleEntity(9, "Sample 9", "Naky 9", "Image url 9", strDate),
    SampleEntity(10, "Sample 10", "Naky 10", "Image url 10", strDate)


)
val updateItem = SampleEntity(11, "Sample 11", "Naky 11", "Image url 11", strDate)
@Composable
fun CallDatabase(
    modifier: Modifier = Modifier,
) {
    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    val context = LocalContext.current
    val currentWidth =
        context.resources.displayMetrics.widthPixels.dp / LocalDensity.current.density

    val sampleViewModel: SampleViewModel = viewModel()
//    sampleViewModel.addSample(insertSampleData)

    val getAllRecord = sampleViewModel.readAllData.observeAsState(initial = listOf()).value
//    var getById = sampleViewModel.getById(1)

    Scaffold(
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Room Databases",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600),
                        color = MaterialTheme.colors.onBackground
                    ))
            }
        }
    ) {
        LaunchedEffect(isRefreshing) {
            if (isRefreshing) {
                delay(1000L)
                isRefreshing= false
            }
        }
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                isRefreshing = true
            },
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                item {
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val widthColum = (currentWidth-32.dp)/5
                        Row(
                            modifier = modifier
                                .width(widthColum/3)
                        ) {
                            Text(text = "Id",
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    color = MaterialTheme.colors.onBackground
                                ))
                        }
                        Row(
                            modifier = modifier
                                .width(widthColum)
                        ) {
                            Text(text = "Name",
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    color = MaterialTheme.colors.onBackground
                                ))
                        }
                        Row(
                            modifier = modifier
                                .width(widthColum)
                        ) {
                            Text(text = "Description",
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    color = MaterialTheme.colors.onBackground
                                ))
                        }
                        Row(
                            modifier = modifier
                                .width(widthColum)
                        ) {
                            Text(text = "Image Url",
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    color = MaterialTheme.colors.onBackground
                                ))
                        }
                        Row(
                            modifier = modifier
                                .width(widthColum)
                        ) {
                            Text(text = "Create Date",
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    color = MaterialTheme.colors.onBackground
                                ))
                        }
                    }
                }

//                item {
//                    CardListItem(data = getById, onDelete = {})
//                }
                items(
                    count = getAllRecord.size
                ){ index ->
                    if(getAllRecord.isNotEmpty()){
                        CardListItem(
                            data = getAllRecord[index],
                            onDelete ={
                                    sampleViewModel.updateSample(it)

                            }
                        )
                    }
                }
            }
        }

    }

}

@Composable
fun CardListItem(
    modifier: Modifier =Modifier,
    data : SampleEntity?,
    onDelete : (sample : SampleEntity) -> Unit
){
    val currentWidth = LocalContext.current.resources.displayMetrics.widthPixels.dp/ LocalDensity.current.density
    if (data != null) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    if (data != null) {
                        onDelete(SampleEntity(
                            id = data.id,
                            name = "${data.name} edit",
                            desc = data.desc,
                            imageUrl = data.imageUrl,
                            createDate = "10-03-2022"
                        ))
                    }
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val widthColum = (currentWidth-32.dp)/5
            Row(
                modifier = modifier
                    .width(widthColum/3)
            ) {
                Text(text = data.id.toString(),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colors.onBackground
                    ))
            }
            Row(
                modifier = modifier
                    .width(widthColum)
            ) {
                Text(text = data.name,
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colors.onBackground
                    ))
            }
            Row(
                modifier = modifier
                    .width(widthColum)
            ) {
                Text(text = data.desc,
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colors.onBackground
                    ))
            }
            Row(
                modifier = modifier
                    .width(widthColum)
            ) {
                Text(text = data.imageUrl,
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colors.onBackground
                    ))
            }
            Row(
                modifier = modifier
                    .width(widthColum)
            ) {
                Text(text = data.createDate,
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colors.onBackground
                    ))
            }
        }
    }
}