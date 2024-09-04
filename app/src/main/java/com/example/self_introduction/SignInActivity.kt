package com.example.self_introduction

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.self_introduction.databinding.ActivitySignInBinding
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlin.math.log

class SignInActivity : AppCompatActivity() {
    private lateinit var requestLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivitySignInBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

//        setContentView(binding.root)

//        val id = binding.etSignInId
//        val pw = binding.etSignInPw
//        val btnSignIn = binding.btnSignIn
//        val btnSignUp = binding.btnSignUp

        // SignUp에서 회원가입한 id, pw를 받아와서 자동으로 입력되도록 함
        requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val data: Intent? = it.data
                viewModel.signInId.value = data?.getStringExtra("SignUpId")
                viewModel.signInPw.value = data?.getStringExtra("SignUpPw")
            }
        }

        // ViewModel의 signInEvent를 관찰하여 처리
        viewModel.signInEvent.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, getString(R.string.toast_msg_signInSuccess), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("LoginId", viewModel.signInId.value)
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.toast_msg_signInError), Toast.LENGTH_SHORT).show()
            }
        })

        // ViewModel의 signUpEvent를 관찰하여 처리
        viewModel.signUpEvent.observe(this, Observer {
            val intent = Intent(this, SignUpActivity::class.java)
            requestLauncher.launch(intent)
        })

        binding.btnSignIn.setOnClickListener {
            val loginId = binding.etSignInId.text.toString()
            if (loginId.isEmpty() || binding.etSignInPw.text.toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_msg_signInError), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.setSignInId(loginId)
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("LoginId", loginId)
                startActivity(intent)
            }
        }

        /*btnSignIn.setOnClickListener {
            // id, pw 중 하나라도 입력되지 않았을 때, 로그인 버튼이 눌려져도 로그인이 되지 않음
            if (id.text.toString().isEmpty() || pw.text.toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_msg_signInError), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.toast_msg_signInSuccess), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("LoginId", id.text.toString())
                startActivity(intent)
            }
        }

        // requestLauncher를 여기서 호출해야함, signupActivity를 호출하면서 requestLauncher가 호출되어야함
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            requestLauncher.launch(intent)
        }*/
    }
}