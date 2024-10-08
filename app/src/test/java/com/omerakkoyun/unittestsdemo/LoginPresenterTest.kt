package com.omerakkoyun.unittestsdemo

import com.omerakkoyun.unittestsdemo.test2.ILoginView
import com.omerakkoyun.unittestsdemo.test2.LoginPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.anyVararg

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class LoginPresenterTest {
    private lateinit var presenter: LoginPresenter
    private lateinit var view: ILoginView


    @Before // testten önce ayağa kalkması gereken nesneler
     fun setUp() {
        view = mock(ILoginView::class.java)
        presenter = LoginPresenter(view)
    }


    @Test
    fun testLoginSuccess() {
        presenter.login("user", "password")
        Mockito.verify(view).onLoginSuccess()
    }

    @Test
    fun testLoginFail() {
        presenter.login("user", "passwords")
        Mockito.verify(view).onLoginFail(anyVararg())
    }


}