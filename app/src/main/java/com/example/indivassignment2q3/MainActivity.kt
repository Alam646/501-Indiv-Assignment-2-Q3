package com.example.indivassignment2q3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.indivassignment2q3.ui.theme.IndivAssignment2Q3Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IndivAssignment2Q3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KotlinPracticeScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun KotlinPracticeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Kotlin Practice Screen (Q3)",
            style = MaterialTheme.typography.headlineSmall
        )

        // Using a state variable to allow dynamic input for the 'when' expression
        var animalInput by rememberSaveable { mutableStateOf("cat") }

        // The 'when' expression provides a clear and concise way to handle multiple
        // distinct cases based on the 'animalInput' value, enhancing the readability
        // compared to just having multiple if-else statements
        val animalSound = when (animalInput) {
            "cat" -> "Meow"
            "dog" -> "Woof"
            "fish" -> "BlubBlub"
            else -> "Unknown animal"
        }

        Text(text = "Animal Sound Checker", style = MaterialTheme.typography.titleMedium)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { animalInput = "cat" }) { Text("Cat") }
            Button(onClick = { animalInput = "dog" }) { Text("Dog") }
            Button(onClick = { animalInput = "fish" }) { Text("Fish") }
        }
        Text(text = "Input: $animalInput")
        Text(text = "Sound: $animalSound", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // to demonstrate robust handling of potentially absent data
        Text("Nullable String Checker", style = MaterialTheme.typography.titleMedium)

        // 'optionalMessage' is nullable to represent data that may or may not be present.
        var optionalMessage: String? by rememberSaveable { mutableStateOf(null) }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { optionalMessage = "Hello from the ?.let block!" }) {
                Text("Set Message")
            }
            Button(onClick = { optionalMessage = null }) {
                Text("Clear Message")
            }
        }

        // '?.let' is crucial here for null safety. It ensures that operations on
        // 'optionalMessage' (like displaying it) only occur if it actually holds a value,
        // which in turn helps with preventing NullPointerExceptions and making the code more resilient
        optionalMessage?.let { message ->
            Text(
                text = "Message: $message",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        } ?: Text( // Provides user feedback when the optional data is absent
            text = "Message is currently null.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // State to manage a numerical value that has specific constraints
        var counter by rememberSaveable { mutableStateOf(0) }

        Text("Counter Limited to 5", style = MaterialTheme.typography.titleMedium)
        Button(onClick = {
            // The 'if' condition is necessary to enforce the business rule that the
            // counter should not exceed a specific limit, demonstrating controlled state mutation
            if (counter < 5) {
                counter++
            }
        }) {
            Text("Increment Counter (Limit 5)")
        }
        Text("Counter Value: $counter", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, name = "Kotlin Practice Screen Preview")
@Composable
fun KotlinPracticeScreenPreview() {
    IndivAssignment2Q3Theme {
        KotlinPracticeScreen(modifier = Modifier.fillMaxSize())
    }
}
