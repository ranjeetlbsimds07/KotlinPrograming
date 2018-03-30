package example.kotlin.com.myapplication.api

import example.kotlin.com.myapplication.model.Article
import example.kotlin.com.myapplication.model.CategoryResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by Guest User on 3/28/2018.
 */
interface ApiInterface {
    @GET("?json=get_category_index")
    fun getCategoryDetails(): Call<CategoryResponse>

    companion object Factory {
        //val BASE_URL = "https://androidteachers.com/"
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
    /* Add new article */
    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("posts")
    fun addArticle(@Body article: Article): Call<Article>
    /* Get list of articles */
    @GET("posts")
    fun getArticles(): Call<List<Article>>
}