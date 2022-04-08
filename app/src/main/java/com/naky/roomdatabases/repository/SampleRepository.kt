package com.naky.roomdatabases.repository

import androidx.lifecycle.LiveData
import com.naky.roomdatabases.dao.SampleDao
import com.naky.roomdatabases.entity.SampleEntity

class SampleRepository(val sampleDao: SampleDao) {

    val readAllData: LiveData<List<SampleEntity>> = sampleDao.getAllData()

    suspend fun getById(id : Int) : SampleEntity? {
        val data = sampleDao.getById(id)
        return data
    }

    suspend fun addSample(item: List<SampleEntity>) {
        sampleDao.insert(item)
    }

    suspend fun updateSample(item: SampleEntity) {
        sampleDao.update(item)
    }

    suspend fun deleteSample(item: SampleEntity) {
        sampleDao.delete(item)
    }

    suspend fun deleteAllRecord() {
        sampleDao.deleteAllRecord()
    }
}