package com.example.internproject

import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.internproject.fragments.DonorHome
import com.example.internproject.fragments.DonorNotifications
import com.example.internproject.fragments.DonorSearchHospitals
import com.example.internproject.fragments.DonorSearchRecipient
import com.example.internproject.ui.theme.LastTheme

class Donormain:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            LastTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DonorApp()
                }
            }
        }
    }

    @Preview
    @Composable
    fun DonorApp() {
        val navController = rememberNavController()


        Scaffold(
            bottomBar = { DonorBottomNavigation(navController) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.DonorHome.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.DonorHome.route) {
                    DonorHome()
                }
                composable(Screen.DonorSearchHospitals.route) {
                    DonorSearchHospitals()

                }
                composable(Screen.DonorSearchRecipients.route) {
                    DonorSearchRecipient()
                }



                composable(Screen.DonorNotifications.route) {
                    DonorNotifications()
                }
            }
        }
    }


    @Composable
    fun DonorBottomNavigation(navController: NavHostController) {
        val screens = listOf(
            Screen.DonorHome,
            Screen.DonorSearchHospitals,
            Screen.DonorSearchRecipients,
            Screen.DonorNotifications
        )

        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.destination?.route

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
                            painter = painterResource(id = getIcon(screen.route)),
                            contentDescription = screen.route,
                            modifier = Modifier.size(24.dp) // Reduced icon size
                        )
                    },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    alwaysShowLabel = false, // Hide labels
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
    fun getIcon(route: String): Int {
        return when (route) {
            Screen.DonorHome.route -> R.drawable.home
            Screen.DonorSearchHospitals.route -> R.drawable.searchicon
            Screen.DonorSearchRecipients.route -> R.drawable.searchrecipient
            Screen.DonorNotifications.route -> R.drawable.notification
            else -> R.drawable.donor
        }
    }

    sealed class Screen(val route: String) {
        object DonorHome : Screen("donorhome")
        object DonorSearchHospitals : Screen("donorsearchhospitals")
        object DonorSearchRecipients : Screen("donorsearchrecipients")
        object DonorNotifications : Screen("donornotifications")


    }
}