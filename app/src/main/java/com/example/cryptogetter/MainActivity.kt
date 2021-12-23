package com.example.cryptogetter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptogetter.Adapter.RecylerViewAdapter
import com.example.cryptogetter.Model.CryptoModel
import com.example.cryptogetter.Service.MyApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecylerViewAdapter.Listener {

    /*
    API URL
    https://raw.githubusercontent.com/
    atilsamancioglu/K21-JSONDataSet/master/crypto.json
     */
    private val BASE_URL = " https://raw.githubusercontent.com/"
    private var cryptoList: ArrayList<CryptoModel>? = null

    private var recylerViewAdapter: RecylerViewAdapter? = null
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()

        layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerView)

    }


    private fun loadData() {
        // retrofit oluşturma
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MyApi::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                //response
                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoList = ArrayList(it)
                        recylerViewAdapter = RecylerViewAdapter(cryptoList!!, this@MainActivity)
                        recyclerView.adapter = recylerViewAdapter
                        recyclerView.layoutManager = layoutManager

                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    override fun onItemClickListener(model: CryptoModel) {
        Toast.makeText(this,""+cryptoList!!.get(0),Toast.LENGTH_SHORT).show()
    }


}