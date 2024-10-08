package com.omerakkoyun.unittestsdemo

import com.omerakkoyun.unittestsdemo.test1.LoginManager
import org.junit.Test

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class LoginManagerTest {
    private val loginManager = LoginManager()
    private val username = "user"
    private val password = "password"

    @Test
    fun testLoginSuccess() {
        val result = loginManager.login(username, password)
        assert(result)
    }

    @Test
    fun testLoginFailure(){
        val result = loginManager.login(username,"wrongpassword")
        assert(!result)
    }
}