package com.example.myapplication

import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert
    fun Insert(books: Books)

    @Update
    fun Update(books: Books)

    @Delete
    fun Delete(books: Books)

    @Query( "SELECT * FROM Books")
    fun read():List<Books>

    @Query("SELECT * FROM Books where id = :bookId" )
    fun getBooks(bookId: String):Books


    @Insert
    fun Insert(biography: Biography)

    @Update
    fun Update(biography: Biography)

    @Delete
    fun Delete(biography: Biography)

    @Query( "SELECT * FROM Biography")
    fun readbio():List<Biography>

    @Query("SELECT * FROM Biography where id = :bookId" )
    fun getBooksbio(bookId: String):Biography


}