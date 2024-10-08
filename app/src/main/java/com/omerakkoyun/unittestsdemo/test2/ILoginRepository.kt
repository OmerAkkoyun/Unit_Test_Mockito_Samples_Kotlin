package com.omerakkoyun.unittestsdemo.test2

import com.omerakkoyun.unittestsdemo.test2.data.ApiResponse
import com.omerakkoyun.unittestsdemo.test2.data.Login

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
interface ILoginRepository {
    fun login(username: String, password: String): ApiResponse<Login>

}