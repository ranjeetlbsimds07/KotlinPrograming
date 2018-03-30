package example.kotlin.com.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import example.kotlin.com.myapplication.adapter.NotesAdapter
import example.kotlin.com.myapplication.helper.AppConstants
import example.kotlin.com.myapplication.model.Article
import java.io.Serializable

class MainActivity : AppCompatActivity(), NotesAdapter.BtnClickListener {


    lateinit var list:List<Article>
    lateinit var lvNotes : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = intent.extras.get(AppConstants.LIST_DATA) as ArrayList<Article>
        Log.d("list",list.toString())
        lvNotes = findViewById(R.id.lvNotes)
        var notesAdapter = NotesAdapter(this, list as ArrayList<Article>,this)
        lvNotes.adapter = notesAdapter
    }

    override fun onBtnClick(article: Article) {
       Log.d("test","==test=="+article);
        val intent: Intent = Intent(this@MainActivity, NoteDetailsActivity::class.java)
        intent.putExtra(AppConstants.ORTICALDETAILS,article)
        startActivity(intent)
    }
}
