package com.omerakkoyun.unittestsdemo

import com.omerakkoyun.unittestsdemo.test2.ILoginView
import com.omerakkoyun.unittestsdemo.test2.LoginPresenter
import com.omerakkoyun.unittestsdemo.test2.data.Login
import com.omerakkoyun.unittestsdemo.test4.ILoginCallback
import com.omerakkoyun.unittestsdemo.test4.ILoginRepositoryRetrofit
import com.omerakkoyun.unittestsdemo.test4.LoginPresenterRetrofit
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class LoginPresenterRetrofitTest {
    private lateinit var presenter: LoginPresenterRetrofit
    private lateinit var view: ILoginView
    private lateinit var repo: ILoginRepositoryRetrofit
    private lateinit var cb: ILoginCallback


    @Before // testten önce ayağa kalkması gereken nesneler
    fun setUp() {
        view = mock(ILoginView::class.java)
        repo = mock(ILoginRepositoryRetrofit::class.java)
        cb = mock(ILoginCallback::class.java)

        presenter = LoginPresenterRetrofit(view, repo)
    }

    @Test
    fun loginSuccess() { // eq (equals) sadece belirtilen değeri kontrol eder, referensı değil, any ise herhangi bir değer
        Mockito.`when`(repo.login(eq("user"), eq("password"), any<ILoginCallback>()))
            .then{
                // callback'i çağır (2.index cb)
                val cb = it.arguments[2] as ILoginCallback
                cb.success(Login("123")) // test için success döndür
            }

        presenter.login("user", "password")

        Mockito.verify(view).onLoginSuccess() // success döndü mü?

    }

    @Test
    fun loginFail() {
        Mockito.`when`(repo.login(any(), any(), any<ILoginCallback>())) // any<ILoginCallback>() yada any() de olabilir
            .then{
                val cb = it.arguments[2] as ILoginCallback
                cb.fail(Throwable())
            }

        presenter.login("x", "x")

        Mockito.verify(view).onLoginFail(any()) // hata döndü mü?

    }

}