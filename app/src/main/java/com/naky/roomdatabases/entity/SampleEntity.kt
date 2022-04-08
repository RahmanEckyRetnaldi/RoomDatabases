package com.naky.roomdatabases.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SampleData")
data class SampleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "desc")
    var desc : String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl : String,

    @ColumnInfo(name = "createDate")
    var createDate : String
)
