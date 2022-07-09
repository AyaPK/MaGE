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

//    public fun insertData(bookname: String, childcomments: String, adultcomments: String, rating: Int, pagesread: Int, datetext: String): Long {
//        val dbb: SQLiteDatabase = helper.writableDatabase
//        val contentValues: ContentValues = ContentValues()
//        contentValues.put(helper.BOOKNAME, bookname)
//        contentValues.put(helper.CHILDCOMMENTS, childcomments)
//        contentValues.put(helper.ADULTCOMMENTS, adultcomments)
//        contentValues.put(helper.RATING, rating)
//        contentValues.put(helper.PAGESREAD, pagesread)
//        contentValues.put(helper.DATETEXT, datetext)
//        return dbb.insert(helper.TABLE_NAME, null, contentValues)
//    }

    public fun insertDiaryEntry(entry: Entry): Long {
        val dbb: SQLiteDatabase = helper.writableDatabase
        val contentValues: ContentValues = ContentValues()
        val entryObject = Gson().toJson(entry)
        contentValues.put(helper.objectString, entryObject)
        return dbb.insert("Entries", null, contentValues)
    }

    public fun getAllDiaryEntries() : ArrayList<Entry> {
        val dbb = helper.writableDatabase
        val columns = arrayOf(helper.uid, helper.objectString)
        val cursor: Cursor = dbb.query("Entries",columns,null,null,null,null,null)
        val buffer: ArrayList<Entry> = ArrayList<Entry>()
        while (cursor.moveToNext()) {
            val entry : Entry = Gson().fromJson(cursor.getString(cursor.getColumnIndexOrThrow(helper.objectString)), Entry::class.java)
            buffer.add(entry)
        }
        return buffer
    }

//
//    public fun getAllBooks() : ArrayList<String> {
//        val dbb = helper.writableDatabase
//        val columns = arrayOf(helper.UID, helper.BOOKNAME, helper.CHILDCOMMENTS, helper.ADULTCOMMENTS, helper.RATING, helper.PAGESREAD, helper.DATETEXT)
//        val cursor: Cursor = dbb.query(helper.TABLE_NAME,columns,null,null,null,null,null)
//        val buffer: ArrayList<String> = ArrayList<String>()
//        while (cursor.moveToNext()) {
//            val cid: Int = cursor.getInt(cursor.getColumnIndexOrThrow(helper.UID))
//            val bookname: String = cursor.getString(cursor.getColumnIndexOrThrow(helper.BOOKNAME))
//            val childcomments: String = cursor.getString(cursor.getColumnIndexOrThrow(helper.CHILDCOMMENTS))
//            val adultcomments: String = cursor.getString(cursor.getColumnIndexOrThrow(helper.ADULTCOMMENTS))
//            val rating: String = cursor.getString(cursor.getColumnIndexOrThrow(helper.RATING))
//            val pagesread: String = cursor.getString(cursor.getColumnIndexOrThrow(helper.PAGESREAD))
//            val datetext: String = cursor.getString(cursor.getColumnIndexOrThrow(helper.DATETEXT))
//            buffer.add("$cid|$bookname|$childcomments|$adultcomments|$rating|$pagesread|$datetext\n")
//        }
//        return buffer
//    }
//
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

    public fun deleteEntry(id: Int) {
        val dbb = helper.writableDatabase
        dbb.delete("choices", "_id=$id", null)

    }

    class MyDbHelper(
        var context: Context,
        private val DATABASE_VERSION: Int = 1,
        private val DATABASE_NAME: String = "myDatabase",
        val TABLE_NAME: String = "choices",
        val uid: String = "id",
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