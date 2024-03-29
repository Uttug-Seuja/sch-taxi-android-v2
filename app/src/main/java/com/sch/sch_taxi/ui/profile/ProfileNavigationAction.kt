package com.sch.sch_taxi.ui.profile


sealed class ProfileNavigationAction {
    object NavigateToBack : ProfileNavigationAction()
    object NavigateToEditProfile : ProfileNavigationAction()
    class NavigateToMoreBottomDialog(val sendUserId: Int) : ProfileNavigationAction()
}