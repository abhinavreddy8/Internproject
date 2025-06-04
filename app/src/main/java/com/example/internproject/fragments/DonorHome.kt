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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DonorHome() {
    var isEditing by remember { mutableStateOf(false) }
    var fullName by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var organAvailable by remember { mutableStateOf("") }
    var medicalHistory by remember { mutableStateOf("") }
    var surgeries by remember { mutableStateOf("") }
    var medications by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf("") }
    var lifestyle by remember { mutableStateOf("") }
    var geneticDisorders by remember { mutableStateOf("") }
    var emergencyContact by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }
    var currentLocation by remember { mutableStateOf("") }
    var preferredLocation by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image Placeholder
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        ) {
            // You can put an Image here using painterResource or Coil
        }

        Button(
            onClick = { /* image picker logic */ },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Upload Profile")
        }

        SectionHeader(text = "Personal Information")

        DonorTextField(value = fullName, onValueChange = { fullName = it }, label = "Full Name", enabled = isEditing)
        DonorTextField(value = dob, onValueChange = { dob = it }, label = "Date of Birth", enabled = isEditing)

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Male", "Female", "Other").forEach { gender ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedGender == gender,
                        onClick = { if (isEditing) selectedGender = gender },
                        enabled = isEditing
                    )
                    Text(gender)
                }
            }
        }

        DonorTextField(value = contactNumber, onValueChange = { contactNumber = it }, label = "Contact Number", enabled = isEditing)
        DonorTextField(value = email, onValueChange = { email = it }, label = "Email", enabled = isEditing)
        DonorTextField(value = address, onValueChange = { address = it }, label = "Address", enabled = isEditing)

        SectionHeader(text = "Medical Information")

        DonorTextField(value = bloodGroup, onValueChange = { bloodGroup = it }, label = "Blood Group", enabled = isEditing)
        DonorTextField(value = organAvailable, onValueChange = { organAvailable = it }, label = "Organ(s) Available for Donation", enabled = isEditing)
        DonorTextField(value = medicalHistory, onValueChange = { medicalHistory = it }, label = "Medical History", enabled = isEditing)
        DonorTextField(value = surgeries, onValueChange = { surgeries = it }, label = "Previous Surgeries", enabled = isEditing)

        Button(onClick = { /* surgery report upload */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Upload Surgery Reports")
        }

        DonorTextField(value = medications, onValueChange = { medications = it }, label = "Current Medications", enabled = isEditing)
        DonorTextField(value = allergies, onValueChange = { allergies = it }, label = "Allergies", enabled = isEditing)
        DonorTextField(value = lifestyle, onValueChange = { lifestyle = it }, label = "Lifestyle", enabled = isEditing)
        DonorTextField(value = geneticDisorders, onValueChange = { geneticDisorders = it }, label = "Genetic Disorders", enabled = isEditing)

        Button(onClick = { /* medical report upload */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Upload Medical Reports")
        }

        SectionHeader(text = "Emergency Contact")

        DonorTextField(value = emergencyContact, onValueChange = { emergencyContact = it }, label = "Emergency Contact", enabled = isEditing)

        SectionHeader(text = "Location Details")

        DonorTextField(value = latitude, onValueChange = {}, label = "Latitude", enabled = false)
        DonorTextField(value = longitude, onValueChange = {}, label = "Longitude", enabled = false)

        Button(
            onClick = { /* fetch location logic */ },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Fetch Current Location")
        }

        DonorTextField(value = currentLocation, onValueChange = { currentLocation = it }, label = "Current Location (Address)", enabled = isEditing)
        DonorTextField(value = preferredLocation, onValueChange = { preferredLocation = it }, label = "Preferred Donation Locations", enabled = isEditing)

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { isEditing = !isEditing },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text(if (isEditing) "Submit" else "Edit")
            }

            if (isEditing) {
                Button(
                    onClick = { isEditing = false },
                    modifier = Modifier.weight(1f).padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}


@Composable
private fun DonorTextField(
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
