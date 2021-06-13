package com.example.tp3desenvolvimentokotlin.ui.home.registerVaccine

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp3desenvolvimentokotlin.R
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService
import com.example.tp3desenvolvimentokotlin.service.impl.EventServiceImpl
import com.example.tp3desenvolvimentokotlin.service.impl.FirebaseAuthServiceImpl
import java.time.LocalDate
import java.util.*

class RegisterVaccineFragment : Fragment() {

    private lateinit var viewModel: RegisterVaccineViewModel;
    private lateinit var firebaseAuthService: FirebaseAuthService;
    private lateinit var eventService: EventService


    private lateinit var iptDate: LocalDate;

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.register_vaccine_fragment, container, false)

        firebaseAuthService = FirebaseAuthServiceImpl()
        eventService = EventServiceImpl()
        iptDate = LocalDate.MIN

        configureViewModel()
        configurarCalendario(inflate)

        return inflate
    }

    private fun configureViewModel() {
        val registerVaccineViewModelFactory = RegisterVaccineViewModelFactory(firebaseAuthService, eventService)
        viewModel = ViewModelProvider(
            this,
            registerVaccineViewModelFactory
        ).get(RegisterVaccineViewModel::class.java)


        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it)
                findNavController().navigate(R.id.indexFragment)
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterVaccineViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun configurarCalendario(view: View) {
        val btnDate = view.findViewById<Button>(R.id.btn_pick_date)
        var txtSelectDate = view.findViewById<TextView>(R.id.txtSelectDate)

        btnDate.setOnClickListener {
            val getDate: Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireContext(),
                android.R.style.ThemeOverlay_Material,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                    val selectDate = Calendar.getInstance()

                    if (view !=null && year != null && month != null && dayOfMonth != null) {
                        selectDate.set(Calendar.YEAR, year)
                        selectDate.set(Calendar.MONTH, month)
                        selectDate.set(Calendar.YEAR, dayOfMonth)
                        iptDate = LocalDate.of(year, month, dayOfMonth)
                        txtSelectDate.text = "Agendado para: ${dayOfMonth}-${month}-${year}"
                    }
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH));

            datePicker.show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnRegistrarDose = view.findViewById<Button>(R.id.btnRegistrarDose)

        btnRegistrarDose.setOnClickListener {

            val vaccineName = view.findViewById<EditText>(R.id.iptNomeDaVacina).text.toString()
            val totalDoses = view.findViewById<EditText>(R.id.iptTotalDeDoses).text.toString()

            var nextVaccine = ""
            if (iptDate != LocalDate.MIN) {
                nextVaccine = iptDate.toString()
            }

            viewModel.register(vaccineName, totalDoses.toInt(), nextVaccine)

        }
    }

}