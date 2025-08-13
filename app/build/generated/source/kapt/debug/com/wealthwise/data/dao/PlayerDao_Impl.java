package com.wealthwise.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wealthwise.data.database.Converters;
import com.wealthwise.data.model.Player;
import com.wealthwise.data.model.Role;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PlayerDao_Impl implements PlayerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Player> __insertionAdapterOfPlayer;

  private Converters __converters;

  private final EntityDeletionOrUpdateAdapter<Player> __deletionAdapterOfPlayer;

  private final EntityDeletionOrUpdateAdapter<Player> __updateAdapterOfPlayer;

  private final SharedSQLiteStatement __preparedStmtOfDeletePlayerById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPlayers;

  public PlayerDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlayer = new EntityInsertionAdapter<Player>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `players` (`id`,`name`,`role`,`cash`,`sacco`,`mmf`,`land`,`reits`,`debt`,`currentDay`,`gameCompleted`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Player entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final String _tmp = __converters().fromRole(entity.getRole());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        statement.bindDouble(4, entity.getCash());
        statement.bindDouble(5, entity.getSacco());
        statement.bindDouble(6, entity.getMmf());
        statement.bindDouble(7, entity.getLand());
        statement.bindDouble(8, entity.getReits());
        statement.bindDouble(9, entity.getDebt());
        statement.bindLong(10, entity.getCurrentDay());
        final int _tmp_1 = entity.getGameCompleted() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
        statement.bindLong(12, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfPlayer = new EntityDeletionOrUpdateAdapter<Player>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `players` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Player entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPlayer = new EntityDeletionOrUpdateAdapter<Player>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `players` SET `id` = ?,`name` = ?,`role` = ?,`cash` = ?,`sacco` = ?,`mmf` = ?,`land` = ?,`reits` = ?,`debt` = ?,`currentDay` = ?,`gameCompleted` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Player entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final String _tmp = __converters().fromRole(entity.getRole());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        statement.bindDouble(4, entity.getCash());
        statement.bindDouble(5, entity.getSacco());
        statement.bindDouble(6, entity.getMmf());
        statement.bindDouble(7, entity.getLand());
        statement.bindDouble(8, entity.getReits());
        statement.bindDouble(9, entity.getDebt());
        statement.bindLong(10, entity.getCurrentDay());
        final int _tmp_1 = entity.getGameCompleted() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfDeletePlayerById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM players WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllPlayers = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM players";
        return _query;
      }
    };
  }

  @Override
  public Object insertPlayer(final Player player, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPlayer.insertAndReturnId(player);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePlayer(final Player player, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPlayer.handle(player);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePlayer(final Player player, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPlayer.handle(player);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePlayerById(final long playerId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePlayerById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, playerId);
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
          __preparedStmtOfDeletePlayerById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllPlayers(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPlayers.acquire();
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
          __preparedStmtOfDeleteAllPlayers.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Player>> getAllPlayers() {
    final String _sql = "SELECT * FROM players ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"players"}, new Callable<List<Player>>() {
      @Override
      @NonNull
      public List<Player> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfCash = CursorUtil.getColumnIndexOrThrow(_cursor, "cash");
          final int _cursorIndexOfSacco = CursorUtil.getColumnIndexOrThrow(_cursor, "sacco");
          final int _cursorIndexOfMmf = CursorUtil.getColumnIndexOrThrow(_cursor, "mmf");
          final int _cursorIndexOfLand = CursorUtil.getColumnIndexOrThrow(_cursor, "land");
          final int _cursorIndexOfReits = CursorUtil.getColumnIndexOrThrow(_cursor, "reits");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDay");
          final int _cursorIndexOfGameCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "gameCompleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Player> _result = new ArrayList<Player>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Player _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Role _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters().toRole(_tmp);
            final double _tmpCash;
            _tmpCash = _cursor.getDouble(_cursorIndexOfCash);
            final double _tmpSacco;
            _tmpSacco = _cursor.getDouble(_cursorIndexOfSacco);
            final double _tmpMmf;
            _tmpMmf = _cursor.getDouble(_cursorIndexOfMmf);
            final double _tmpLand;
            _tmpLand = _cursor.getDouble(_cursorIndexOfLand);
            final double _tmpReits;
            _tmpReits = _cursor.getDouble(_cursorIndexOfReits);
            final double _tmpDebt;
            _tmpDebt = _cursor.getDouble(_cursorIndexOfDebt);
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            final boolean _tmpGameCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfGameCompleted);
            _tmpGameCompleted = _tmp_1 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Player(_tmpId,_tmpName,_tmpRole,_tmpCash,_tmpSacco,_tmpMmf,_tmpLand,_tmpReits,_tmpDebt,_tmpCurrentDay,_tmpGameCompleted,_tmpCreatedAt);
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
  public Flow<Player> getPlayerById(final long playerId) {
    final String _sql = "SELECT * FROM players WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, playerId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"players"}, new Callable<Player>() {
      @Override
      @Nullable
      public Player call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfCash = CursorUtil.getColumnIndexOrThrow(_cursor, "cash");
          final int _cursorIndexOfSacco = CursorUtil.getColumnIndexOrThrow(_cursor, "sacco");
          final int _cursorIndexOfMmf = CursorUtil.getColumnIndexOrThrow(_cursor, "mmf");
          final int _cursorIndexOfLand = CursorUtil.getColumnIndexOrThrow(_cursor, "land");
          final int _cursorIndexOfReits = CursorUtil.getColumnIndexOrThrow(_cursor, "reits");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDay");
          final int _cursorIndexOfGameCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "gameCompleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final Player _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Role _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters().toRole(_tmp);
            final double _tmpCash;
            _tmpCash = _cursor.getDouble(_cursorIndexOfCash);
            final double _tmpSacco;
            _tmpSacco = _cursor.getDouble(_cursorIndexOfSacco);
            final double _tmpMmf;
            _tmpMmf = _cursor.getDouble(_cursorIndexOfMmf);
            final double _tmpLand;
            _tmpLand = _cursor.getDouble(_cursorIndexOfLand);
            final double _tmpReits;
            _tmpReits = _cursor.getDouble(_cursorIndexOfReits);
            final double _tmpDebt;
            _tmpDebt = _cursor.getDouble(_cursorIndexOfDebt);
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            final boolean _tmpGameCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfGameCompleted);
            _tmpGameCompleted = _tmp_1 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new Player(_tmpId,_tmpName,_tmpRole,_tmpCash,_tmpSacco,_tmpMmf,_tmpLand,_tmpReits,_tmpDebt,_tmpCurrentDay,_tmpGameCompleted,_tmpCreatedAt);
          } else {
            _result = null;
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
  public Flow<Player> getCurrentPlayer() {
    final String _sql = "SELECT * FROM players WHERE gameCompleted = 0 ORDER BY createdAt DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"players"}, new Callable<Player>() {
      @Override
      @Nullable
      public Player call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfCash = CursorUtil.getColumnIndexOrThrow(_cursor, "cash");
          final int _cursorIndexOfSacco = CursorUtil.getColumnIndexOrThrow(_cursor, "sacco");
          final int _cursorIndexOfMmf = CursorUtil.getColumnIndexOrThrow(_cursor, "mmf");
          final int _cursorIndexOfLand = CursorUtil.getColumnIndexOrThrow(_cursor, "land");
          final int _cursorIndexOfReits = CursorUtil.getColumnIndexOrThrow(_cursor, "reits");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDay");
          final int _cursorIndexOfGameCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "gameCompleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final Player _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Role _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters().toRole(_tmp);
            final double _tmpCash;
            _tmpCash = _cursor.getDouble(_cursorIndexOfCash);
            final double _tmpSacco;
            _tmpSacco = _cursor.getDouble(_cursorIndexOfSacco);
            final double _tmpMmf;
            _tmpMmf = _cursor.getDouble(_cursorIndexOfMmf);
            final double _tmpLand;
            _tmpLand = _cursor.getDouble(_cursorIndexOfLand);
            final double _tmpReits;
            _tmpReits = _cursor.getDouble(_cursorIndexOfReits);
            final double _tmpDebt;
            _tmpDebt = _cursor.getDouble(_cursorIndexOfDebt);
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            final boolean _tmpGameCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfGameCompleted);
            _tmpGameCompleted = _tmp_1 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new Player(_tmpId,_tmpName,_tmpRole,_tmpCash,_tmpSacco,_tmpMmf,_tmpLand,_tmpReits,_tmpDebt,_tmpCurrentDay,_tmpGameCompleted,_tmpCreatedAt);
          } else {
            _result = null;
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
  public Flow<List<Player>> getCompletedPlayers() {
    final String _sql = "SELECT * FROM players WHERE gameCompleted = 1 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"players"}, new Callable<List<Player>>() {
      @Override
      @NonNull
      public List<Player> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfCash = CursorUtil.getColumnIndexOrThrow(_cursor, "cash");
          final int _cursorIndexOfSacco = CursorUtil.getColumnIndexOrThrow(_cursor, "sacco");
          final int _cursorIndexOfMmf = CursorUtil.getColumnIndexOrThrow(_cursor, "mmf");
          final int _cursorIndexOfLand = CursorUtil.getColumnIndexOrThrow(_cursor, "land");
          final int _cursorIndexOfReits = CursorUtil.getColumnIndexOrThrow(_cursor, "reits");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDay");
          final int _cursorIndexOfGameCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "gameCompleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Player> _result = new ArrayList<Player>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Player _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Role _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters().toRole(_tmp);
            final double _tmpCash;
            _tmpCash = _cursor.getDouble(_cursorIndexOfCash);
            final double _tmpSacco;
            _tmpSacco = _cursor.getDouble(_cursorIndexOfSacco);
            final double _tmpMmf;
            _tmpMmf = _cursor.getDouble(_cursorIndexOfMmf);
            final double _tmpLand;
            _tmpLand = _cursor.getDouble(_cursorIndexOfLand);
            final double _tmpReits;
            _tmpReits = _cursor.getDouble(_cursorIndexOfReits);
            final double _tmpDebt;
            _tmpDebt = _cursor.getDouble(_cursorIndexOfDebt);
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            final boolean _tmpGameCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfGameCompleted);
            _tmpGameCompleted = _tmp_1 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Player(_tmpId,_tmpName,_tmpRole,_tmpCash,_tmpSacco,_tmpMmf,_tmpLand,_tmpReits,_tmpDebt,_tmpCurrentDay,_tmpGameCompleted,_tmpCreatedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Arrays.asList(Converters.class);
  }

  private synchronized Converters __converters() {
    if (__converters == null) {
      __converters = __db.getTypeConverter(Converters.class);
    }
    return __converters;
  }
}
