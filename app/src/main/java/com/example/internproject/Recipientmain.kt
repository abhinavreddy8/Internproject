package com.example.internproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.internproject.RecipientScreen.RecipientHome
import com.example.internproject.RecipientScreen.RecipientSearchDonors

import com.example.internproject.fragments.RecipientNotifications
import com.example.internproject.fragments.RecipientSearchDonor
import com.example.internproject.fragments.RecipientSearchHospital
import com.example.internproject.fragments.Recipienthome
import com.example.internproject.ui.theme.LastTheme
import com.example.internproject.viewmodels.RecipientHomeViewModel

class Recipientmain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            LastTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipientApp()
                }
            }
        }
    }
}

@Composable
fun RecipientApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { RecipientBottomNavigation(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = RecipientScreen.RecipientHome.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(RecipientScreen.RecipientHome.route) {
                Recipienthome()
            }
            composable(RecipientScreen.RecipientSearchDonors.route) {
                RecipientSearchDonor()
            }
            composable(RecipientScreen.RecipientSearchHospitals.route) {
                       RecipientSearchHospital()
            }

            composable(RecipientScreen.RecipientNotifications.route) {
                RecipientNotifications()
            }


        }
    }
}

@Composable
fun RecipientBottomNavigation(navController: NavHostController) {
    val screens = listOf(
        RecipientScreen.RecipientHome,
        RecipientScreen.RecipientSearchDonors,
        RecipientScreen.RecipientSearchHospitals,
        RecipientScreen.RecipientNotifications
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        tonalElevation = 4.dp,
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = getRecipientIcon(screen.route)),
                        contentDescription = screen.route,
                        modifier = Modifier.size(24.dp) // Reduced icon size
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
            )
        }
    }
}

@Composable
fun getRecipientIcon(route: String): Int {
    return when (route) {
        RecipientScreen.RecipientHome.route -> R.drawable.home
        RecipientScreen.RecipientSearchDonors.route -> R.drawable.searchdonor
        RecipientScreen.RecipientSearchHospitals.route -> R.drawable.searchicon
        RecipientScreen.RecipientNotifications.route -> R.drawable.notification// Changed to a notification icon
        else -> R.drawable.recipient
    }
}

sealed class RecipientScreen(val route: String) {
    object RecipientHome : RecipientScreen("recipienthome")
    object RecipientSearchDonors : RecipientScreen("recipientsearchdonors")
    object RecipientSearchHospitals : RecipientScreen("recipientsearchhospitals")
    object RecipientNotifications : RecipientScreen("recipientnotifications")

}