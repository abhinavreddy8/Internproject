package com.example.internproject.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class NotificationData(
    val notificationId: String = "",
    val hospitalName: String = "",
    val status: String = "",
    val timestamp: Long = 0,
    val responseMessage: String? = null,
    val requestId: String = "",
    val type: String = "HOSPITAL"
)
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipientNotifications() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Notifications",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Sample placeholder data
            val sampleNotifications = listOf(
                NotificationData(
                    notificationId = "1",
                    hospitalName = "Sample Hospital",
                    status = "pending",
                    timestamp = System.currentTimeMillis(),
                    responseMessage = "Sample message",
                    requestId = "req1",
                    type = "HOSPITAL"
                ),
                NotificationData(
                    notificationId = "2",
                    hospitalName = "Donor Request",
                    status = "accepted",
                    timestamp = System.currentTimeMillis(),
                    responseMessage = "Sample donor message",
                    requestId = "req2",
                    type = "DONOR"
                )
            )

            if (sampleNotifications.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No notifications available",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sampleNotifications) { notification ->
                        NotificationCard(notification = notification)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationCard(notification: NotificationData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = notification.hospitalName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = notification.timestamp.toString(), // Simplified for UI
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            notification.responseMessage?.let { message ->
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            val chipText = when (notification.status) {
                "pending" -> "Pending"
                "accepted" -> "Accepted"
                "rejected" -> "Declined"
                else -> notification.status.replaceFirstChar { it.uppercase() }
            }
            val chipColor = when (notification.status) {
                "pending" -> MaterialTheme.colorScheme.secondaryContainer
                "accepted" -> MaterialTheme.colorScheme.primaryContainer
                "rejected" -> MaterialTheme.colorScheme.errorContainer
                else -> MaterialTheme.colorScheme.surfaceVariant
            }
            val chipTextColor = when (notification.status) {
                "pending" -> MaterialTheme.colorScheme.onSecondaryContainer
                "accepted" -> MaterialTheme.colorScheme.onPrimaryContainer
                "rejected" -> MaterialTheme.colorScheme.onErrorContainer
                else -> MaterialTheme.colorScheme.onSurfaceVariant
            }

            SuggestionChip(
                onClick = { },
                label = {
                    Text(
                        text = chipText,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                colors = SuggestionChipDefaults.suggestionChipColors(
                    containerColor = chipColor,
                    labelColor = chipTextColor
                )
            )
        }
    }
}