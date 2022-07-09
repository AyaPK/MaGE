package uk.co.ayaspace.mage.mainTabbedActivities.diary

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.utils.DataAccess
import uk.co.ayaspace.mage.utils.recyclyerUtils.DiaryRecyclerAdapter

class ViewDiaryEntry : AppCompatActivity() {
    lateinit var entryTitle: TextView
    lateinit var entryContent: TextView
    lateinit var entryImage: ImageView
    lateinit var entryDate: TextView
    lateinit var backButton: Button
    lateinit var deleteButton: Button
    lateinit var editButton: Button
    lateinit var dataAccess: DataAccess
    lateinit var entryID: Number

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_diary_entry)
        dataAccess = DataAccess(this)

        val entryTitleText: String = intent.getStringExtra("title").toString()
        val entryContentText: String = intent.getStringExtra("content").toString()
        val entryDateText: String = intent.getStringExtra("date").toString()
        val entryImageText: String = intent.getStringExtra("image").toString()
        entryID = intent.getIntExtra("entryID", -1)

        println(entryID)

        entryTitle = findViewById(R.id.diary_entry_title)
        entryContent = findViewById(R.id.entry_content_view)
        entryImage = findViewById(R.id.entry_image_view)
        entryDate = findViewById(R.id.diary_entry_date)
        backButton = findViewById(R.id.back_button)
        deleteButton = findViewById(R.id.delete_button)
        editButton = findViewById(R.id.edit_button)

        backButton.setOnClickListener {
            setResult(RESULT_OK, intent)
            finish()
        }

        deleteButton.setOnClickListener {
            dataAccess.deleteEntry(entryID as Int)
            setResult(RESULT_OK, intent)
            finish()
        }

        editButton.setOnClickListener {
            val intent : Intent = Intent(this, EditDiaryEntry::class.java)
            intent.putExtra("title", entryTitle.text.toString())
            intent.putExtra("content", entryContent.text.toString())
            intent.putExtra("date", entryDate.text.toString())
            intent.putExtra("id", entryID)
            getResultFromEditClicked.launch(intent)
        }


        if (entryImageText == "null") {
            entryImage.visibility = View.GONE
        }
        entryTitle.text = entryTitleText
        entryContent.text = entryContentText
        entryDate.text = entryDateText


    }

    private var getResultFromEditClicked = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val entry = dataAccess.getEntryByID(entryID as Int)
            entryTitle.text = entry.title
            entryContent.text = entry.content
            entryDate.text = entry.dateText
        }
    }
}