package eu.tutorials.newsapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

//Todo 3 Create a Screen package and create the TopNews File
//Todo 4: Create the TopNews Compose function
@Composable
fun TopNews() {
    //Todo 5: add a Column with a fillMaxSize and set horizontalAlignment to center
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
      //Todo 6:Add a Text with text as Top News and fontWeight od semi bold
        Text(text = "Top News",fontWeight = FontWeight.SemiBold)
    }
}


//Todo 8: Add a preview function for TopScreen
@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    TopNews()
}