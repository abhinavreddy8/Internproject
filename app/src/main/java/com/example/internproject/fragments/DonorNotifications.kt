package com.example.internproject.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.internproject.R
import com.example.internproject.viewmodels.DonorNotificationsViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ContactRequest(
    val recipientName: String = "",
    val timestamp: Long = 0L,
    val status: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DonorNotifications() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text("Contact Requests")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Sample data for UI display
            val sampleRequests = listOf(
                ContactRequest(
                    recipientName = "John Doe",
                    timestamp = 1625097600000L, // Example timestamp
                    status = "pending"
                )
            )

            if (sampleRequests.isEmpty()) {
                EmptyNotificationsPlaceholder()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    items(sampleRequests) { request ->
                        ContactRequestItem(request = request)
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyNotificationsPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "No notifications",
            modifier = Modifier
                .size(64.dp)
                .padding(bottom = 16.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        )
        Text(
            text = "No pending contact requests",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Spacer(height = 8.dp)
        Text(
            text = "When recipients request your contact information, they will appear here",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactRequestItem(request: ContactRequest) {
    val backgroundColor = when (request.status) {
        "accepted" -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        "rejected" -> MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
        else -> MaterialTheme.colorScheme.surface
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = request.recipientName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                val statusChipColor = when (request.status) {
                    "accepted" -> MaterialTheme.colorScheme.primary
                    "rejected" -> MaterialTheme.colorScheme.error
                    else -> MaterialTheme.colorScheme.secondary
                }

                val statusText = when (request.status) {
                    "pending" -> "Pending"
                    "accepted" -> "Accepted"
                    "rejected" -> "Declined"
                    else -> request.status.replaceFirstChar { it.uppercase() }
                }

                if (request.status != "pending") {
                    SuggestionChip(
                        onClick = { },
                        label = { Text(statusText) },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = statusChipColor.copy(alpha = 0.2f),
                            labelColor = statusChipColor
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Has requested your contact information",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Sample Date", // Placeholder for formatted date
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )

            if (request.status == "pending") {
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = { },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        ),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Text("Decline")
                    }

                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Accept")
                    }
                }
            }
        }
    }
}

@Composable
fun Spacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}