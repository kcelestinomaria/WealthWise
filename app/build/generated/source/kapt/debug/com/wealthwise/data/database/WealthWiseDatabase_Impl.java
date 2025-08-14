package com.wealthwise.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.wealthwise.data.dao.LeaderboardDao;
import com.wealthwise.data.dao.LeaderboardDao_Impl;
import com.wealthwise.data.dao.PlayerDao;
import com.wealthwise.data.dao.PlayerDao_Impl;
import com.wealthwise.data.dao.TransactionDao;
import com.wealthwise.data.dao.TransactionDao_Impl;
import com.wealthwise.data.database.dao.AchievementDao;
import com.wealthwise.data.database.dao.AchievementDao_Impl;
import com.wealthwise.data.database.dao.GameSessionDao;
import com.wealthwise.data.database.dao.GameSessionDao_Impl;
import com.wealthwise.data.database.dao.GoalDao;
import com.wealthwise.data.database.dao.GoalDao_Impl;
import com.wealthwise.data.database.dao.UserDao;
import com.wealthwise.data.database.dao.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WealthWiseDatabase_Impl extends WealthWiseDatabase {
  private volatile PlayerDao _playerDao;

  private volatile TransactionDao _transactionDao;

  private volatile LeaderboardDao _leaderboardDao;

  private volatile UserDao _userDao;

  private volatile GameSessionDao _gameSessionDao;

  private volatile GoalDao _goalDao;

  private volatile AchievementDao _achievementDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `players` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `role` TEXT NOT NULL, `cash` REAL NOT NULL, `sacco` REAL NOT NULL, `mmf` REAL NOT NULL, `land` REAL NOT NULL, `reits` REAL NOT NULL, `debt` REAL NOT NULL, `currentDay` INTEGER NOT NULL, `gameCompleted` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `transactions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playerId` INTEGER NOT NULL, `day` INTEGER NOT NULL, `type` TEXT NOT NULL, `amount` REAL NOT NULL, `description` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `leaderboard_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playerName` TEXT NOT NULL, `netWorth` REAL NOT NULL, `scenario` TEXT NOT NULL, `cash` REAL NOT NULL, `assets` REAL NOT NULL, `liabilities` REAL NOT NULL, `date` INTEGER NOT NULL, `rank` INTEGER NOT NULL, `syncedToFirebase` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`user_id` TEXT NOT NULL, `firebase_uid` TEXT, `display_name` TEXT NOT NULL, `email` TEXT, `is_anonymous` INTEGER NOT NULL, `profile_image_url` TEXT, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, `preferences` TEXT NOT NULL, `is_synced` INTEGER NOT NULL, PRIMARY KEY(`user_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `game_sessions` (`session_id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `current_day` INTEGER NOT NULL, `balance` INTEGER NOT NULL, `savings` INTEGER NOT NULL, `debt` INTEGER NOT NULL, `monthly_income` INTEGER NOT NULL, `monthly_expenses` INTEGER NOT NULL, `career` TEXT NOT NULL, `career_id` TEXT, `goals` TEXT NOT NULL, `is_completed` INTEGER NOT NULL, `completion_score` INTEGER NOT NULL, `started_at` INTEGER NOT NULL, `completed_at` INTEGER, `last_played_at` INTEGER NOT NULL, `is_synced` INTEGER NOT NULL, PRIMARY KEY(`session_id`), FOREIGN KEY(`user_id`) REFERENCES `users`(`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_game_sessions_user_id` ON `game_sessions` (`user_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `goals` (`goal_id` TEXT NOT NULL, `session_id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `target_amount` INTEGER NOT NULL, `current_amount` INTEGER NOT NULL, `category` TEXT NOT NULL, `icon` TEXT NOT NULL, `is_completed` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, `completed_at` INTEGER, `priority` INTEGER NOT NULL, `deadline` INTEGER, `is_synced` INTEGER NOT NULL, PRIMARY KEY(`goal_id`), FOREIGN KEY(`session_id`) REFERENCES `game_sessions`(`session_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_goals_session_id` ON `goals` (`session_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `transaction_entities` (`transaction_id` TEXT NOT NULL, `session_id` TEXT NOT NULL, `player_id` TEXT NOT NULL, `type` TEXT NOT NULL, `amount` INTEGER NOT NULL, `description` TEXT NOT NULL, `category` TEXT, `day` INTEGER NOT NULL, `balance_before` INTEGER NOT NULL, `balance_after` INTEGER NOT NULL, `goal_id` TEXT, `created_at` INTEGER NOT NULL, `is_synced` INTEGER NOT NULL, PRIMARY KEY(`transaction_id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_transaction_entities_session_id` ON `transaction_entities` (`session_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_transaction_entities_player_id` ON `transaction_entities` (`player_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `achievements` (`achievement_id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `icon` TEXT NOT NULL, `is_unlocked` INTEGER NOT NULL, `unlocked_at` INTEGER, `progress` INTEGER NOT NULL, `target` INTEGER NOT NULL, `reward_amount` INTEGER NOT NULL, `is_synced` INTEGER NOT NULL, PRIMARY KEY(`achievement_id`), FOREIGN KEY(`user_id`) REFERENCES `users`(`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_achievements_user_id` ON `achievements` (`user_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1781dcf026cd8d46cfefcac5c7c7c79c')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `players`");
        db.execSQL("DROP TABLE IF EXISTS `transactions`");
        db.execSQL("DROP TABLE IF EXISTS `leaderboard_entries`");
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `game_sessions`");
        db.execSQL("DROP TABLE IF EXISTS `goals`");
        db.execSQL("DROP TABLE IF EXISTS `transaction_entities`");
        db.execSQL("DROP TABLE IF EXISTS `achievements`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsPlayers = new HashMap<String, TableInfo.Column>(12);
        _columnsPlayers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("cash", new TableInfo.Column("cash", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("sacco", new TableInfo.Column("sacco", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("mmf", new TableInfo.Column("mmf", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("land", new TableInfo.Column("land", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("reits", new TableInfo.Column("reits", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("debt", new TableInfo.Column("debt", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("currentDay", new TableInfo.Column("currentDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("gameCompleted", new TableInfo.Column("gameCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlayers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlayers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlayers = new TableInfo("players", _columnsPlayers, _foreignKeysPlayers, _indicesPlayers);
        final TableInfo _existingPlayers = TableInfo.read(db, "players");
        if (!_infoPlayers.equals(_existingPlayers)) {
          return new RoomOpenHelper.ValidationResult(false, "players(com.wealthwise.data.model.Player).\n"
                  + " Expected:\n" + _infoPlayers + "\n"
                  + " Found:\n" + _existingPlayers);
        }
        final HashMap<String, TableInfo.Column> _columnsTransactions = new HashMap<String, TableInfo.Column>(7);
        _columnsTransactions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("playerId", new TableInfo.Column("playerId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("day", new TableInfo.Column("day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransactions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransactions = new TableInfo("transactions", _columnsTransactions, _foreignKeysTransactions, _indicesTransactions);
        final TableInfo _existingTransactions = TableInfo.read(db, "transactions");
        if (!_infoTransactions.equals(_existingTransactions)) {
          return new RoomOpenHelper.ValidationResult(false, "transactions(com.wealthwise.data.model.Transaction).\n"
                  + " Expected:\n" + _infoTransactions + "\n"
                  + " Found:\n" + _existingTransactions);
        }
        final HashMap<String, TableInfo.Column> _columnsLeaderboardEntries = new HashMap<String, TableInfo.Column>(10);
        _columnsLeaderboardEntries.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeaderboardEntries.put("playerName", new TableInfo.Column("playerName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeaderboardEntries.put("netWorth", new TableInfo.Column("netWorth", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeaderboardEntries.put("scenario", new TableInfo.Column("scenario", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeaderboardEntries.put("cash", new TableInfo.Column("cash", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeaderboardEntries.put("assets", new TableInfo.Column("assets", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeaderboardEntries.put("liabilities", new TableInfo.Column("liabilities", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeaderboardEntries.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeaderboardEntries.put("rank", new TableInfo.Column("rank", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeaderboardEntries.put("syncedToFirebase", new TableInfo.Column("syncedToFirebase", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLeaderboardEntries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLeaderboardEntries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLeaderboardEntries = new TableInfo("leaderboard_entries", _columnsLeaderboardEntries, _foreignKeysLeaderboardEntries, _indicesLeaderboardEntries);
        final TableInfo _existingLeaderboardEntries = TableInfo.read(db, "leaderboard_entries");
        if (!_infoLeaderboardEntries.equals(_existingLeaderboardEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "leaderboard_entries(com.wealthwise.data.model.LeaderboardEntry).\n"
                  + " Expected:\n" + _infoLeaderboardEntries + "\n"
                  + " Found:\n" + _existingLeaderboardEntries);
        }
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(10);
        _columnsUsers.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("firebase_uid", new TableInfo.Column("firebase_uid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("display_name", new TableInfo.Column("display_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("is_anonymous", new TableInfo.Column("is_anonymous", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("profile_image_url", new TableInfo.Column("profile_image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("preferences", new TableInfo.Column("preferences", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.wealthwise.data.database.entities.UserEntity).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsGameSessions = new HashMap<String, TableInfo.Column>(17);
        _columnsGameSessions.put("session_id", new TableInfo.Column("session_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("current_day", new TableInfo.Column("current_day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("balance", new TableInfo.Column("balance", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("savings", new TableInfo.Column("savings", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("debt", new TableInfo.Column("debt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("monthly_income", new TableInfo.Column("monthly_income", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("monthly_expenses", new TableInfo.Column("monthly_expenses", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("career", new TableInfo.Column("career", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("career_id", new TableInfo.Column("career_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("goals", new TableInfo.Column("goals", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("is_completed", new TableInfo.Column("is_completed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("completion_score", new TableInfo.Column("completion_score", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("started_at", new TableInfo.Column("started_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("completed_at", new TableInfo.Column("completed_at", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("last_played_at", new TableInfo.Column("last_played_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGameSessions.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGameSessions = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysGameSessions.add(new TableInfo.ForeignKey("users", "CASCADE", "NO ACTION", Arrays.asList("user_id"), Arrays.asList("user_id")));
        final HashSet<TableInfo.Index> _indicesGameSessions = new HashSet<TableInfo.Index>(1);
        _indicesGameSessions.add(new TableInfo.Index("index_game_sessions_user_id", false, Arrays.asList("user_id"), Arrays.asList("ASC")));
        final TableInfo _infoGameSessions = new TableInfo("game_sessions", _columnsGameSessions, _foreignKeysGameSessions, _indicesGameSessions);
        final TableInfo _existingGameSessions = TableInfo.read(db, "game_sessions");
        if (!_infoGameSessions.equals(_existingGameSessions)) {
          return new RoomOpenHelper.ValidationResult(false, "game_sessions(com.wealthwise.data.database.entities.GameSessionEntity).\n"
                  + " Expected:\n" + _infoGameSessions + "\n"
                  + " Found:\n" + _existingGameSessions);
        }
        final HashMap<String, TableInfo.Column> _columnsGoals = new HashMap<String, TableInfo.Column>(14);
        _columnsGoals.put("goal_id", new TableInfo.Column("goal_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("session_id", new TableInfo.Column("session_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("target_amount", new TableInfo.Column("target_amount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("current_amount", new TableInfo.Column("current_amount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("icon", new TableInfo.Column("icon", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("is_completed", new TableInfo.Column("is_completed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("completed_at", new TableInfo.Column("completed_at", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("deadline", new TableInfo.Column("deadline", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGoals = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysGoals.add(new TableInfo.ForeignKey("game_sessions", "CASCADE", "NO ACTION", Arrays.asList("session_id"), Arrays.asList("session_id")));
        final HashSet<TableInfo.Index> _indicesGoals = new HashSet<TableInfo.Index>(1);
        _indicesGoals.add(new TableInfo.Index("index_goals_session_id", false, Arrays.asList("session_id"), Arrays.asList("ASC")));
        final TableInfo _infoGoals = new TableInfo("goals", _columnsGoals, _foreignKeysGoals, _indicesGoals);
        final TableInfo _existingGoals = TableInfo.read(db, "goals");
        if (!_infoGoals.equals(_existingGoals)) {
          return new RoomOpenHelper.ValidationResult(false, "goals(com.wealthwise.data.database.entities.GoalEntity).\n"
                  + " Expected:\n" + _infoGoals + "\n"
                  + " Found:\n" + _existingGoals);
        }
        final HashMap<String, TableInfo.Column> _columnsTransactionEntities = new HashMap<String, TableInfo.Column>(13);
        _columnsTransactionEntities.put("transaction_id", new TableInfo.Column("transaction_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("session_id", new TableInfo.Column("session_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("player_id", new TableInfo.Column("player_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("amount", new TableInfo.Column("amount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("day", new TableInfo.Column("day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("balance_before", new TableInfo.Column("balance_before", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("balance_after", new TableInfo.Column("balance_after", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("goal_id", new TableInfo.Column("goal_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntities.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactionEntities = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransactionEntities = new HashSet<TableInfo.Index>(2);
        _indicesTransactionEntities.add(new TableInfo.Index("index_transaction_entities_session_id", false, Arrays.asList("session_id"), Arrays.asList("ASC")));
        _indicesTransactionEntities.add(new TableInfo.Index("index_transaction_entities_player_id", false, Arrays.asList("player_id"), Arrays.asList("ASC")));
        final TableInfo _infoTransactionEntities = new TableInfo("transaction_entities", _columnsTransactionEntities, _foreignKeysTransactionEntities, _indicesTransactionEntities);
        final TableInfo _existingTransactionEntities = TableInfo.read(db, "transaction_entities");
        if (!_infoTransactionEntities.equals(_existingTransactionEntities)) {
          return new RoomOpenHelper.ValidationResult(false, "transaction_entities(com.wealthwise.data.database.entities.TransactionEntity).\n"
                  + " Expected:\n" + _infoTransactionEntities + "\n"
                  + " Found:\n" + _existingTransactionEntities);
        }
        final HashMap<String, TableInfo.Column> _columnsAchievements = new HashMap<String, TableInfo.Column>(11);
        _columnsAchievements.put("achievement_id", new TableInfo.Column("achievement_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("icon", new TableInfo.Column("icon", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("is_unlocked", new TableInfo.Column("is_unlocked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("unlocked_at", new TableInfo.Column("unlocked_at", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("progress", new TableInfo.Column("progress", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("target", new TableInfo.Column("target", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("reward_amount", new TableInfo.Column("reward_amount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAchievements = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAchievements.add(new TableInfo.ForeignKey("users", "CASCADE", "NO ACTION", Arrays.asList("user_id"), Arrays.asList("user_id")));
        final HashSet<TableInfo.Index> _indicesAchievements = new HashSet<TableInfo.Index>(1);
        _indicesAchievements.add(new TableInfo.Index("index_achievements_user_id", false, Arrays.asList("user_id"), Arrays.asList("ASC")));
        final TableInfo _infoAchievements = new TableInfo("achievements", _columnsAchievements, _foreignKeysAchievements, _indicesAchievements);
        final TableInfo _existingAchievements = TableInfo.read(db, "achievements");
        if (!_infoAchievements.equals(_existingAchievements)) {
          return new RoomOpenHelper.ValidationResult(false, "achievements(com.wealthwise.data.database.entities.AchievementEntity).\n"
                  + " Expected:\n" + _infoAchievements + "\n"
                  + " Found:\n" + _existingAchievements);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1781dcf026cd8d46cfefcac5c7c7c79c", "10de00a45a4c1c7d1c3c490059c9a876");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "players","transactions","leaderboard_entries","users","game_sessions","goals","transaction_entities","achievements");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `players`");
      _db.execSQL("DELETE FROM `transactions`");
      _db.execSQL("DELETE FROM `leaderboard_entries`");
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `game_sessions`");
      _db.execSQL("DELETE FROM `goals`");
      _db.execSQL("DELETE FROM `transaction_entities`");
      _db.execSQL("DELETE FROM `achievements`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PlayerDao.class, PlayerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TransactionDao.class, TransactionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LeaderboardDao.class, LeaderboardDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GameSessionDao.class, GameSessionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GoalDao.class, GoalDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AchievementDao.class, AchievementDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public PlayerDao playerDao() {
    if (_playerDao != null) {
      return _playerDao;
    } else {
      synchronized(this) {
        if(_playerDao == null) {
          _playerDao = new PlayerDao_Impl(this);
        }
        return _playerDao;
      }
    }
  }

  @Override
  public TransactionDao transactionDao() {
    if (_transactionDao != null) {
      return _transactionDao;
    } else {
      synchronized(this) {
        if(_transactionDao == null) {
          _transactionDao = new TransactionDao_Impl(this);
        }
        return _transactionDao;
      }
    }
  }

  @Override
  public LeaderboardDao leaderboardDao() {
    if (_leaderboardDao != null) {
      return _leaderboardDao;
    } else {
      synchronized(this) {
        if(_leaderboardDao == null) {
          _leaderboardDao = new LeaderboardDao_Impl(this);
        }
        return _leaderboardDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public GameSessionDao gameSessionDao() {
    if (_gameSessionDao != null) {
      return _gameSessionDao;
    } else {
      synchronized(this) {
        if(_gameSessionDao == null) {
          _gameSessionDao = new GameSessionDao_Impl(this);
        }
        return _gameSessionDao;
      }
    }
  }

  @Override
  public GoalDao goalDao() {
    if (_goalDao != null) {
      return _goalDao;
    } else {
      synchronized(this) {
        if(_goalDao == null) {
          _goalDao = new GoalDao_Impl(this);
        }
        return _goalDao;
      }
    }
  }

  @Override
  public AchievementDao achievementDao() {
    if (_achievementDao != null) {
      return _achievementDao;
    } else {
      synchronized(this) {
        if(_achievementDao == null) {
          _achievementDao = new AchievementDao_Impl(this);
        }
        return _achievementDao;
      }
    }
  }
}
