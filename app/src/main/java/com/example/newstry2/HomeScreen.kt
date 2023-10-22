package com.example.newstry2

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(newsViewModel: NewsViewModel) {
    val newsState by newsViewModel.newsState.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "NewsApp")
                    },
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.White,
                    actions = {
                        // Add action items here (like icons)
                    }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {

                //News_app()
                MainInfo(newsState = newsState)

            }

        }
    }
}


@Composable
fun News_app() {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainInfo(newsState: NewsState) {
    Column(
        modifier = Modifier.padding(top = 20.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isVisible by remember {
            mutableStateOf(false)
        }
        when (newsState){
            is NewsState.Success -> {
                val newsData = newsState.newsData
                Text(
                    text = newsData.article[0].title,
                    fontSize = 20.sp,
                    color = Color(0,0,139),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center

                )
                Text(
                    text = newsData.article[0].author,
                    fontSize = 11.sp,
                    color = Color(13,47,117,225),
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = newsData.article[0].description,
                    fontSize = 15.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(top = 10.dp),
                    textAlign = TextAlign.Center

                )
                Spacer(modifier = Modifier.height(20.dp))

                // 1
                Text(
                    text = newsData.article[1].title,
                    fontSize = 20.sp,
                    color = Color(0,0,139),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center

                )
                Text(
                    text = newsData.article[1].author,
                    fontSize = 11.sp,
                    color = Color(13,47,117,225),
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = newsData.article[1].description,
                    fontSize = 15.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(top = 10.dp),
                    textAlign = TextAlign.Center

                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = {
                    isVisible=!isVisible

                }) {
                    Text(text = if(isVisible)"More News" else "Less News")
                }
                Spacer(modifier = Modifier.height(20.dp))
                AnimatedVisibility(
                    visible = isVisible,
                    enter = scaleIn(initialScale = 2f) + expandVertically() ,
                    exit = scaleOut(targetScale = 2f)+ shrinkVertically()

                ){
                  newsData.article.forEach{ e ->
                      val newsList = listOf(
                          newsData.article[e.title.toInt()].title,
                          newsData.article[e.author.toInt()].author,
                          newsData.article[e.description.toInt()].description
                      )
                      LazyColumn {
                          items(newsData.article) { article ->
                              NewsCard(news = article)
                          }
                      }

                  }

                }

            }
            is NewsState.Loading -> {
                CircularProgressIndicator()
            }
            is NewsState.Error -> {
                Text(
                    text = "Failed to fetch news data.",
                    fontSize = 18.sp,
                    color = Color.Red
                )
            }
        }
    }

}