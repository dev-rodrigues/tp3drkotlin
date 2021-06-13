package com.example.tp3desenvolvimentokotlin.ui.auth.login

import android.os.Bundle
import android.view.Gravity
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
import com.example.tp3desenvolvimentokotlin.service.ValidationService
import com.example.tp3desenvolvimentokotlin.service.dto.InputText
import com.example.tp3desenvolvimentokotlin.service.impl.FirebaseAuthServiceImpl
import com.example.tp3desenvolvimentokotlin.service.impl.ValidationServiceImpl
import kotlinx.android.synthetic.main.login_fragment.*
import java.util.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var firebaseAuthService: FirebaseAuthService
    private lateinit var validationService: ValidationService

    private lateinit var viewAux: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.login_fragment, container, false);
        viewAux = inflate

        firebaseAuthService = FirebaseAuthServiceImpl()
        validationService = ValidationServiceImpl()

        configureViewModel()
        configureNavigation()

        // VERIFICA SE EXISTE UMA INSTANCIA VALIDA
        if (firebaseAuthService.isLogged())
            findNavController().navigate(R.id.indexFragment)

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
                findNavController().navigate(R.id.indexFragment)
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.setOnClickListener{

            var email  = txtEmail.text.toString()
            var password = txtPassword.text.toString()

            val validate = validationService.validate(
                Arrays.asList(
                    InputText("E-mail", email),
                    InputText("Password", password)
                )
            );

            if (validate.isNullOrEmpty()) {
                viewModel.authenticate(email, password)
            } else {
                validate.forEach {
                    val makeText = Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}