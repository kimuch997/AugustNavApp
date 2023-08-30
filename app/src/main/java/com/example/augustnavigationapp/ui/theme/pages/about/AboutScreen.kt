package com.example.augustnavigationapp.ui.theme.pages.about

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun AboutScreen(navController: NavHostController){
    Text(text = "Welcome to About Screen")
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun AboutScreenPreview() {

}