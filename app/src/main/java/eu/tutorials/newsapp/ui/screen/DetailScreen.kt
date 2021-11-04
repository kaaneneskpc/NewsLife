package eu.tutorials.newsapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

/**Todo 7: create DetailScreen File and function
 * Add a Column with fillMaxSize and set horizontalAlignment to center
 * Add a Text with text as Detail Screen and fontWeight as SemiBold
 * */
@Composable
fun DetailScreen() {
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Detail Screen",fontWeight = FontWeight.SemiBold)
    }
}


//Todo 9: Add a preview function for DetailScreen
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen()
}