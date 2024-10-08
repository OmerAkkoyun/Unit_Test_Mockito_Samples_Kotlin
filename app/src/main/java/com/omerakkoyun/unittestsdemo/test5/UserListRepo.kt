package com.omerakkoyun.unittestsdemo.test5

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class UserListRepoImpl : UserListRepo {

    override fun getUsers(): List<User> {
        return listOf(User("user1"), User("user2"))

    }

}

interface UserListRepo {
    fun getUsers(): List<User>
}