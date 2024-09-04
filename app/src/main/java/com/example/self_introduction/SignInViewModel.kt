package com.example.self_introduction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    val signInId: MutableLiveData<String> = MutableLiveData<String>()
    val signInPw = MutableLiveData<String>()

    private val _signInEvent = MutableLiveData<Boolean>()
    val signInEvent: LiveData<Boolean> get() = _signInEvent

    private val _signUpEvent = MutableLiveData<Unit>()
    val signUpEvent: LiveData<Unit> get() = _signUpEvent

    fun onSignInClick() {
        // 로그인 로직 추가
        if (signInId.value.isNullOrEmpty() || signInPw.value.isNullOrEmpty()) {
            _signInEvent.value = false
        } else {
            _signInEvent.value = true
        }
    }

    fun onSignUpClick() {
        // 회원가입 로직 추가
        _signUpEvent.value = Unit
    }

    fun setSignInId(id: String) {
        signInId.value = id
    }
}