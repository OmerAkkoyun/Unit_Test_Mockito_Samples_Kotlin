package com.omerakkoyun.unittestsdemo.test3

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class OnBoardingPresenter(private val repo: IPreferenceRepository, private val view: IOnBoardingView) {

    fun check() {
        if (!repo.isBoardingSeen()) {
            view.showOnBoarding()
            repo.setOnBoardingSeen()
        } else {
            view.showLogin()
            repo.setToken("123")
        }
    }

}

interface IOnBoardingView {
    fun showOnBoarding()
    fun showLogin()
}