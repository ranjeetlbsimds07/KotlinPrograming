package example.kotlin.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import example.kotlin.com.myapplication.helper.AppConstants
import example.kotlin.com.myapplication.model.Article

class NoteDetailsActivity : AppCompatActivity() {

    lateinit var article:Article
    lateinit var tvid: TextView
    lateinit var tvTitle: TextView
    lateinit var tvBody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        article = intent.extras.get(AppConstants.ORTICALDETAILS) as Article
        tvid = findViewById<TextView>(R.id.tvid) as TextView
        tvTitle = findViewById<TextView>(R.id.tvTitle) as TextView
        tvBody = findViewById<TextView>(R.id.tvBody) as TextView

        tvid.text = article.userId.toString();
        tvTitle.text = article.title.toString();
        tvBody.text = article.body.toString();


    }
}
