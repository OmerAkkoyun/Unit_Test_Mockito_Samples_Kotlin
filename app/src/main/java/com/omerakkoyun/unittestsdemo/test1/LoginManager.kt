package com.omerakkoyun.unittestsdemo.test1

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class LoginManager {

    fun login(username: String, password: String): Boolean {
        // Simüle bir kullanıcı adı ve şifre kontrolü
        return username == "user" && password == "password"

    }
}