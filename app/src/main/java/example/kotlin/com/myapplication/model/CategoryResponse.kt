package example.kotlin.com.myapplication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Guest User on 3/28/2018.
 */
class CategoryResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("categories")
    var categories: ArrayList<Category>? = null
}