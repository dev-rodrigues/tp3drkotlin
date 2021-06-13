package com.example.tp3desenvolvimentokotlin.ui.home.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp3desenvolvimentokotlin.R
import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import com.example.tp3desenvolvimentokotlin.domain.entity.Release
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService
import com.example.tp3desenvolvimentokotlin.service.impl.EventServiceImpl
import com.example.tp3desenvolvimentokotlin.service.impl.FirebaseAuthServiceImpl
import com.example.tp3desenvolvimentokotlin.ui.home.dashboard.dto.EventDTO
import com.example.tp3desenvolvimentokotlin.ui.home.registerVaccine.RegisterVaccineViewModel
import com.example.tp3desenvolvimentokotlin.ui.home.registerVaccine.RegisterVaccineViewModelFactory
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class   ShowVaccineFragment : Fragment() {

    private lateinit var viewModel: ShowVaccineViewModel;
    private lateinit var firebaseAuthService: FirebaseAuthService;
    private lateinit var eventService: EventService;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.show_vaccine_fragment, container, false);

        firebaseAuthService = FirebaseAuthServiceImpl()
        eventService = EventServiceImpl()

        configureViewModel()

        var addNextDose = inflate.findViewById<Button>(R.id.addNextDose);
        configureButton(addNextDose, inflate)

        return inflate;
    }

    private fun configureViewModel() {
        val showVaccineViewModelFactory = ShowVaccineViewModelFactory(firebaseAuthService, eventService)
        viewModel = ViewModelProvider(this, showVaccineViewModelFactory).get(ShowVaccineViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) findNavController().navigate(R.id.indexFragment)
        })

        viewModel.msg.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (!it.isNullOrBlank()) Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    private fun configureButton(addNextDose: Button, inflate: View) {
        addNextDose.setOnClickListener {
            var oldReleases = EventDTO.eventDTO!!.releases

            var newRelease = ArrayList<Release>();

            oldReleases?.forEach {
                newRelease.add(it)
            }

            newRelease.add(Release(null, Calendar.getInstance().time))
            EventDTO.eventDTO!!.releases = newRelease

            processNextDoseButton(EventDTO.eventDTO!!, inflate)
            Toast.makeText(requireContext(), "Dose registrada", Toast.LENGTH_LONG).show();
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (EventDTO.eventDTO != null) {
            var selected = EventDTO.eventDTO!!;

            view.findViewById<EditText>(R.id.editTextTextEditNameVaccine).setText(selected.name)
            view.findViewById<EditText>(R.id.editTextTextEditTotalVaccine).setText(selected.total.toString())

            val nextVaccine = selected.nextVaccine

            if (!nextVaccine.isNullOrEmpty()) {
                val nextVaccineSplit = nextVaccine?.split("-");
                val date = "Agendado para: ${nextVaccineSplit[2]}-${nextVaccineSplit[1]}-${nextVaccineSplit[0]}";
                view.findViewById<TextView>(R.id.lblEditSelectDateVaccine).text = date
            } else {
                view.findViewById<TextView>(R.id.lblEditSelectDateVaccine).text = "Informe a data da próxima dose";
            }

            processNextDoseButton(selected, view)
        }

        var buttonEdit = view.findViewById<Button>(R.id.btnEditarDose);
        buttonEdit.setOnClickListener {
            var editTextTextEditNameVaccine = view.findViewById<EditText>(R.id.editTextTextEditNameVaccine).text.toString()
            var editTextTextEditTotalVaccine = view.findViewById<EditText>(R.id.editTextTextEditTotalVaccine).text.toString()

            EventDTO.eventDTO!!.name = editTextTextEditNameVaccine
            EventDTO.eventDTO!!.total = editTextTextEditTotalVaccine.toInt()

            viewModel.editEvent()
        }

        var removeDose = view.findViewById<Button>(R.id.removeDose);
        removeDose.setOnClickListener {
            viewModel.delete()
        }
    }

    private fun processNextDoseButton(selected: Event, view: View) {
        if (isValidEdit(selected)) {
            var addNextDose = view.findViewById<Button>(R.id.addNextDose);
            addNextDose.visibility = View.GONE

            var lblEditSelectDateVaccine = view.findViewById<TextView>(R.id.lblEditSelectDateVaccine)
            lblEditSelectDateVaccine.visibility = View.GONE
        }
    }

    private fun isValidEdit(selected: Event) = selected.releases!!.size >= selected.total!!
}