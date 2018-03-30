package example.kotlin.com.myapplication.model

import java.io.Serializable

/**
 * Created by Guest User on 3/28/2018.
 */

class Article : Serializable{
    var userId: Int =0
    var id: Int =0
    var title: String? = null
    var body: String? = null


}