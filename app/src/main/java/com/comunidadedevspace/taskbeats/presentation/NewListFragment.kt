package com.comunidadedevspace.taskbeats.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comunidadedevspace.taskbeats.BuildConfig
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.local.local.News
import com.comunidadedevspace.taskbeats.data.local.remot.NewsResponse
import com.comunidadedevspace.taskbeats.data.local.remot.RetrofitModule
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [NewListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewListFragment : Fragment() {
 private val adapter = NewsListAdapter()
val viewModel by lazy { NewsListViewModel.creat() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvNews = view.findViewById<RecyclerView>(R.id.rv_news)
        rvNews.adapter = adapter

        viewModel.newslivedata.observe(viewLifecycleOwner){
           val newsList =  it.map {

                News(
                    title = it.titulo,
                    imgUrl = it.imagens
                )
            }
            adapter.submitList(newsList)
        }






    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */

        fun newInstance() = NewListFragment()

    }
}