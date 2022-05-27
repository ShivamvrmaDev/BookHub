package com.example.test

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Books")
class Books (


    @PrimaryKey(autoGenerate = true) var id :String,
    @ColumnInfo(name="Book_name")  var name:String,
    @ColumnInfo(name="writer")   var writer :String,
    @ColumnInfo(name="price")     var price:String,
    @ColumnInfo(name="desc")     var desc :String,
    @ColumnInfo(name="image")    var image:String,
    @ColumnInfo(name="ratings")     var rating:String
)