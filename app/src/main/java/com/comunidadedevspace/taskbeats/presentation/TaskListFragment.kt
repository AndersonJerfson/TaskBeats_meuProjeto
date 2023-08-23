package com.comunidadedevspace.taskbeats.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.Task

/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {

    private lateinit var ctn_estadovazio: LinearLayout

    val adapter: TaskListAdpter = TaskListAdpter(::opemtaskdetail)

    private val viewModel  by lazy {
        TaskListViewModel.creat(requireActivity().application)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview: RecyclerView = view.findViewById(R.id.rv_tasklist)
        recyclerview.adapter = adapter

        ctn_estadovazio = view.findViewById(R.id.ctn_estadovazio)

    }

    override fun onStart() {
        super.onStart()

        listFromDataBase()
    }

    private fun listFromDataBase() {

        val listObserver = Observer<List<Task>> {
            if(it.isEmpty()){
                ctn_estadovazio.visibility = View.VISIBLE
            }else{
                ctn_estadovazio.visibility = View.GONE
            }
            adapter.submitList(it)
        }
        viewModel.taskListViewModel.observe(this, listObserver)

    }

    fun opemtaskdetail(task: Task){
        val intent = taskdetail.start(requireContext(), task)
        requireActivity().startActivity(intent)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance() = TaskListFragment()

    }

}