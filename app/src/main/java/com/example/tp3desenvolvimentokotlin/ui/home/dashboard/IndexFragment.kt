package com.example.tp3desenvolvimentokotlin.ui.home.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.tp3desenvolvimentokotlin.R
import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import com.example.tp3desenvolvimentokotlin.domain.worker.NotifyMeWorker
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService
import com.example.tp3desenvolvimentokotlin.service.impl.EventServiceImpl
import com.example.tp3desenvolvimentokotlin.service.impl.FirebaseAuthServiceImpl
import com.example.tp3desenvolvimentokotlin.ui.home.dashboard.addapter.VaccineAdapter
import com.example.tp3desenvolvimentokotlin.ui.home.dashboard.dto.EventDTO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IndexFragment : Fragment() {

    private lateinit var viewModel: IndexViewModel
    private lateinit var firebaseAuthService: FirebaseAuthService
    private lateinit var eventService: EventService

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.index_fragment, container, false);
        firebaseAuthService = FirebaseAuthServiceImpl();
        eventService = EventServiceImpl();

        val indexViewModel = IndexViewModelFactory(firebaseAuthService, eventService);
        viewModel = ViewModelProvider(requireActivity(), indexViewModel).get(IndexViewModel::class.java);


        viewModel.findVaccinesToUser();

        val viewList = inflate.findViewById<RecyclerView>(R.id.lista_lancamentos);

        viewModel.events.observe(viewLifecycleOwner, Observer {
            configureViewList(it, viewList)
        })

        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<NotifyMeWorker>().build()

        WorkManager
            .getInstance(requireContext())
            .enqueue(uploadWorkRequest)

        configurarNavegacao(inflate)
        return inflate;
    }

    @SuppressLint("WrongConstant")
    private fun configureViewList(events: List<Event>, viewList: RecyclerView) {
        viewList.adapter = VaccineAdapter(events as ArrayList<Event>) {
            EventDTO.eventDTO = it
            findNavController().navigate(R.id.showVaccineFragment)
        }
        viewList.layoutManager = LinearLayoutManager(requireContext(), OrientationHelper.HORIZONTAL, false)
    }

    private fun configurarNavegacao(view: View) {
        val addVaccine = view.findViewById<FloatingActionButton>(R.id.floatingActionButtonAdd)
        val exit = view.findViewById<FloatingActionButton>(R.id.floatingActionButtonExit)

        exit.setOnClickListener {
            viewModel.exitToApp()
            findNavController().navigate(R.id.loginFragment)
        }

        addVaccine.setOnClickListener{
            findNavController().navigate(R.id.registerVaccineFragment)
        }
    }
}