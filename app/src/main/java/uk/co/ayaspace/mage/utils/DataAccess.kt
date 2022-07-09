package uk.co.ayaspace.mage.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.gson.Gson
import uk.co.ayaspace.mage.model.Entry
import java.lang.Exception


class DataAccess(private val context: Context) {
    var helper: MyDbHelper = MyDbHelper(context)
    var gson: Gson = Gson()

    public fun insertDiaryEntry(entry: Entry): Long {
        val dbb: SQLiteDatabase = helper.writableDatabase
        val contentValues: ContentValues = ContentValues()
        val entryObject = gson.toJson(entry)
        contentValues.put(helper.objectString, entryObject)
        return dbb.insert("Entries", null, contentValues)
    }

    public fun getAllDiaryEntries() : ArrayList<Entry> {
        val dbb = helper.writableDatabase
        val columns = arrayOf(helper.id, helper.objectString)
        val cursor: Cursor = dbb.query("Entries",columns,null,null,null,null,null)
        val buffer: ArrayList<Entry> = ArrayList<Entry>()
        while (cursor.moveToNext()) {
            val entry : Entry = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(helper.objectString)), Entry::class.java)
            entry.entryID = cursor.getString(cursor.getColumnIndexOrThrow(helper.id)).toInt()
            buffer.add(entry)
        }
        return buffer
    }

    public fun deleteEntry(id: Int) {
        val dbb = helper.writableDatabase
        dbb.delete("Entries", "id=$id", null)
    }

    public fun getEntryByID(id: Int): Entry {
        val dbb = helper.writableDatabase
        val columns = arrayOf(helper.id, helper.objectString)
        val cursor: Cursor = dbb.query("Entries",columns,"id=$id",null,null,null,null)
        val buffer: ArrayList<Entry> = ArrayList<Entry>()
        while (cursor.moveToNext()) {
            val entry : Entry = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(helper.objectString)), Entry::class.java)
            entry.entryID = cursor.getString(cursor.getColumnIndexOrThrow(helper.id)).toInt()
            buffer.add(entry)
        }
        return buffer[0]
    }

    public fun updateEntry(entry: Entry) {
        val dbb = helper.writableDatabase
        val updatedEntry = ContentValues()
        val id = entry.entryID.toInt()
        updatedEntry.put(helper.id, id)
        updatedEntry.put(helper.objectString, gson.toJson(entry))
        dbb.update("Entries", updatedEntry,"id=$id", null)
    }

//    public fun updateEntry(id: Int, name: String, childcomments: String, adultcomment: String, rating: Int, pagesread: Int, datetext: String) {
//        val dbb = helper.writableDatabase
//        val updatedEntry = ContentValues()
//        updatedEntry.put("name", name)
//        updatedEntry.put("childcomments", childcomments)
//        updatedEntry.put("adultcomments", adultcomment)
//        updatedEntry.put("rating", rating)
//        updatedEntry.put("pagesread", pagesread)
//        updatedEntry.put("datetext", datetext)

//        dbb.update("choices", updatedEntry, "_id=$id", null)
//         }


    class MyDbHelper(
        var context: Context,
        private val DATABASE_VERSION: Int = 1,
        private val DATABASE_NAME: String = "myDatabase",
        val TABLE_NAME: String = "choices",
        val id: String = "id",
        val objectString: String = "objectString",
        val cloudStorage: String = "CloudStorage",
        val timeFormat: String = "TimeFormat",
        val dateFormat: String = "DateFormat",

        private val CREATE_ENTRIES_TABLE: String = "CREATE TABLE Entries (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "objectString VARCHAR(255)" +
                ");",

        private val CREATE_SETTINGS_TABLE: String = "CREATE TABLE Settings (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CloudStorage VARCHAR(255), " +
                "TimeFormat VARCHAR(255), " +
                "DateFormat VARCHAR(255)" +
                ");",

        private val DROP_USER_TABLE: String = "DROP TABLE IF EXISTS UserData",
        private val DROP_SETTINGS_TABLE: String = "Drop TABLE IF EXISTS Settings"

    ) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase?) {
            try {
                db?.execSQL(CREATE_ENTRIES_TABLE)
//                db?.execSQL(CREATE_SETTINGS_TABLE)
            } catch (e: Exception) {
                println("$e")
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            try {
                db?.execSQL(DROP_USER_TABLE)
                db?.execSQL(DROP_SETTINGS_TABLE)
                onCreate(db)
            } catch (e: Exception) {
                println("$e")
            }
        }

    }
}