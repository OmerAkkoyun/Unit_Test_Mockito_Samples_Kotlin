package com.omerakkoyun.unittestsdemo.test2

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class LoginPresenter(private val view: ILoginView) {

    fun login(username: String, password: String) {
        val repository = LoginRepositoryImpl()
        val result = repository.login(username, password)

        if (result.success != null) {
            view.onLoginSuccess()
        }

        if (result.error != null) {
            view.onLoginFail(result.error)
        }


    }
}