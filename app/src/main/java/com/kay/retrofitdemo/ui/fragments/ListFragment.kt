package com.kay.retrofitdemo.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kay.retrofitdemo.data.ApiInterface
import com.kay.retrofitdemo.data.MyDataItem
import com.kay.retrofitdemo.databinding.FragmentListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Something something
        getMyData()

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()/*our Gson dependency*/)
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        // Create a new variable that get the data from our retrofitBuilder
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(/*ctl,shift,space*/object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                // this variable hold the response body
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                // we need to fetch the response body below
                for (myData in responseBody) {
                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                }
                binding.tvId.text = myStringBuilder
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("listFragment", "onFailure" + t.message)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}