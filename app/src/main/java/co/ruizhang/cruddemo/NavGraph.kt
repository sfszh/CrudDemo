package co.ruizhang.cruddemo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.ruizhang.cruddemo.MainDestinations.REPOS_ROUTE
import co.ruizhang.cruddemo.data.Mock_Repos
import co.ruizhang.cruddemo.ui.Onboarding
import co.ruizhang.cruddemo.ui.RepoCard
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme

/**
 * Destinations used in the ([OwlApp]).
 */
object MainDestinations {
    const val ONBOARDING_ROUTE = "onboarding"
    const val REPOS_ROUTE = "repos"
    const val REPO_DETAIL_ROUTE = "repo"
    const val REPO_DETAIL_ID_KEY = "repoId"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.ONBOARDING_ROUTE,
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.ONBOARDING_ROUTE) {
            Onboarding {
                navController.navigate(REPOS_ROUTE)
            }
        }

        composable(MainDestinations.REPOS_ROUTE) {
            val mockData = Mock_Repos[0]

            CrudDemoTheme {
                RepoCard(repo = mockData, onClick = {})
            }
        }

    }
}