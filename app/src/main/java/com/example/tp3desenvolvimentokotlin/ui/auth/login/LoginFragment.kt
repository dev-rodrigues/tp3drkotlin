package com.example.tp3desenvolvimentokotlin.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp3desenvolvimentokotlin.R
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService
import com.example.tp3desenvolvimentokotlin.service.impl.FirebaseAuthServiceImpl

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var firebaseAuthService: FirebaseAuthService
    private lateinit var viewAux: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.login_fragment, container, false);
        viewAux = inflate

        firebaseAuthService = FirebaseAuthServiceImpl()

        configureViewModel()
        configureNavigation()

        return inflate;
    }

    private fun configureNavigation() {
        var btnRegister = viewAux?.findViewById<Button>(R.id.btnNavigateRegister)
        btnRegister.setOnClickListener{
            findNavController().navigate(R.id.registerFragment)
        }
    }

    private fun configureViewModel() {
        val loginViewModelFactory = LoginViewModelFactory(firebaseAuthService)

        viewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it)
                //TODO: NAVEGAR PARA DASHBOARD
                println("Autenticou com sucesso")
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }
}