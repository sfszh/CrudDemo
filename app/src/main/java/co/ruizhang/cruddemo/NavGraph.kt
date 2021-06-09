package co.ruizhang.cruddemo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.ruizhang.cruddemo.MainDestinations.ONBOARDING_ROUTE
import co.ruizhang.cruddemo.MainDestinations.REPOS_ROUTE
import co.ruizhang.cruddemo.MainDestinations.REPOS_SEARCH
import co.ruizhang.cruddemo.MainDestinations.REPO_DETAIL_ID_KEY
import co.ruizhang.cruddemo.MainDestinations.REPO_DETAIL_ROUTE
import co.ruizhang.cruddemo.ui.Onboarding
import co.ruizhang.cruddemo.ui.RepoDetail
import co.ruizhang.cruddemo.ui.Repos
import co.ruizhang.cruddemo.ui.reposearch.RepoSearch

/**
 * Destinations used in the ([OwlApp]).
 */
object MainDestinations {
    const val ONBOARDING_ROUTE = "onboarding"
    const val REPOS_ROUTE = "repos"
    const val REPOS_SEARCH = "repos_search"
    const val REPO_DETAIL_ROUTE = "repo"
    const val REPO_DETAIL_ID_KEY = "repoId"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ONBOARDING_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ONBOARDING_ROUTE) {
            Onboarding {
                navController.navigate(REPOS_ROUTE)
            }
        }

        composable(REPOS_ROUTE) {
            Repos(
                selectRepo = { navController.navigate("${REPO_DETAIL_ROUTE}/$it") },
                navigateToRepoSearch = {
                    navController.navigate(
                        REPOS_SEARCH
                    )
                })
        }

        composable(REPOS_SEARCH) {
            RepoSearch()
        }

        composable("${REPO_DETAIL_ROUTE}/{${REPO_DETAIL_ID_KEY}}") { backStackEntry ->
            RepoDetail(backStackEntry.arguments?.getInt("userId")!!)
        }
    }
}