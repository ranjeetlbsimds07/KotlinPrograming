package example.kotlin.com.myapplication

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import example.kotlin.com.myapplication.api.ApiInterface
import example.kotlin.com.myapplication.helper.AppConstants
import example.kotlin.com.myapplication.model.Article
import example.kotlin.com.myapplication.model.Category
import example.kotlin.com.myapplication.model.CategoryResponse
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Serializable
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    //private var et_user_name = null;
    val client = OkHttpClient()
    lateinit var list:List<Article>
    lateinit var pDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var et_user_name = findViewById<EditText>(R.id.et_user_name) as EditText
        var et_password = findViewById<EditText>(R.id.et_password) as EditText
        var btn_reset = findViewById<Button>(R.id.btn_reset) as Button
        var btn_submit = findViewById<Button>(R.id.btn_submit) as Button

        btn_submit.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        DisplayProgressDialog()
        fethdetails()


    }

    private fun fethdetails() {
        val apiService = ApiInterface.create()
        val call = apiService.getArticles();
        call.enqueue(object : retrofit2.Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>?, response: Response<List<Article>>?) {
                if (response != null) {
                    if (pDialog != null && pDialog!!.isShowing()) {
                        pDialog.dismiss()
                    }
                     list = response.body()!!

                }
            }

            override fun onFailure(call: Call<List<Article>>?, t: Throwable?) {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.dismiss()
                }
            }

        })
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_submit -> {
                    val user_name = et_user_name.text;
                    val password = et_password.text;
                    DisplayProgressDialog()
                    val article = Article();
                    article.userId = 1;
                    article.id = 101
                    article.title = "Test Article"
                    article.body = "Have fun posting"

                    postArticle(article)
                }
                R.id.btn_reset -> {
                    //Toast.makeText(this, "btn_reset", Toast.LENGTH_SHORT).show()
                    //DisplayProgressDialog()
                    //callWebService()
                    val intent: Intent = Intent(this@LoginActivity, DashBoardActivity::class.java)
                    startActivity(intent)
                }

            }
        }
    }

    private fun postArticle(article: Article) {
        val apiService = ApiInterface.create()
        val call = apiService.addArticle(article);
        call.enqueue(object : retrofit2.Callback<Article> {
            override fun onFailure(call: Call<Article>?, t: Throwable?) {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.dismiss()
                }
            }

            override fun onResponse(call: Call<Article>?, response: Response<Article>?) {
                if (response != null) {
                    if (pDialog != null && pDialog!!.isShowing()) {
                        pDialog.dismiss()
                    }
                    val intent: Intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra(AppConstants.LIST_DATA,list as Serializable)
                    startActivity(intent)
                    Toast.makeText(this@LoginActivity, "List of Category  \n  " + response, Toast.LENGTH_LONG).show()
                    txtDisplay.setText(response.body().toString() + "")
                }
            }

        })
    }

    private fun callWebService() {
        val apiService = ApiInterface.create()
        val call = apiService.getCategoryDetails()
        Log.d("REQUEST", call.toString() + "")
        call.enqueue(object : retrofit2.Callback<CategoryResponse> {
            override fun onFailure(call: Call<CategoryResponse>?, t: Throwable?) {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.dismiss()
                }
            }

            override fun onResponse(call: Call<CategoryResponse>?, response: Response<CategoryResponse>?) {
                if (response != null) {
                    if (pDialog != null && pDialog!!.isShowing()) {
                        pDialog.dismiss()
                    }
                    var list: List<Category> = response.body()!!.categories!!
                    Log.d("MainActivity", "" + list.size)
                    var msg: String = ""
                    for (item: Category in list.iterator()) {
                        msg = msg + item.title + "\n"
                    }
                    //Toast.makeText(this@LoginActivity, "List of Category  \n  " + msg, Toast.LENGTH_LONG).show()
                    //txtDisplay.setText(msg + "")

                }
            }

        })

    }


    private fun DisplayProgressDialog() {
        pDialog = ProgressDialog(this@LoginActivity)
        pDialog.setMessage("Loading..")
        pDialog.setCancelable(false)
        pDialog.isIndeterminate = false
        pDialog.show()
    }

}

