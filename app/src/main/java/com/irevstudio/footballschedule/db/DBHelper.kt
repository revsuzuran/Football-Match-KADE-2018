package com.irevstudio.footballschedule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.irevstudio.footballschedule.model.MatchFavorites
import com.irevstudio.footballschedule.model.TeamFavorites
import org.jetbrains.anko.db.*

class DBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoritesTeam.db", null, 1){

    companion object {
        private var instance: DBHelper? = null

        @Synchronized
    fun getInstance(ctx: Context): DBHelper{
            if(instance == null){
                instance = DBHelper(ctx.applicationContext)
            }
            return instance as DBHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(TeamFavorites.TABLE_TEAM_FAVORITE, true,
                TeamFavorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamFavorites.TEAM_ID to TEXT + UNIQUE,
                TeamFavorites.TEAM_NAME to TEXT,
                TeamFavorites.TEAM_BADGE to TEXT)
        db.createTable(MatchFavorites.TABLE_MATCH_FAVORITE, true,
                MatchFavorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                MatchFavorites.MATCH_ID to TEXT + UNIQUE,
                MatchFavorites.MATCH_DATE to TEXT,
                MatchFavorites.MATCH_TIME to TEXT,
                MatchFavorites.HOME_TEAM_ID to TEXT,
                MatchFavorites.AWAY_TEAM_ID to TEXT,
                MatchFavorites.HOME_TEAM_NAME to TEXT,
                MatchFavorites.AWAY_TEAM_NAME to TEXT,
                MatchFavorites.HOME_TEAM_LOGO to TEXT,
                MatchFavorites.AWAY_TEAM_LOGO to TEXT,
                MatchFavorites.HOME_TEAM_SKOR to TEXT,
                MatchFavorites.AWAY_TEAM_SKOR to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TeamFavorites.TABLE_TEAM_FAVORITE, true)
        db.dropTable(MatchFavorites.TABLE_MATCH_FAVORITE, true)
    }

}

// Access property for Context
val Context.database: DBHelper
    get() = DBHelper.getInstance(applicationContext)