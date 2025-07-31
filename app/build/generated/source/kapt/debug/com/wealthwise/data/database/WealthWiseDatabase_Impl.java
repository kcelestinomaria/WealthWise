package com.wealthwise.data.database;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.wealthwise.data.dao.LeaderboardDao;
import com.wealthwise.data.dao.LeaderboardDao_Impl;
import com.wealthwise.data.dao.PlayerDao;
import com.wealthwise.data.dao.PlayerDao_Impl;
import com.wealthwise.data.dao.TransactionDao;
import com.wealthwise.data.dao.TransactionDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class WealthWiseDatabase_Impl extends WealthWiseDatabase {
  private volatile PlayerDao _playerDao;

  private volatile TransactionDao _transactionDao;

  private volatile LeaderboardDao _leaderboardDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(1, "20201a4f24b88c08dd290b89094c7f64", "5d5aaccc69653c88119d244a34e5bbd8") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `players` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `role` TEXT NOT NULL, `cash` REAL NOT NULL, `sacco` REAL NOT NULL, `mmf` REAL NOT NULL, `land` REAL NOT NULL, `reits` REAL NOT NULL, `debt` REAL NOT NULL, `currentDay` INTEGER NOT NULL, `gameCompleted` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `transactions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playerId` INTEGER NOT NULL, `day` INTEGER NOT NULL, `type` TEXT NOT NULL, `amount` REAL NOT NULL, `description` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `leaderboard_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playerName` TEXT NOT NULL, `netWorth` REAL NOT NULL, `scenario` TEXT NOT NULL, `cash` REAL NOT NULL, `assets` REAL NOT NULL, `liabilities` REAL NOT NULL, `date` INTEGER NOT NULL, `rank` INTEGER NOT NULL, `syncedToFirebase` INTEGER NOT NULL)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '20201a4f24b88c08dd290b89094c7f64')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `players`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `transactions`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `leaderboard_entries`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsPlayers = new HashMap<String, TableInfo.Column>(12);
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
        final Set<TableInfo.ForeignKey> _foreignKeysPlayers = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesPlayers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlayers = new TableInfo("players", _columnsPlayers, _foreignKeysPlayers, _indicesPlayers);
        final TableInfo _existingPlayers = TableInfo.read(connection, "players");
        if (!_infoPlayers.equals(_existingPlayers)) {
          return new RoomOpenDelegate.ValidationResult(false, "players(com.wealthwise.data.model.Player).\n"
                  + " Expected:\n" + _infoPlayers + "\n"
                  + " Found:\n" + _existingPlayers);
        }
        final Map<String, TableInfo.Column> _columnsTransactions = new HashMap<String, TableInfo.Column>(7);
        _columnsTransactions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("playerId", new TableInfo.Column("playerId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("day", new TableInfo.Column("day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysTransactions = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesTransactions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransactions = new TableInfo("transactions", _columnsTransactions, _foreignKeysTransactions, _indicesTransactions);
        final TableInfo _existingTransactions = TableInfo.read(connection, "transactions");
        if (!_infoTransactions.equals(_existingTransactions)) {
          return new RoomOpenDelegate.ValidationResult(false, "transactions(com.wealthwise.data.model.Transaction).\n"
                  + " Expected:\n" + _infoTransactions + "\n"
                  + " Found:\n" + _existingTransactions);
        }
        final Map<String, TableInfo.Column> _columnsLeaderboardEntries = new HashMap<String, TableInfo.Column>(10);
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
        final Set<TableInfo.ForeignKey> _foreignKeysLeaderboardEntries = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesLeaderboardEntries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLeaderboardEntries = new TableInfo("leaderboard_entries", _columnsLeaderboardEntries, _foreignKeysLeaderboardEntries, _indicesLeaderboardEntries);
        final TableInfo _existingLeaderboardEntries = TableInfo.read(connection, "leaderboard_entries");
        if (!_infoLeaderboardEntries.equals(_existingLeaderboardEntries)) {
          return new RoomOpenDelegate.ValidationResult(false, "leaderboard_entries(com.wealthwise.data.model.LeaderboardEntry).\n"
                  + " Expected:\n" + _infoLeaderboardEntries + "\n"
                  + " Found:\n" + _existingLeaderboardEntries);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "players", "transactions", "leaderboard_entries");
  }

  @Override
  public void clearAllTables() {
    super.performClear(false, "players", "transactions", "leaderboard_entries");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PlayerDao.class, PlayerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TransactionDao.class, TransactionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LeaderboardDao.class, LeaderboardDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
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
}
