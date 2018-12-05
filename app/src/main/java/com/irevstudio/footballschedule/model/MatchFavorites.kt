package com.irevstudio.footballschedule.model

data class MatchFavorites(val id: Long?, val matchId: String?,
                          val matchDate: String?, val matchTime: String?,
                          val homeTeamId: String?, val awayTeamId: String?,
                          val homeTeamName: String, val awayTeamName: String?,
                          val homeTeamLogo: String?, val awayTeamLogo: String?,
                          val homeTeamSkor: String?, val awayTeamSkor: String?){
    companion object {
        const val TABLE_MATCH_FAVORITE: String = "TABLE_MATCH_FAVORITE"
        const val ID: String = "ID"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val MATCH_TIME: String = "MATCH_TIME"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_TEAM_LOGO: String = "HOME_TEAM_LOGO"
        const val AWAY_TEAM_LOGO: String = "AWAY_TEAM_LOGO"
        const val HOME_TEAM_SKOR: String = "HOME_TEAM_SKOR"
        const val AWAY_TEAM_SKOR: String = "AWAY_TEAM_SKOR"

    }
}