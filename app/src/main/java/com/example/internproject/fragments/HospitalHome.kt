package com.example.internproject.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.internproject.R
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
fun Hospitalhome() {
    var isEditing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hospital Logo Section
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("") // Placeholder for logo URL
                    .crossfade(true)
                    .build(),
                contentDescription = "Hospital Logo",
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
            Text("Upload Logo")
        }

        // Hospital Information Section
        SectionHeader(text = "Hospital Details")

        HospitalTextField(
            value = "", // Placeholder for hospitalName
            onValueChange = { /* Placeholder */ },
            label = "Hospital Name",
            enabled = isEditing
        )

        HospitalTextField(
            value = "", // Placeholder for hospitalId
            onValueChange = { /* Placeholder */ },
            label = "Hospital ID",
            enabled = isEditing
        )

        HospitalTextField(
            value = "", // Placeholder for address
            onValueChange = { /* Placeholder */ },
            label = "Address",
            enabled = isEditing,
            singleLine = false
        )

        // Contact Information
        SectionHeader(text = "Contact Information")

        HospitalTextField(
            value = "", // Placeholder for phoneNumber
            onValueChange = { /* Placeholder */ },
            label = "Phone Number",
            enabled = isEditing
        )

        HospitalTextField(
            value = "", // Placeholder for email
            onValueChange = { /* Placeholder */ },
            label = "Email Address",
            enabled = isEditing
        )

        HospitalTextField(
            value = "", // Placeholder for website
            onValueChange = { /* Placeholder */ },
            label = "Website",
            enabled = isEditing
        )

        HospitalTextField(
            value = "", // Placeholder for emergencyContact
            onValueChange = { /* Placeholder */ },
            label = "Emergency Contact",
            enabled = isEditing
        )

        // Hospital Services Section
        SectionHeader(text = "Hospital Services")

        HospitalTextField(
            value = "", // Placeholder for specializations
            onValueChange = { /* Placeholder */ },
            label = "Specializations",
            enabled = isEditing,
            singleLine = false
        )

        HospitalTextField(
            value = "", // Placeholder for organTransplantTypes
            onValueChange = { /* Placeholder */ },
            label = "Organ Transplant Types",
            enabled = isEditing,
            singleLine = false
        )

        HospitalTextField(
            value = "", // Placeholder for transplantTeam
            onValueChange = { /* Placeholder */ },
            label = "Transplant Team",
            enabled = isEditing,
            singleLine = false
        )

        // Facility Information Section
        SectionHeader(text = "Facility Information")

        HospitalTextField(
            value = "", // Placeholder for bedsAvailable
            onValueChange = { /* Placeholder */ },
            label = "Beds Available",
            enabled = isEditing
        )

        HospitalTextField(
            value = "", // Placeholder for icuFacilities
            onValueChange = { /* Placeholder */ },
            label = "ICU Facilities",
            enabled = isEditing,
            singleLine = false
        )

        HospitalTextField(
            value = "", // Placeholder for operatingRooms
            onValueChange = { /* Placeholder */ },
            label = "Operating Rooms",
            enabled = isEditing
        )

        // Location Section
        SectionHeader(text = "Location Details")

        HospitalTextField(
            value = "", // Placeholder for latitude
            onValueChange = { /* Read-only field */ },
            label = "Latitude",
            enabled = false
        )

        HospitalTextField(
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

        HospitalTextField(
            value = "", // Placeholder for currentLocation
            onValueChange = { /* Placeholder */ },
            label = "Current Location (Address)",
            enabled = isEditing
        )

        // Certification Section
        SectionHeader(text = "Certifications & Licenses")

        HospitalTextField(
            value = "", // Placeholder for certifications
            onValueChange = { /* Placeholder */ },
            label = "Certifications",
            enabled = isEditing,
            singleLine = false
        )

        HospitalTextField(
            value = "", // Placeholder for accreditations
            onValueChange = { /* Placeholder */ },
            label = "Accreditations",
            enabled = isEditing,
            singleLine = false
        )

        Button(
            onClick = { /* Placeholder */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Certificates")
        }

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
private fun HospitalTextField(
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