package com.example.self_introduction

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.self_introduction.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.signUpEvent.observe(this) { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("SignUpId", viewModel.signUpId.value)
                intent.putExtra("SignUpPw", viewModel.signUpPassword.value)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }
        }

        /*val etName = binding.etSignUpName
        val etId = binding.etSignUpId
        val etPw = binding.etSignUpPw
        val btnSign = binding.btnSign
        val nameErrorText = binding.nameErrorText
        val idErrorText = binding.idErrorText
        val pwErrorText = binding.pwErrorText*/

        // name, id, pw가 모두 입력 되었을 때만 회원가입 버튼이 눌려짐
        /*val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 아무것도 안함
            }

            override fun afterTextChanged(s: Editable?) {
                if (etName.text.isNotEmpty()) {
                    nameErrorText.visibility = TextView.INVISIBLE
                }
                if (etId.text.isNotEmpty()) {
                    idErrorText.visibility = TextView.INVISIBLE
                }
                if (etPw.text.isNotEmpty()) {
                    pwErrorText.visibility = TextView.INVISIBLE
                }
                btnSign.isEnabled = etName.text.toString().isNotEmpty() && etId.text.toString().isNotEmpty() && etPw.text.toString().isNotEmpty()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 아무것도 안함
            }
        }*/

        /*etName.addTextChangedListener(textWatcher)
        etId.addTextChangedListener(textWatcher)
        etPw.addTextChangedListener(textWatcher)

        etName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && etName.text.isEmpty()) {
                nameErrorText.visibility = TextView.VISIBLE
                nameErrorText.text = "이름을 입력해주세요."
            }
        }

        etId.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && etId.text.isEmpty()) {
                idErrorText.visibility = TextView.VISIBLE
                idErrorText.text = "아이디를 입력해주세요."
            }
        }

        etPw.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && etPw.text.isEmpty()) {
                pwErrorText.visibility = TextView.VISIBLE
                pwErrorText.text = "비밀번호를 입력해주세요."
            }
        }*/

        /*btnSign.setOnClickListener {
            // etName, etId, etPw 중 하나라도 입력되지 않았을 때, 회원가입 버튼이 눌려져도 회원가입이 되지 않음
            if (etName.text.isEmpty() || etId.text.isEmpty() || etPw.text.isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else if (!isPasswordValid(etPw.text.toString())) {
                Toast.makeText(this, "비밀번호는 8자이상, 숫자 포함, 대소문자 포함, 특수문자가 포함되어야 합니다.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("SignUpId", etId.text.toString())
                intent.putExtra("SignUpPw", etPw.text.toString())
                setResult(RESULT_OK, intent)                            // SignUpActivity에서 SignInActivity로 돌아갈 때, SignUpActivity에서 입력한 id, pw를 SignInActivity에 전달
                finish()
            }
        }*/
    }
}