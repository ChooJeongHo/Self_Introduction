package com.example.self_introduction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    val signUpName = MutableLiveData<String>()
    val signUpId = MutableLiveData<String>()
    val signUpPassword = MutableLiveData<String>()

    private val _signUpEvent = MutableLiveData<Boolean>()
    val signUpEvent: LiveData<Boolean> get() = _signUpEvent

    private val _nameError = MutableLiveData<String>()
    val nameError: LiveData<String> get() = _nameError

    private val _idError = MutableLiveData<String>()
    val idError: LiveData<String> get() = _idError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> get() = _passwordError

    fun onSignUpClick() {
        var isValid = true

        if (signUpName.value.isNullOrEmpty()) {
            _nameError.value = "이름을 입력해주세요."
            isValid = false
        } else {
            _nameError.value = ""
        }

        if (signUpId.value.isNullOrEmpty()) {
            _idError.value = "아이디를 입력해주세요."
            isValid = false
        } else {
            _idError.value = ""
        }

        if (signUpPassword.value.isNullOrEmpty()) {
            _passwordError.value = "비밀번호를 입력해주세요."
            isValid = false
        } else if (!isPasswordValid(signUpPassword.value!!)) {
            _passwordError.value = "비밀번호는 8자 이상, 숫자 포함, 대소문자 포함, 특수문자가 포함되어야 합니다."
            isValid = false
        } else {
            _passwordError.value = ""
        }

        _signUpEvent.value = isValid
    }

    // 비밀번호 조건 : 8자 이상, 숫자 포함, 대문자 포함, 소문자 포함, 특수문자 포함
    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        return password.matches(Regex(passwordPattern))
    }
}