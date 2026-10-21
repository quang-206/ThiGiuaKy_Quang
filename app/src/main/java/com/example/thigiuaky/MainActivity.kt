package com.example.thigiuaky

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ScreenOne()
                }
            }
        }
    }
}
@Composable
fun ScreenOne() {
    // Sử dụng state để lưu text người dùng nhập
    var inputText by remember { mutableStateOf("") }

    // Lấy context để chuyển màn hình
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Màn hình 1 - Nhập dữ liệu", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Nhập nội dung...") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Chuyển sang SecondActivity và gửi kèm dữ liệu
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("message_key", inputText)
            context.startActivity(intent)
        }) {
            Text("Chuyển sang Màn hình 2")
        }
    }
}