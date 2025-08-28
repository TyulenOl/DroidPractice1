@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import coil.compose.AsyncImage;
import ru.urfu.droidpractice1.viewmodel.MainActivityScreenViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(
    onShare: () -> Unit,
    onNavigate: () -> Unit,
    isArticleRead: Boolean,
    onReadStateChange: (Boolean) -> Unit,
    viewModel: MainActivityScreenViewModel
) {
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.article_title)
                            )

                            IconButton(onClick = {
                                onShare(
                                )
                            }) {
                                Icon(Icons.Default.Share, contentDescription = "Поделиться статьей")
                            }
                        }
                    })
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.article_name), fontSize = 22.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FingerButton(
                        onClick = { viewModel.increaseLikes() },
                        Icons.Default.KeyboardArrowUp,
                        viewModel._likesCounter
                    )

                    FingerButton(
                        onClick = { viewModel.increaseDislikes() },
                        Icons.Default.KeyboardArrowDown,
                        viewModel._dislikesCounter
                    )
                }

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Text(
                    text = stringResource(id = R.string.article_text)
                )

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                AsyncImage(
                    model = "https://resizer.mail.ru/p/1c088cbb-7696-5716-8a84-df4d11104e20/AQAGX9T3ZeUCQRGl_VllTMArA6vWGVanSsoLx-rE3tBVYDgdah5mQSOhOCGVr_4jO_Yvk94c3jyHdPusE_wpGL20dbk.webp",
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    contentDescription = "Описание"
                )

                Button(
                    onClick = {
                        onReadStateChange(isArticleRead)
                        onNavigate()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isArticleRead) Color.Blue else Color.Red,
                        contentColor = if (isArticleRead) Color.White else Color.Black
                    )
                ) {
                    Text(text = stringResource(id = R.string.next_article))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen({}, {}, false, {}, MainActivityScreenViewModel(0, 0, {}, {}))
}

@Composable
fun FingerButton(onClick: () -> Unit, image: ImageVector, value: Int) {
    IconButton(onClick = { onClick() }) {
        Icon(image, contentDescription = null)
    }

    Text(text = value.toString())
}