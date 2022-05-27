package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Books::class,Biography::class],version = 1)
abstract class DbClass : RoomDatabase() {

    abstract fun daomethods():Dao

}