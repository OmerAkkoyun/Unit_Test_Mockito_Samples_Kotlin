package com.omerakkoyun.unittestsdemo.test2

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
interface ILoginView {
    fun onLoginSuccess()
    fun onLoginFail(e: Throwable)
}