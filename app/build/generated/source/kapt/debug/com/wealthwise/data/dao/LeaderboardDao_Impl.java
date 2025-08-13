package com.wealthwise.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wealthwise.data.model.LeaderboardEntry;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class LeaderboardDao_Impl implements LeaderboardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LeaderboardEntry> __insertionAdapterOfLeaderboardEntry;

  private final EntityDeletionOrUpdateAdapter<LeaderboardEntry> __deletionAdapterOfLeaderboardEntry;

  private final EntityDeletionOrUpdateAdapter<LeaderboardEntry> __updateAdapterOfLeaderboardEntry;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynced;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllEntries;

  public LeaderboardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLeaderboardEntry = new EntityInsertionAdapter<LeaderboardEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `leaderboard_entries` (`id`,`playerName`,`netWorth`,`scenario`,`cash`,`assets`,`liabilities`,`date`,`rank`,`syncedToFirebase`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LeaderboardEntry entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getPlayerName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPlayerName());
        }
        statement.bindDouble(3, entity.getNetWorth());
        if (entity.getScenario() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getScenario());
        }
        statement.bindDouble(5, entity.getCash());
        statement.bindDouble(6, entity.getAssets());
        statement.bindDouble(7, entity.getLiabilities());
        statement.bindLong(8, entity.getDate());
        statement.bindLong(9, entity.getRank());
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(10, _tmp);
      }
    };
    this.__deletionAdapterOfLeaderboardEntry = new EntityDeletionOrUpdateAdapter<LeaderboardEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `leaderboard_entries` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LeaderboardEntry entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfLeaderboardEntry = new EntityDeletionOrUpdateAdapter<LeaderboardEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `leaderboard_entries` SET `id` = ?,`playerName` = ?,`netWorth` = ?,`scenario` = ?,`cash` = ?,`assets` = ?,`liabilities` = ?,`date` = ?,`rank` = ?,`syncedToFirebase` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LeaderboardEntry entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getPlayerName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPlayerName());
        }
        statement.bindDouble(3, entity.getNetWorth());
        if (entity.getScenario() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getScenario());
        }
        statement.bindDouble(5, entity.getCash());
        statement.bindDouble(6, entity.getAssets());
        statement.bindDouble(7, entity.getLiabilities());
        statement.bindLong(8, entity.getDate());
        statement.bindLong(9, entity.getRank());
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getId());
      }
    };
    this.__preparedStmtOfMarkAsSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE leaderboard_entries SET syncedToFirebase = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllEntries = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM leaderboard_entries";
        return _query;
      }
    };
  }

  @Override
  public Object insertEntry(final LeaderboardEntry entry,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfLeaderboardEntry.insertAndReturnId(entry);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertEntries(final List<LeaderboardEntry> entries,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLeaderboardEntry.insert(entries);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteEntry(final LeaderboardEntry entry,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLeaderboardEntry.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateEntry(final LeaderboardEntry entry,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLeaderboardEntry.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsSynced(final long entryId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsSynced.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, entryId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfMarkAsSynced.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllEntries(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllEntries.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllEntries.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<LeaderboardEntry>> getAllEntries() {
    final String _sql = "SELECT * FROM leaderboard_entries ORDER BY netWorth DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leaderboard_entries"}, new Callable<List<LeaderboardEntry>>() {
      @Override
      @NonNull
      public List<LeaderboardEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlayerName = CursorUtil.getColumnIndexOrThrow(_cursor, "playerName");
          final int _cursorIndexOfNetWorth = CursorUtil.getColumnIndexOrThrow(_cursor, "netWorth");
          final int _cursorIndexOfScenario = CursorUtil.getColumnIndexOrThrow(_cursor, "scenario");
          final int _cursorIndexOfCash = CursorUtil.getColumnIndexOrThrow(_cursor, "cash");
          final int _cursorIndexOfAssets = CursorUtil.getColumnIndexOrThrow(_cursor, "assets");
          final int _cursorIndexOfLiabilities = CursorUtil.getColumnIndexOrThrow(_cursor, "liabilities");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfRank = CursorUtil.getColumnIndexOrThrow(_cursor, "rank");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<LeaderboardEntry> _result = new ArrayList<LeaderboardEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LeaderboardEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPlayerName;
            if (_cursor.isNull(_cursorIndexOfPlayerName)) {
              _tmpPlayerName = null;
            } else {
              _tmpPlayerName = _cursor.getString(_cursorIndexOfPlayerName);
            }
            final double _tmpNetWorth;
            _tmpNetWorth = _cursor.getDouble(_cursorIndexOfNetWorth);
            final String _tmpScenario;
            if (_cursor.isNull(_cursorIndexOfScenario)) {
              _tmpScenario = null;
            } else {
              _tmpScenario = _cursor.getString(_cursorIndexOfScenario);
            }
            final double _tmpCash;
            _tmpCash = _cursor.getDouble(_cursorIndexOfCash);
            final double _tmpAssets;
            _tmpAssets = _cursor.getDouble(_cursorIndexOfAssets);
            final double _tmpLiabilities;
            _tmpLiabilities = _cursor.getDouble(_cursorIndexOfLiabilities);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpRank;
            _tmpRank = _cursor.getInt(_cursorIndexOfRank);
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new LeaderboardEntry(_tmpId,_tmpPlayerName,_tmpNetWorth,_tmpScenario,_tmpCash,_tmpAssets,_tmpLiabilities,_tmpDate,_tmpRank,_tmpSyncedToFirebase);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<LeaderboardEntry>> getTopEntries(final int limit) {
    final String _sql = "SELECT * FROM leaderboard_entries ORDER BY netWorth DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leaderboard_entries"}, new Callable<List<LeaderboardEntry>>() {
      @Override
      @NonNull
      public List<LeaderboardEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlayerName = CursorUtil.getColumnIndexOrThrow(_cursor, "playerName");
          final int _cursorIndexOfNetWorth = CursorUtil.getColumnIndexOrThrow(_cursor, "netWorth");
          final int _cursorIndexOfScenario = CursorUtil.getColumnIndexOrThrow(_cursor, "scenario");
          final int _cursorIndexOfCash = CursorUtil.getColumnIndexOrThrow(_cursor, "cash");
          final int _cursorIndexOfAssets = CursorUtil.getColumnIndexOrThrow(_cursor, "assets");
          final int _cursorIndexOfLiabilities = CursorUtil.getColumnIndexOrThrow(_cursor, "liabilities");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfRank = CursorUtil.getColumnIndexOrThrow(_cursor, "rank");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<LeaderboardEntry> _result = new ArrayList<LeaderboardEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LeaderboardEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPlayerName;
            if (_cursor.isNull(_cursorIndexOfPlayerName)) {
              _tmpPlayerName = null;
            } else {
              _tmpPlayerName = _cursor.getString(_cursorIndexOfPlayerName);
            }
            final double _tmpNetWorth;
            _tmpNetWorth = _cursor.getDouble(_cursorIndexOfNetWorth);
            final String _tmpScenario;
            if (_cursor.isNull(_cursorIndexOfScenario)) {
              _tmpScenario = null;
            } else {
              _tmpScenario = _cursor.getString(_cursorIndexOfScenario);
            }
            final double _tmpCash;
            _tmpCash = _cursor.getDouble(_cursorIndexOfCash);
            final double _tmpAssets;
            _tmpAssets = _cursor.getDouble(_cursorIndexOfAssets);
            final double _tmpLiabilities;
            _tmpLiabilities = _cursor.getDouble(_cursorIndexOfLiabilities);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpRank;
            _tmpRank = _cursor.getInt(_cursorIndexOfRank);
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new LeaderboardEntry(_tmpId,_tmpPlayerName,_tmpNetWorth,_tmpScenario,_tmpCash,_tmpAssets,_tmpLiabilities,_tmpDate,_tmpRank,_tmpSyncedToFirebase);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<LeaderboardEntry>> getTopEntriesByRole(final String scenario, final int limit) {
    final String _sql = "SELECT * FROM leaderboard_entries WHERE scenario = ? ORDER BY netWorth DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (scenario == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, scenario);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leaderboard_entries"}, new Callable<List<LeaderboardEntry>>() {
      @Override
      @NonNull
      public List<LeaderboardEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlayerName = CursorUtil.getColumnIndexOrThrow(_cursor, "playerName");
          final int _cursorIndexOfNetWorth = CursorUtil.getColumnIndexOrThrow(_cursor, "netWorth");
          final int _cursorIndexOfScenario = CursorUtil.getColumnIndexOrThrow(_cursor, "scenario");
          final int _cursorIndexOfCash = CursorUtil.getColumnIndexOrThrow(_cursor, "cash");
          final int _cursorIndexOfAssets = CursorUtil.getColumnIndexOrThrow(_cursor, "assets");
          final int _cursorIndexOfLiabilities = CursorUtil.getColumnIndexOrThrow(_cursor, "liabilities");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfRank = CursorUtil.getColumnIndexOrThrow(_cursor, "rank");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<LeaderboardEntry> _result = new ArrayList<LeaderboardEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LeaderboardEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPlayerName;
            if (_cursor.isNull(_cursorIndexOfPlayerName)) {
              _tmpPlayerName = null;
            } else {
              _tmpPlayerName = _cursor.getString(_cursorIndexOfPlayerName);
            }
            final double _tmpNetWorth;
            _tmpNetWorth = _cursor.getDouble(_cursorIndexOfNetWorth);
            final String _tmpScenario;
            if (_cursor.isNull(_cursorIndexOfScenario)) {
              _tmpScenario = null;
            } else {
              _tmpScenario = _cursor.getString(_cursorIndexOfScenario);
            }
            final double _tmpCash;
            _tmpCash = _cursor.getDouble(_cursorIndexOfCash);
            final double _tmpAssets;
            _tmpAssets = _cursor.getDouble(_cursorIndexOfAssets);
            final double _tmpLiabilities;
            _tmpLiabilities = _cursor.getDouble(_cursorIndexOfLiabilities);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpRank;
            _tmpRank = _cursor.getInt(_cursorIndexOfRank);
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new LeaderboardEntry(_tmpId,_tmpPlayerName,_tmpNetWorth,_tmpScenario,_tmpCash,_tmpAssets,_tmpLiabilities,_tmpDate,_tmpRank,_tmpSyncedToFirebase);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getUnsyncedEntries(final Continuation<? super List<LeaderboardEntry>> $completion) {
    final String _sql = "SELECT * FROM leaderboard_entries WHERE syncedToFirebase = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<LeaderboardEntry>>() {
      @Override
      @NonNull
      public List<LeaderboardEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlayerName = CursorUtil.getColumnIndexOrThrow(_cursor, "playerName");
          final int _cursorIndexOfNetWorth = CursorUtil.getColumnIndexOrThrow(_cursor, "netWorth");
          final int _cursorIndexOfScenario = CursorUtil.getColumnIndexOrThrow(_cursor, "scenario");
          final int _cursorIndexOfCash = CursorUtil.getColumnIndexOrThrow(_cursor, "cash");
          final int _cursorIndexOfAssets = CursorUtil.getColumnIndexOrThrow(_cursor, "assets");
          final int _cursorIndexOfLiabilities = CursorUtil.getColumnIndexOrThrow(_cursor, "liabilities");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfRank = CursorUtil.getColumnIndexOrThrow(_cursor, "rank");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<LeaderboardEntry> _result = new ArrayList<LeaderboardEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LeaderboardEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPlayerName;
            if (_cursor.isNull(_cursorIndexOfPlayerName)) {
              _tmpPlayerName = null;
            } else {
              _tmpPlayerName = _cursor.getString(_cursorIndexOfPlayerName);
            }
            final double _tmpNetWorth;
            _tmpNetWorth = _cursor.getDouble(_cursorIndexOfNetWorth);
            final String _tmpScenario;
            if (_cursor.isNull(_cursorIndexOfScenario)) {
              _tmpScenario = null;
            } else {
              _tmpScenario = _cursor.getString(_cursorIndexOfScenario);
            }
            final double _tmpCash;
            _tmpCash = _cursor.getDouble(_cursorIndexOfCash);
            final double _tmpAssets;
            _tmpAssets = _cursor.getDouble(_cursorIndexOfAssets);
            final double _tmpLiabilities;
            _tmpLiabilities = _cursor.getDouble(_cursorIndexOfLiabilities);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpRank;
            _tmpRank = _cursor.getInt(_cursorIndexOfRank);
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new LeaderboardEntry(_tmpId,_tmpPlayerName,_tmpNetWorth,_tmpScenario,_tmpCash,_tmpAssets,_tmpLiabilities,_tmpDate,_tmpRank,_tmpSyncedToFirebase);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
