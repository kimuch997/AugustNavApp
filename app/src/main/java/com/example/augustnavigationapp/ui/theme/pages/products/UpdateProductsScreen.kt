package com.example.augustnavigationapp.ui.theme.pages.products

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.augustnavigationapp.models.Product
import com.example.augustnavigationapp.models.ProductRepository
import com.example.augustnavigationapp.ui.theme.AugustNavigationAppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductsScreen(navController: NavHostController, id:String) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        var context = LocalContext.current
        var name by remember { mutableStateOf("") }
        var quantity by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }

        var currentDataRef =FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        currentDataRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var product = snapshot.getValue(Product::class.java)
                name = product!!.name
                quantity = product!!.quantity
                price = product!!.price
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        Text(text ="Add product",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )
        var productname by remember { mutableStateOf(TextFieldValue(name)) }
        var productquantity by remember { mutableStateOf(TextFieldValue(quantity)) }
        var productprice by remember { mutableStateOf(TextFieldValue(price)) }




        OutlinedTextField(value = productname,
            onValueChange = {productname = it},
            label = { Text(text = "Product name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(value = productquantity,
            onValueChange = {productquantity = it},
            label = { Text(text = "Product quantity*") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = productprice,
            onValueChange = {productprice = it},
            label = { Text(text = "Product price *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-------WRITE THE SAVE LOGIC HERE---//
            var productRepository = ProductRepository(navController, context)
            productRepository.UpdateProduct(productname.text.trim(), productquantity.text.trim(), productprice.text.trim(),id)
        }) {
            Text(text = "Update")
        }


    }
}



@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun UpdateProductsScreenPreview() {
    AugustNavigationAppTheme {
        UpdateProductsScreen(rememberNavController(),id = "")
    }

}