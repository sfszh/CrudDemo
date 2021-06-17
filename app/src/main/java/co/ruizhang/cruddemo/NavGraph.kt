package co.ruizhang.cruddemo

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.ruizhang.cruddemo.MainDestinations.ONBOARDING_ROUTE
import co.ruizhang.cruddemo.MainDestinations.REPOS_ROUTE
import co.ruizhang.cruddemo.MainDestinations.REPOS_SEARCH
import co.ruizhang.cruddemo.MainDestinations.REPO_DETAIL_ID
import co.ruizhang.cruddemo.MainDestinations.REPO_DETAIL_ROUTE
import co.ruizhang.cruddemo.ui.onboarding.Onboarding
import co.ruizhang.cruddemo.ui.repodetail.RepoDetail
import co.ruizhang.cruddemo.ui.Repos
import co.ruizhang.cruddemo.ui.reposearch.RepoSearch
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

object MainDestinations {
    const val ONBOARDING_ROUTE = "onboarding"
    const val REPOS_ROUTE = "repos"
    const val REPOS_SEARCH = "repos_search"
    const val REPO_DETAIL_ROUTE = "repo"
    const val REPO_DETAIL_ID = "repo_id"
}

@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalComposeUiApi
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = REPOS_ROUTE,
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
                },
                navigateToSplash = {
                    navController.navigate(ONBOARDING_ROUTE)
                }

            )
        }

        composable(REPOS_SEARCH) {
            RepoSearch(back = {
                navController.popBackStack()
            })
        }

        composable("${REPO_DETAIL_ROUTE}/{${REPO_DETAIL_ID}}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString(REPO_DETAIL_ID)?.toInt() ?: 0
            RepoDetail(id, back = { navController.popBackStack() })
        }
    }
}