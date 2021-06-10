package co.ruizhang.cruddemo.data

import co.ruizhang.cruddemo.ui.reposearch.RepoSearchViewData

val MOCK_REPOS = listOf(
    Repository(
        id = 0,
        name = "retrofit 0",
        fullName = "octocat/retrofit",
        description = "desc retrofit 0",
        stargazersCount = 0,
        forksCount = 0,
        url = "https://api.github.com/repos/square/retrofit",
    ),

    Repository(
        id = 1,
        name = "retrofit 1",
        fullName = "octocat/retrofit",
        description = "desc retrofit 1",
        stargazersCount = 0,
        forksCount = 1,
        url = "https://api.github.com/repos/square/retrofit",
    ),

    Repository(
        id = 2,
        name = "retrofit 2",
        fullName = "octocat/retrofit",
        description = "desc retrofit 2",
        stargazersCount = 0,
        forksCount = 2,
        url = "https://api.github.com/repos/square/retrofit",
    ),

    Repository(
        id = 3,
        name = "retrofit 3",
        fullName = "octocat/retrofit",
        description = "desc retrofit 3",
        stargazersCount = 0,
        forksCount = 3,
        url = "https://api.github.com/repos/square/retrofit",
    ),

    Repository(
        id = 4,
        name = "retrofit 4",
        fullName = "octocat/retrofit",
        description = null,
        stargazersCount = 0,
        forksCount = 4,
        url = "https://api.github.com/repos/square/retrofit",
    )
)

val MOCK_SEARCH = listOf(
    RepoSearchViewData(
        id = 0,
        name = "retrofit 0",
        isChecked = false
    ),
    RepoSearchViewData(
        id = 1,
        name = "retrofit 1",
        isChecked = false
    ),
    RepoSearchViewData(
        id = 2,
        name = "retrofit 2",
        isChecked = false
    )

)