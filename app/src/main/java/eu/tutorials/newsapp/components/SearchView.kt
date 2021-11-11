package eu.tutorials.newsapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.newsapp.ui.NewsManager

@Composable
fun SearchView(query:MutableState<String>,newsManager: NewsManager) {
    val localFocusManager = LocalFocusManager.current
    Card(elevation = 6.dp,shape = RoundedCornerShape(4.dp),modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        backgroundColor = MaterialTheme.colors.primary) {
    Row {
        TextField(value = query.value, onValueChange = {
            newsManager.onQueryChanged(it)
        }, modifier = Modifier
            .fillMaxWidth(),
            label = {
                Text(text = "Search",color = White)
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, "",tint = White)
            },
            trailingIcon = {
                if (query.value != "") {
                    IconButton(
                        onClick = {
                            query.value =
                                ""
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            tint = White
                        )
                    }
                }
            },
            textStyle = TextStyle(color = White,fontSize = 18.sp),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (query.value != "") {
                        newsManager.getSearchedArticles(query.value)
                    }
                    localFocusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(textColor = White)
        )
    }
    }
}