package example.kotlin.com.myapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import example.kotlin.com.myapplication.R
import example.kotlin.com.myapplication.model.Article
import java.util.*

/**
 * Created by Guest User on 3/29/2018.
 */
class NotesAdapter : BaseAdapter {
    private var context: Context? = null
    private var articleList = ArrayList<Article>()
    private lateinit var btnClickListner : BtnClickListener;

    constructor(context: Context, articleList: ArrayList<Article>, btnClickListner : BtnClickListener) : super() {
        this.context = context
        this.articleList = articleList
        this.btnClickListner =btnClickListner
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ViewHolder
        if (convertView == null) {
            view = convertView ?: LayoutInflater.from(context).inflate(R.layout.row_note, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }
        vh.tvTitle.text = articleList[position].title
        vh.tvContent.text = articleList[position].body

        vh.llNote.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (btnClickListner != null)
                    btnClickListner?.onBtnClick(articleList[position])
            }
        })
        return view

    }

    override fun getItem(position: Int): Any {
        return articleList[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
       return articleList.size
    }

    private class ViewHolder(view: View?) {

        val tvTitle: TextView
        val tvContent: TextView
        val llNote: LinearLayout

        init {
            this.tvTitle = view?.findViewById<TextView>(R.id.tvTitle) as TextView
            this.tvContent = view?.findViewById<TextView>(R.id.tvContent) as TextView
            this.llNote = view?.findViewById<LinearLayout>(R.id.llNote) as LinearLayout
        }


    }
    open interface BtnClickListener {
        fun onBtnClick(position: Article)
    }
}