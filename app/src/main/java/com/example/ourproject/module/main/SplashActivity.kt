package com.example.ourproject.module.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ourproject.R
import com.example.ourproject.core.base.BaseActivity
import com.example.ourproject.module.business.account.LoginActivity
import com.example.ourproject.module.main.viewmodel.LoginState
import com.example.ourproject.module.main.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SplashScreen(
                onLoginIn = {
                    navigateToMain()
                },
                onLoginOut = {
                    navigateToLogin()
                }
            )
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun navigateToMain() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onLoginIn: () -> Unit = {},
    onLoginOut: () -> Unit = {},
) {
    rememberCoroutineScope().launch {
        viewModel.viewState.collect {
            when (it) {
                SplashViewModel.SplashViewState.Idle -> {
                    // do nothing
                }

                is SplashViewModel.SplashViewState.Success -> {
                    it.loginState.let {
                        when (it) {
                            LoginState.LoggedIn -> {
                                onLoginIn()
                            }

                            LoginState.LoggedOut -> {
                                onLoginOut()
                            }

                            LoginState.Unknown -> {
                                // do nothing
                            }
                        }
                    }
                }
            }
        }
    }
    Content()
}

@Composable
private fun Content() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.shop),
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.Center,
        contentDescription = stringResource(
            id = R.string.app_name
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Content()
}