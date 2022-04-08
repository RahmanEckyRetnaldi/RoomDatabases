package com.naky.roomdatabases.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.naky.roomdatabases.dao.database.SampleDatabase
import com.naky.roomdatabases.entity.SampleEntity
import com.naky.roomdatabases.repository.SampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SampleViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData : LiveData<List<SampleEntity>>
    private val repository : SampleRepository

    init {
        val sampleDao = SampleDatabase.getInstance(application).sampleDao()
        repository = SampleRepository(sampleDao = sampleDao)
        readAllData = repository.readAllData
    }

    fun addSample(item : List<SampleEntity>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSample(item)
        }
    }

    fun updateSample(item: SampleEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSample(item)
        }
    }

    fun deleteSample(item: SampleEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSample(item)
        }
    }

    fun deleteAllRecord(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllRecord()
        }
    }

}

//class SampleViewModelFactory(val application: Application): ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//    @Suppress("UNCHECKED_CAST")
//    if(modelClass.isAssignableFrom(SampleViewModel::class.java)){
//        return SampleViewModel(application) as T
//    }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

