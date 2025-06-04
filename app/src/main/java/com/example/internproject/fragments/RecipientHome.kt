package com.example.internproject.fragments

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
@Preview
@Composable
fun Recipienthome() {
    var isEditing by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image Section
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("") // Placeholder for profile image URL
                    .crossfade(true)
                    .build(),
                contentDescription = "Profile Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Button(
            onClick = { /* Placeholder */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Upload Profile")
        }

        // Personal Information Section
        SectionHeader(text = "Personal Information")


        RecipientTextField(
            value = "", // Placeholder for fullName
            onValueChange = { /* Placeholder */ },
            label = "Full Name",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for dob
            onValueChange = { /* Placeholder */ },
            label = "Date of Birth",
            enabled = isEditing
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Male", "Female", "Other").forEach { gender ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedGender == gender,
                        onClick = {
                            if (isEditing) {
                                selectedGender = gender
                            }
                        },
                        enabled = isEditing
                    )
                    Text(gender)
                }
            }
        }

        // Contact Information
        RecipientTextField(
            value = "", // Placeholder for contactNumber
            onValueChange = { /* Placeholder */ },
            label = "Contact Number",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for email
            onValueChange = { /* Placeholder */ },
            label = "Email",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for address
            onValueChange = { /* Placeholder */ },
            label = "Address",
            enabled = isEditing
        )

        // Medical Information Section
        SectionHeader(text = "Medical Information")

        RecipientTextField(
            value = "", // Placeholder for bloodGroup
            onValueChange = { /* Placeholder */ },
            label = "Blood Group",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for organNeeded
            onValueChange = { /* Placeholder */ },
            label = "Organ(s) Needed",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for urgencyLevel
            onValueChange = { /* Placeholder */ },
            label = "Urgency Level",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for medicalHistory
            onValueChange = { /* Placeholder */ },
            label = "Medical History",
            enabled = isEditing,
            singleLine = false
        )

        RecipientTextField(
            value = "", // Placeholder for diagnosisDetails
            onValueChange = { /* Placeholder */ },
            label = "Diagnosis Details",
            enabled = isEditing,
            singleLine = false
        )

        Button(
            onClick = { /* Placeholder */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Diagnostic Reports")
        }

        RecipientTextField(
            value = "", // Placeholder for medications
            onValueChange = { /* Placeholder */ },
            label = "Current Medications",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for allergies
            onValueChange = { /* Placeholder */ },
            label = "Allergies",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for doctorName
            onValueChange = { /* Placeholder */ },
            label = "Doctor/Specialist Name",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for hospitalName
            onValueChange = { /* Placeholder */ },
            label = "Hospital Name",
            enabled = isEditing
        )

        Button(
            onClick = { /* Placeholder */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Medical Reports")
        }

        // Insurance Information

        SectionHeader(text = "Insurance Information")

        RecipientTextField(
            value = "", // Placeholder for insuranceProvider
            onValueChange = { /* Placeholder */ },
            label = "Insurance Provider",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for insuranceId
            onValueChange = { /* Placeholder */ },
            label = "Insurance ID",
            enabled = isEditing
        )

        // Emergency Contact Section
        SectionHeader(text = "Emergency Contact")

        RecipientTextField(
            value = "", // Placeholder for emergencyContact
            onValueChange = { /* Placeholder */ },
            label = "Emergency Contact",
            enabled = isEditing
        )

        // Location Section
        SectionHeader(text = "Location Details")

        RecipientTextField(
            value = "", // Placeholder for latitude
            onValueChange = { /* Read-only field */ },
            label = "Latitude",
            enabled = false
        )

        RecipientTextField(
            value = "", // Placeholder for longitude
            onValueChange = { /* Read-only field */ },
            label = "Longitude",
            enabled = false
        )

        Button(
            onClick = { /* Placeholder */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            enabled = true
        ) {
            Text("Fetch Current Location")
        }

        RecipientTextField(
            value = "", // Placeholder for currentLocation
            onValueChange = { /* Placeholder */ },
            label = "Current Location (Address)",
            enabled = isEditing
        )

        RecipientTextField(
            value = "", // Placeholder for preferredHospitals
            onValueChange = { /* Placeholder */ },
            label = "Preferred Hospitals",
            enabled = isEditing
        )

        // Action Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { isEditing = !isEditing },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(if (isEditing) "Submit" else "Edit")
            }

            if (isEditing) {
                Button(
                    onClick = { isEditing = false },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}


@Composable
private fun RecipientTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        enabled = enabled,
        singleLine = singleLine,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}