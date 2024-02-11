package com.azhar.composequotes

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.ProgressIndicatorDefaults
import com.azhar.composequotes.data.QuotesRoomDatabase
import com.azhar.composequotes.data.repository.QuotesRepository
import com.azhar.composequotes.scenes.CategorySelectionScreen
import com.azhar.composequotes.scenes.DetailsScreen
import com.azhar.composequotes.ui.theme.ComposeQuotesTheme
import com.azhar.composequotes.ui.theme.Purple40
import com.azhar.composequotes.ui.theme.Purple80
import com.azhar.composequotes.ui.theme.colorBlack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.sql.Array
import kotlin.math.roundToInt

/**
 * @created 11/26/2023 -4:10 PM
 * @project ComposeQuotes
 * @author  azhar
 */
class MainActivity : ComponentActivity() {
    private val viewModel: QuotesViewModel by viewModels {
        QuotesViewModelFactory(
            QuotesRepository(
                QuotesRoomDatabase.getDataBase(this@MainActivity).quotesDao()
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sheetState = rememberModalBottomSheetState()
            val searchQuery = rememberSaveable { mutableStateOf("") }
            val scope = rememberCoroutineScope()
            var showBottomSheet by remember { mutableStateOf(false) }
            val isDarkThemEnabled =
                rememberSaveable {
                    mutableStateOf(false)
                }

            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier.height(height = 500.dp),
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState,
                ) {
                    AboutMe(scope, sheetState) { showBottomSheet = it }

                }
            }
            MaterialTheme(
                colorScheme = if (isDarkThemEnabled.value) darkColorScheme() else lightColorScheme()

            ) {
                ComposeQuotesTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        Scaffold(topBar = {
                            Column {
                                TopAppBar(
                                    title = {
                                        Text(
                                            text = "Quotes App",
                                            color = Color.White
                                        )
                                    },
                                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple40),
                                )
                                OutlinedTextField(
                                    value = searchQuery.value,
                                    onValueChange = {
                                        searchQuery.value = it
                                        viewModel.searchQuotes(it)
                                    },
                                    label = { Text(stringResource(R.string.search)) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                                    keyboardActions = KeyboardActions(onSearch = {
                                    }),
                                    shape = RoundedCornerShape(5.dp), singleLine = true,
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Filled.Search,
                                            contentDescription = "Search Icon"
                                        )
                                    },
                                )
                            }


                        }, floatingActionButton = {
                            FloatingActionButton(
                                containerColor = Purple40,
                                shape = CircleShape,
                                onClick = {
                                    showBottomSheet = showBottomSheet.not()
                                    isDarkThemEnabled.value = !isDarkThemEnabled.value
                                }) {
                                Icon(
                                    tint = Color.White,
                                    imageVector = Icons.Default.AccountBox,
                                    contentDescription = ""
                                )

                            }
                        }) {
                            Surface(
                                modifier = Modifier
                                    .padding(top = it.calculateTopPadding())
                                    .fillMaxSize()
                            ) {
                                HomeScreen()

                            }


                        }

                    }
                }
            }
        }


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun AboutMe(
        scope: CoroutineScope? = null,
        sheetState: SheetState? = null,
        showBottomSheet: (Boolean) -> Unit
    ) {
        val openWebView = remember {
            mutableStateOf(false)
        }
        if (openWebView.value) {
            WebViewScreen()
        }
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp)
                .verticalScroll(rememberScrollState(), enabled = true)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), verticalArrangement = Arrangement.spacedBy((-40).dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.azhar_linkedin_background),
                    contentDescription = ""
                )
                Image(
                    painter = painterResource(id = R.drawable.azhar),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .border(2.dp, Color.White, CircleShape)
                )
            }
            Text(
                text = """Azhar (Mohammad Azahruddin Ansari) Ansari 
(He/Him)
Senior Software Engineer @Bharti Airtel | Airtel Digital (Android Developer l Java | Kotlin | Flutter | Jetpack compose) | Follow For Android Updates
Talks about #kmm, #kotlin, #android, #compose, and #javadevelopment #KMM""",
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp)
            )
            // Sheet content

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 10.dp), onClick = {
                openWebView.value = openWebView.value.not()

            }) {
                Text("Visit profile")
            }
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 10.dp), onClick = {
                scope?.launch { sheetState?.hide() }?.invokeOnCompletion {
                    if (sheetState?.isVisible?.not() == true) {
                        showBottomSheet(false)
                    }
                }
            }) {
                Text("Close")
            }
        }

    }

    @Composable
    private fun HomeScreen() {
        val controller = rememberNavController()
        NavHost(navController = controller, startDestination = Screens.CATEGORY.name) {
            composable(route = Screens.CATEGORY.name) {
                CategorySelectionScreen(controller, viewModel)
            }
            composable(route = Screens.DETAILS.name.plus("/{quote}")) {
                DetailsScreen(controller)
            }
        }


    }


}

@Composable
fun WebViewCompose(url: String, modifier: Modifier = Modifier, onCreated: (WebView) -> Unit = {}) {
    val context = LocalContext.current
    val webView: MyWebView = remember(context) {
        MyWebView(context).also(onCreated)
    }
    webView.webViewClient = WebViewClient()
    DisposableEffect(webView) {
        onDispose {
            webView.stopLoading()
            webView.destroy()
        }
    }
    val scrollabeState = rememberScrollableState { delta ->
        val scrollY = webView.scrollY
        val consume = (scrollY - delta).coerceIn(0f, webView.verticalScrollRange.toFloat())
        webView.scrollTo(0, consume.roundToInt())
        (scrollY - webView.scrollY).toFloat()
    }
    AndroidView(
        factory = { webView },
        modifier = modifier
            .height(300.dp)
            .scrollable(scrollabeState, Orientation.Vertical)
    ) { webView2 ->
        webView2.loadUrl(url)
    }
}

@Composable
fun WebViewScreen() {
    val progressState = remember {
        mutableStateOf(false)
    }

    if (progressState.value) {
        ProgressBar()

    }
    val mUrl = "https://github.com/AzharCodeWizard"
    AndroidView(modifier = Modifier.verticalScroll(state = rememberScrollState(), true), factory = {
        WebView(it).apply {
            settings.javaScriptEnabled = true
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    progressState.value = false
                    super.onPageFinished(view, url)
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    progressState.value = true
                    super.onPageStarted(view, url, favicon)
                }
            }
            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}

@Composable
private fun ProgressBar() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
                .fillMaxSize(),
            indicatorColor = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

private class MyWebView(context: Context) : WebView(context) {
    val verticalScrollRange: Int get() = computeVerticalScrollRange() - height
}
