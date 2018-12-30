package com.ncl.login

import com.ncl.common.Constants
import com.ncl.common.StateContext
import com.ncl.common.domain.screen.ScreenCoordinator
import com.ncl.common.domain.screen.ScreenCoordinatorService
import com.ncl.common.domain.user.UserManager
import com.ncl.common.domain.user.UserService
import com.ncl.coordinator.Coordinator
import io.reactivex.subjects.BehaviorSubject


class LoginCoordinator(coordinatorId: String,
                       private val screenCoordinator: ScreenCoordinator?

) : Coordinator(coordinatorId) {

    enum class Stage {
        Idle,
        LoginOauth,
        LoginInternal
    }

    private var stage: Stage = Stage.Idle
    private var currentCoordinator: Coordinator? = null
    private val loginSubject: BehaviorSubject<out LoginFlowEvent> = BehaviorSubject.createDefault(LoginFlowEvent.LoginIdle())
    private var userManager: UserManager? = null


    override fun start() {
        if (stage == Stage.Idle) {
            stage = Stage.LoginInternal
            //onStageCoordinator = getChildById(Constants.LOGIN_VIEWMODEL_ID)
            //onStageCoordinator.start()
            showLoginScreen()
        }
    }

    override fun stop() {

    }


    fun subscribe() {

    }

    private fun showLoginScreen() {
        val loginFragment = LoginFragment()
        loginFragment.setCoordinatorId(coordinatorId)

        screenCoordinator?.setView(loginFragment, Constants.LOGIN_FRAGMENT_TAG)
    }


    var userManagerListener = object: UserManager.Listener {

        override fun internalSignUpFail() {
            //screenCoordinator?.setView()
        }

        override fun internalSignUpSuccess() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun internalLoginFail() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun internalLoginSuccess() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun oauthProviderPlatformLoginFail() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun oauthProviderPlatformVerifyEmailSent() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun oauthHamperPlatformLoginStarted() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun oauthHamperPlatformLoginFail(t: Throwable) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun oauthHamperPlatformLoginSuccess() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

}


data class LoginInput(val username: String, val password: String)


sealed class LoginFlowEvent {

    class LoginIdle : LoginFlowEvent() {}

}