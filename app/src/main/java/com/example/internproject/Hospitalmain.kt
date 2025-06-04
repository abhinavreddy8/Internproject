package com.example.internproject

import android.os.Bundle
import android.util.Log
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
import com.example.internproject.fragments.HospitalNotifications
import com.example.internproject.fragments.HospitalSearchDonor
import com.example.internproject.fragments.HospitalSearchRecipient
import com.example.internproject.fragments.Hospitalhome
import com.example.internproject.ui.theme.LastTheme

class Hospitalmain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LastTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HospitalApp()
                }
            }
        }
    }


}

@Composable
fun HospitalApp() {
    val navController = rememberNavController()





    Scaffold(
        bottomBar = { HospitalBottomNavigation(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HospitalScreen.HospitalHome.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(HospitalScreen.HospitalHome.route) {
                Hospitalhome()
            }
            composable(HospitalScreen.HospitalSearchDonors.route) {
                HospitalSearchDonor()
            }
            composable(HospitalScreen.HospitalSearchRecipients.route) {
                HospitalSearchRecipient()
            }


            composable(HospitalScreen.HospitalNotifications.route) {
              HospitalNotifications()
            }
        }
    }
}

@Composable
fun HospitalBottomNavigation(navController: NavHostController) {
    val screens = listOf(
        HospitalScreen.HospitalHome,
        HospitalScreen.HospitalSearchDonors,
        HospitalScreen.HospitalSearchRecipients,
        HospitalScreen.HospitalNotifications
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
                        painter = painterResource(id = getHospitalIcon(screen.route)),
                        contentDescription = screen.route,
                        modifier = Modifier.size(24.dp) // Reduced icon size
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    Log.d("Navigation", "Bottom nav clicked: ${screen.route}")
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = false, // Hides label for minimal design
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
fun getHospitalIcon(route: String): Int {
    return when (route) {
        HospitalScreen.HospitalHome.route -> R.drawable.home
        HospitalScreen.HospitalSearchDonors.route -> R.drawable.searchdonor
        HospitalScreen.HospitalSearchRecipients.route -> R.drawable.searchrecipient
        HospitalScreen.HospitalNotifications.route -> R.drawable.notification
        else -> R.drawable.hospital
    }
}

sealed class HospitalScreen(val route: String) {
    object HospitalHome : HospitalScreen("hospitalhome")
    object HospitalSearchDonors : HospitalScreen("hospitalsearchdonors")
    object HospitalSearchRecipients : HospitalScreen("hospitalsearchrecipients")
    object HospitalNotifications : HospitalScreen("hospitalnotifications")
}