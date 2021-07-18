package com.example.doggiefacts

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        factTV.text = "Choose an animal to get a random fact about them"
    }

    @SuppressLint("SetTextI18n")
    fun catfact(view: View) {
        val url = "https://catfact.ninja/fact?max_length=300"
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                Log.d("Fact", response.optString("fact"))
                factTV.text = response.getString("fact")
                animalTV.text = "Cat fact of the day -"
            },
            { error -> error.printStackTrace()
            }
        )
        //Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }


    @SuppressLint("SetTextI18n")
    fun dogfact(view: View) {
        val url = "https://dog-facts-api.herokuapp.com/api/v1/resources/dogs?number=1"

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                Log.d("Fact", response.optString(0))
                factTV.text = response.getJSONObject(0).getString("fact")
                animalTV.text = "Dog fact of the day -"
            },
            { error -> error.printStackTrace()
            }
        )
        //Add the request to the RequestQueue.
        queue.add(jsonArrayRequest)
    }

}