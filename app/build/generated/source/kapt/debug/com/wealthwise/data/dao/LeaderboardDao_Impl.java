package com.wealthwise.data.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.wealthwise.data.model.LeaderboardEntry;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class LeaderboardDao_Impl implements LeaderboardDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<LeaderboardEntry> __insertAdapterOfLeaderboardEntry;

  private final EntityDeleteOrUpdateAdapter<LeaderboardEntry> __deleteAdapterOfLeaderboardEntry;

  private final EntityDeleteOrUpdateAdapter<LeaderboardEntry> __updateAdapterOfLeaderboardEntry;

  public LeaderboardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfLeaderboardEntry = new EntityInsertAdapter<LeaderboardEntry>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `leaderboard_entries` (`id`,`playerName`,`netWorth`,`scenario`,`cash`,`assets`,`liabilities`,`date`,`rank`,`syncedToFirebase`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final LeaderboardEntry entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getPlayerName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getPlayerName());
        }
        statement.bindDouble(3, entity.getNetWorth());
        if (entity.getScenario() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getScenario());
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
    this.__deleteAdapterOfLeaderboardEntry = new EntityDeleteOrUpdateAdapter<LeaderboardEntry>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `leaderboard_entries` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final LeaderboardEntry entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfLeaderboardEntry = new EntityDeleteOrUpdateAdapter<LeaderboardEntry>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `leaderboard_entries` SET `id` = ?,`playerName` = ?,`netWorth` = ?,`scenario` = ?,`cash` = ?,`assets` = ?,`liabilities` = ?,`date` = ?,`rank` = ?,`syncedToFirebase` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final LeaderboardEntry entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getPlayerName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getPlayerName());
        }
        statement.bindDouble(3, entity.getNetWorth());
        if (entity.getScenario() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getScenario());
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
  }

  @Override
  public Object insertEntry(final LeaderboardEntry entry,
      final Continuation<? super Long> $completion) {
    if (entry == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfLeaderboardEntry.insertAndReturnId(_connection, entry);
    }, $completion);
  }

  @Override
  public Object insertEntries(final List<LeaderboardEntry> entries,
      final Continuation<? super Unit> $completion) {
    if (entries == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfLeaderboardEntry.insert(_connection, entries);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object deleteEntry(final LeaderboardEntry entry,
      final Continuation<? super Unit> $completion) {
    if (entry == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfLeaderboardEntry.handle(_connection, entry);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateEntry(final LeaderboardEntry entry,
      final Continuation<? super Unit> $completion) {
    if (entry == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfLeaderboardEntry.handle(_connection, entry);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<LeaderboardEntry>> getAllEntries() {
    final String _sql = "SELECT * FROM leaderboard_entries ORDER BY netWorth DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"leaderboard_entries"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfPlayerName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "playerName");
        final int _columnIndexOfNetWorth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "netWorth");
        final int _columnIndexOfScenario = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "scenario");
        final int _columnIndexOfCash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cash");
        final int _columnIndexOfAssets = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assets");
        final int _columnIndexOfLiabilities = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "liabilities");
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfRank = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rank");
        final int _columnIndexOfSyncedToFirebase = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncedToFirebase");
        final List<LeaderboardEntry> _result = new ArrayList<LeaderboardEntry>();
        while (_stmt.step()) {
          final LeaderboardEntry _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpPlayerName;
          if (_stmt.isNull(_columnIndexOfPlayerName)) {
            _tmpPlayerName = null;
          } else {
            _tmpPlayerName = _stmt.getText(_columnIndexOfPlayerName);
          }
          final double _tmpNetWorth;
          _tmpNetWorth = _stmt.getDouble(_columnIndexOfNetWorth);
          final String _tmpScenario;
          if (_stmt.isNull(_columnIndexOfScenario)) {
            _tmpScenario = null;
          } else {
            _tmpScenario = _stmt.getText(_columnIndexOfScenario);
          }
          final double _tmpCash;
          _tmpCash = _stmt.getDouble(_columnIndexOfCash);
          final double _tmpAssets;
          _tmpAssets = _stmt.getDouble(_columnIndexOfAssets);
          final double _tmpLiabilities;
          _tmpLiabilities = _stmt.getDouble(_columnIndexOfLiabilities);
          final long _tmpDate;
          _tmpDate = _stmt.getLong(_columnIndexOfDate);
          final int _tmpRank;
          _tmpRank = (int) (_stmt.getLong(_columnIndexOfRank));
          final boolean _tmpSyncedToFirebase;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfSyncedToFirebase));
          _tmpSyncedToFirebase = _tmp != 0;
          _item = new LeaderboardEntry(_tmpId,_tmpPlayerName,_tmpNetWorth,_tmpScenario,_tmpCash,_tmpAssets,_tmpLiabilities,_tmpDate,_tmpRank,_tmpSyncedToFirebase);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<LeaderboardEntry>> getTopEntries(final int limit) {
    final String _sql = "SELECT * FROM leaderboard_entries ORDER BY netWorth DESC LIMIT ?";
    return FlowUtil.createFlow(__db, false, new String[] {"leaderboard_entries"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, limit);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfPlayerName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "playerName");
        final int _columnIndexOfNetWorth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "netWorth");
        final int _columnIndexOfScenario = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "scenario");
        final int _columnIndexOfCash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cash");
        final int _columnIndexOfAssets = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assets");
        final int _columnIndexOfLiabilities = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "liabilities");
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfRank = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rank");
        final int _columnIndexOfSyncedToFirebase = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncedToFirebase");
        final List<LeaderboardEntry> _result = new ArrayList<LeaderboardEntry>();
        while (_stmt.step()) {
          final LeaderboardEntry _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpPlayerName;
          if (_stmt.isNull(_columnIndexOfPlayerName)) {
            _tmpPlayerName = null;
          } else {
            _tmpPlayerName = _stmt.getText(_columnIndexOfPlayerName);
          }
          final double _tmpNetWorth;
          _tmpNetWorth = _stmt.getDouble(_columnIndexOfNetWorth);
          final String _tmpScenario;
          if (_stmt.isNull(_columnIndexOfScenario)) {
            _tmpScenario = null;
          } else {
            _tmpScenario = _stmt.getText(_columnIndexOfScenario);
          }
          final double _tmpCash;
          _tmpCash = _stmt.getDouble(_columnIndexOfCash);
          final double _tmpAssets;
          _tmpAssets = _stmt.getDouble(_columnIndexOfAssets);
          final double _tmpLiabilities;
          _tmpLiabilities = _stmt.getDouble(_columnIndexOfLiabilities);
          final long _tmpDate;
          _tmpDate = _stmt.getLong(_columnIndexOfDate);
          final int _tmpRank;
          _tmpRank = (int) (_stmt.getLong(_columnIndexOfRank));
          final boolean _tmpSyncedToFirebase;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfSyncedToFirebase));
          _tmpSyncedToFirebase = _tmp != 0;
          _item = new LeaderboardEntry(_tmpId,_tmpPlayerName,_tmpNetWorth,_tmpScenario,_tmpCash,_tmpAssets,_tmpLiabilities,_tmpDate,_tmpRank,_tmpSyncedToFirebase);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<LeaderboardEntry>> getTopEntriesByRole(final String scenario, final int limit) {
    final String _sql = "SELECT * FROM leaderboard_entries WHERE scenario = ? ORDER BY netWorth DESC LIMIT ?";
    return FlowUtil.createFlow(__db, false, new String[] {"leaderboard_entries"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (scenario == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, scenario);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, limit);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfPlayerName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "playerName");
        final int _columnIndexOfNetWorth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "netWorth");
        final int _columnIndexOfScenario = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "scenario");
        final int _columnIndexOfCash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cash");
        final int _columnIndexOfAssets = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assets");
        final int _columnIndexOfLiabilities = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "liabilities");
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfRank = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rank");
        final int _columnIndexOfSyncedToFirebase = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncedToFirebase");
        final List<LeaderboardEntry> _result = new ArrayList<LeaderboardEntry>();
        while (_stmt.step()) {
          final LeaderboardEntry _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpPlayerName;
          if (_stmt.isNull(_columnIndexOfPlayerName)) {
            _tmpPlayerName = null;
          } else {
            _tmpPlayerName = _stmt.getText(_columnIndexOfPlayerName);
          }
          final double _tmpNetWorth;
          _tmpNetWorth = _stmt.getDouble(_columnIndexOfNetWorth);
          final String _tmpScenario;
          if (_stmt.isNull(_columnIndexOfScenario)) {
            _tmpScenario = null;
          } else {
            _tmpScenario = _stmt.getText(_columnIndexOfScenario);
          }
          final double _tmpCash;
          _tmpCash = _stmt.getDouble(_columnIndexOfCash);
          final double _tmpAssets;
          _tmpAssets = _stmt.getDouble(_columnIndexOfAssets);
          final double _tmpLiabilities;
          _tmpLiabilities = _stmt.getDouble(_columnIndexOfLiabilities);
          final long _tmpDate;
          _tmpDate = _stmt.getLong(_columnIndexOfDate);
          final int _tmpRank;
          _tmpRank = (int) (_stmt.getLong(_columnIndexOfRank));
          final boolean _tmpSyncedToFirebase;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfSyncedToFirebase));
          _tmpSyncedToFirebase = _tmp != 0;
          _item = new LeaderboardEntry(_tmpId,_tmpPlayerName,_tmpNetWorth,_tmpScenario,_tmpCash,_tmpAssets,_tmpLiabilities,_tmpDate,_tmpRank,_tmpSyncedToFirebase);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getUnsyncedEntries(final Continuation<? super List<LeaderboardEntry>> $completion) {
    final String _sql = "SELECT * FROM leaderboard_entries WHERE syncedToFirebase = 0";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfPlayerName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "playerName");
        final int _columnIndexOfNetWorth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "netWorth");
        final int _columnIndexOfScenario = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "scenario");
        final int _columnIndexOfCash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cash");
        final int _columnIndexOfAssets = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assets");
        final int _columnIndexOfLiabilities = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "liabilities");
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfRank = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rank");
        final int _columnIndexOfSyncedToFirebase = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncedToFirebase");
        final List<LeaderboardEntry> _result = new ArrayList<LeaderboardEntry>();
        while (_stmt.step()) {
          final LeaderboardEntry _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpPlayerName;
          if (_stmt.isNull(_columnIndexOfPlayerName)) {
            _tmpPlayerName = null;
          } else {
            _tmpPlayerName = _stmt.getText(_columnIndexOfPlayerName);
          }
          final double _tmpNetWorth;
          _tmpNetWorth = _stmt.getDouble(_columnIndexOfNetWorth);
          final String _tmpScenario;
          if (_stmt.isNull(_columnIndexOfScenario)) {
            _tmpScenario = null;
          } else {
            _tmpScenario = _stmt.getText(_columnIndexOfScenario);
          }
          final double _tmpCash;
          _tmpCash = _stmt.getDouble(_columnIndexOfCash);
          final double _tmpAssets;
          _tmpAssets = _stmt.getDouble(_columnIndexOfAssets);
          final double _tmpLiabilities;
          _tmpLiabilities = _stmt.getDouble(_columnIndexOfLiabilities);
          final long _tmpDate;
          _tmpDate = _stmt.getLong(_columnIndexOfDate);
          final int _tmpRank;
          _tmpRank = (int) (_stmt.getLong(_columnIndexOfRank));
          final boolean _tmpSyncedToFirebase;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfSyncedToFirebase));
          _tmpSyncedToFirebase = _tmp != 0;
          _item = new LeaderboardEntry(_tmpId,_tmpPlayerName,_tmpNetWorth,_tmpScenario,_tmpCash,_tmpAssets,_tmpLiabilities,_tmpDate,_tmpRank,_tmpSyncedToFirebase);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object markAsSynced(final long entryId, final Continuation<? super Unit> $completion) {
    final String _sql = "UPDATE leaderboard_entries SET syncedToFirebase = 1 WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, entryId);
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAllEntries(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM leaderboard_entries";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
