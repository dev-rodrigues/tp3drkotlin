package com.example.tp3desenvolvimentokotlin.ui.auth.register

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
import com.example.tp3desenvolvimentokotlin.service.UserDetailService
import com.example.tp3desenvolvimentokotlin.service.ValidationService
import com.example.tp3desenvolvimentokotlin.service.dto.InputText
import com.example.tp3desenvolvimentokotlin.service.impl.FirebaseAuthServiceImpl
import com.example.tp3desenvolvimentokotlin.service.impl.UserDetailServiceImpl
import com.example.tp3desenvolvimentokotlin.service.impl.ValidationServiceImpl
import kotlinx.android.synthetic.main.register_fragment.*
import java.util.*
import kotlin.collections.ArrayList

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var firebaseAuthService: FirebaseAuthService
    private lateinit var userDetailService: UserDetailService

    private lateinit var viewAux: View
    private lateinit var validationService: ValidationService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.register_fragment, container, false);
        viewAux = inflate

        firebaseAuthService = FirebaseAuthServiceImpl()
        userDetailService = UserDetailServiceImpl()
        validationService = ValidationServiceImpl()

        configureViewModel()
        configureNavigation()

        return inflate;
    }

    private fun configureNavigation() {
        var btnReturn = viewAux?.findViewById<Button>(R.id.btnRegisterReturn)
        btnReturn.setOnClickListener{
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun configureViewModel() {
        val loginViewModelFactory = RegisterViewModelFactory(firebaseAuthService, userDetailService)
        viewModel = ViewModelProvider(this, loginViewModelFactory).get(RegisterViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it)
                findNavController().popBackStack()
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegisterExecute.setOnClickListener{

            val fullName = iptRegisterFullName.text.toString()
            val email = iptRegisterEmail.text.toString()
            val password = iptRegisterPassword.text.toString()

            var inputs = ArrayList<InputText>()
            inputs.addAll(Arrays.asList(
                InputText("Nome", fullName),
                InputText("E-mail", email),
                InputText("Senha", password))
            )

            var validacao = validationService.validate(inputs)

            if (validacao.isNullOrEmpty())  {
                viewModel.register(email, password, fullName)
            } else {
                var position = 1
                validacao.forEach {
                    val makeText = Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                    makeText.setGravity(Gravity.LEFT, position * 10, position * 10)
                    position *= 2
                    makeText.show()
                }
            }

        }
    }
}