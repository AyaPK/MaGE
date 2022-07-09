package uk.co.ayaspace.mage.mainTabbedActivities.diary

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import uk.co.ayaspace.mage.R

class ViewDiaryEntry : AppCompatActivity() {
    lateinit var entryTitle: TextView
    lateinit var entryContent: TextView
    lateinit var entryImage: ImageView
    lateinit var entryDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_diary_entry)

        val entryTitleText: String = intent.getStringExtra("title").toString()
        val entryContentText: String = intent.getStringExtra("content").toString()
        val entryDateText: String = intent.getStringExtra("date").toString()
        val entryImageText: String = intent.getStringExtra("image").toString()

        entryTitle = findViewById(R.id.diary_entry_title)
        entryContent = findViewById(R.id.entry_content_view)
        entryImage = findViewById(R.id.entry_image_view)
        entryDate = findViewById(R.id.diary_entry_date)


        if (entryImageText == "null") {
            entryImage.visibility = View.GONE
        }
        entryTitle.text = entryTitleText
        entryContent.text = entryContentText
        entryDate.text = entryDateText

    }
}