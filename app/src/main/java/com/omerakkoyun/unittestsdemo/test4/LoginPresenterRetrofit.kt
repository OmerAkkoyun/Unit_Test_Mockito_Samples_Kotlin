package com.omerakkoyun.unittestsdemo.test4

import com.omerakkoyun.unittestsdemo.test2.ILoginView
import com.omerakkoyun.unittestsdemo.test2.data.Login

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class LoginPresenterRetrofit(private val view: ILoginView, private val repo: ILoginRepositoryRetrofit) {

    fun login(username: String, password: String) {
        repo.login(username, password, object : ILoginCallback {
            override fun success(login: Login) {
                view.onLoginSuccess()
            }

            override fun fail(error: Throwable) {
                view.onLoginFail(error)
            }

        })
    }


}