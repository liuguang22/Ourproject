package com.example.ourproject.module.business.account

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ourproject.R
import com.example.ourproject.core.base.BaseActivity
import com.example.ourproject.core.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint


/**
 * 登录页面
 */
@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onUpClick: () -> Unit = {},
) {
    MainTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    CartsTopBar(onUpClick)
                },
                modifier = Modifier
                    .fillMaxSize(),
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                            .fillMaxWidth(),
                        text = "欢迎回来",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                            .fillMaxWidth(),
                        text = "未注册的手机号将自动创建为手机号"
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                            .fillMaxWidth(),
                        text = "139 **** 1234",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Button(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                            .fillMaxWidth(),
                        onClick = { /*TODO*/ }) {
                        Text(text = "本机手机号码一键登录")
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CartsTopBar(
    onUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(stringResource(id = R.string.app_name))
        },
        modifier = modifier.statusBarsPadding(),
        navigationIcon = {
            IconButton(onClick = onUpClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = null
                )
            }
        },
    )
}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen()
}