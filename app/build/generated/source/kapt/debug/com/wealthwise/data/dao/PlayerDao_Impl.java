package com.wealthwise.data.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.wealthwise.data.database.Converters;
import com.wealthwise.data.model.Player;
import com.wealthwise.data.model.Role;
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
public final class PlayerDao_Impl implements PlayerDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Player> __insertAdapterOfPlayer;

  private final Converters __converters = new Converters();

  private final EntityDeleteOrUpdateAdapter<Player> __deleteAdapterOfPlayer;

  private final EntityDeleteOrUpdateAdapter<Player> __updateAdapterOfPlayer;

  public PlayerDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfPlayer = new EntityInsertAdapter<Player>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `players` (`id`,`name`,`role`,`cash`,`sacco`,`mmf`,`land`,`reits`,`debt`,`currentDay`,`gameCompleted`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Player entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        final String _tmp = __converters.fromRole(entity.getRole());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, _tmp);
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
    this.__deleteAdapterOfPlayer = new EntityDeleteOrUpdateAdapter<Player>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `players` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Player entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPlayer = new EntityDeleteOrUpdateAdapter<Player>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `players` SET `id` = ?,`name` = ?,`role` = ?,`cash` = ?,`sacco` = ?,`mmf` = ?,`land` = ?,`reits` = ?,`debt` = ?,`currentDay` = ?,`gameCompleted` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Player entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        final String _tmp = __converters.fromRole(entity.getRole());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, _tmp);
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
  }

  @Override
  public Object insertPlayer(final Player player, final Continuation<? super Long> $completion) {
    if (player == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfPlayer.insertAndReturnId(_connection, player);
    }, $completion);
  }

  @Override
  public Object deletePlayer(final Player player, final Continuation<? super Unit> $completion) {
    if (player == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfPlayer.handle(_connection, player);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updatePlayer(final Player player, final Continuation<? super Unit> $completion) {
    if (player == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfPlayer.handle(_connection, player);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<Player>> getAllPlayers() {
    final String _sql = "SELECT * FROM players ORDER BY createdAt DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"players"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfCash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cash");
        final int _columnIndexOfSacco = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sacco");
        final int _columnIndexOfMmf = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mmf");
        final int _columnIndexOfLand = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "land");
        final int _columnIndexOfReits = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reits");
        final int _columnIndexOfDebt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "debt");
        final int _columnIndexOfCurrentDay = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentDay");
        final int _columnIndexOfGameCompleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gameCompleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final List<Player> _result = new ArrayList<Player>();
        while (_stmt.step()) {
          final Player _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final Role _tmpRole;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfRole)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfRole);
          }
          _tmpRole = __converters.toRole(_tmp);
          final double _tmpCash;
          _tmpCash = _stmt.getDouble(_columnIndexOfCash);
          final double _tmpSacco;
          _tmpSacco = _stmt.getDouble(_columnIndexOfSacco);
          final double _tmpMmf;
          _tmpMmf = _stmt.getDouble(_columnIndexOfMmf);
          final double _tmpLand;
          _tmpLand = _stmt.getDouble(_columnIndexOfLand);
          final double _tmpReits;
          _tmpReits = _stmt.getDouble(_columnIndexOfReits);
          final double _tmpDebt;
          _tmpDebt = _stmt.getDouble(_columnIndexOfDebt);
          final int _tmpCurrentDay;
          _tmpCurrentDay = (int) (_stmt.getLong(_columnIndexOfCurrentDay));
          final boolean _tmpGameCompleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfGameCompleted));
          _tmpGameCompleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _item = new Player(_tmpId,_tmpName,_tmpRole,_tmpCash,_tmpSacco,_tmpMmf,_tmpLand,_tmpReits,_tmpDebt,_tmpCurrentDay,_tmpGameCompleted,_tmpCreatedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<Player> getPlayerById(final long playerId) {
    final String _sql = "SELECT * FROM players WHERE id = ?";
    return FlowUtil.createFlow(__db, false, new String[] {"players"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, playerId);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfCash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cash");
        final int _columnIndexOfSacco = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sacco");
        final int _columnIndexOfMmf = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mmf");
        final int _columnIndexOfLand = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "land");
        final int _columnIndexOfReits = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reits");
        final int _columnIndexOfDebt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "debt");
        final int _columnIndexOfCurrentDay = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentDay");
        final int _columnIndexOfGameCompleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gameCompleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final Player _result;
        if (_stmt.step()) {
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final Role _tmpRole;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfRole)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfRole);
          }
          _tmpRole = __converters.toRole(_tmp);
          final double _tmpCash;
          _tmpCash = _stmt.getDouble(_columnIndexOfCash);
          final double _tmpSacco;
          _tmpSacco = _stmt.getDouble(_columnIndexOfSacco);
          final double _tmpMmf;
          _tmpMmf = _stmt.getDouble(_columnIndexOfMmf);
          final double _tmpLand;
          _tmpLand = _stmt.getDouble(_columnIndexOfLand);
          final double _tmpReits;
          _tmpReits = _stmt.getDouble(_columnIndexOfReits);
          final double _tmpDebt;
          _tmpDebt = _stmt.getDouble(_columnIndexOfDebt);
          final int _tmpCurrentDay;
          _tmpCurrentDay = (int) (_stmt.getLong(_columnIndexOfCurrentDay));
          final boolean _tmpGameCompleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfGameCompleted));
          _tmpGameCompleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _result = new Player(_tmpId,_tmpName,_tmpRole,_tmpCash,_tmpSacco,_tmpMmf,_tmpLand,_tmpReits,_tmpDebt,_tmpCurrentDay,_tmpGameCompleted,_tmpCreatedAt);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<Player> getCurrentPlayer() {
    final String _sql = "SELECT * FROM players WHERE gameCompleted = 0 ORDER BY createdAt DESC LIMIT 1";
    return FlowUtil.createFlow(__db, false, new String[] {"players"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfCash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cash");
        final int _columnIndexOfSacco = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sacco");
        final int _columnIndexOfMmf = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mmf");
        final int _columnIndexOfLand = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "land");
        final int _columnIndexOfReits = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reits");
        final int _columnIndexOfDebt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "debt");
        final int _columnIndexOfCurrentDay = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentDay");
        final int _columnIndexOfGameCompleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gameCompleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final Player _result;
        if (_stmt.step()) {
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final Role _tmpRole;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfRole)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfRole);
          }
          _tmpRole = __converters.toRole(_tmp);
          final double _tmpCash;
          _tmpCash = _stmt.getDouble(_columnIndexOfCash);
          final double _tmpSacco;
          _tmpSacco = _stmt.getDouble(_columnIndexOfSacco);
          final double _tmpMmf;
          _tmpMmf = _stmt.getDouble(_columnIndexOfMmf);
          final double _tmpLand;
          _tmpLand = _stmt.getDouble(_columnIndexOfLand);
          final double _tmpReits;
          _tmpReits = _stmt.getDouble(_columnIndexOfReits);
          final double _tmpDebt;
          _tmpDebt = _stmt.getDouble(_columnIndexOfDebt);
          final int _tmpCurrentDay;
          _tmpCurrentDay = (int) (_stmt.getLong(_columnIndexOfCurrentDay));
          final boolean _tmpGameCompleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfGameCompleted));
          _tmpGameCompleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _result = new Player(_tmpId,_tmpName,_tmpRole,_tmpCash,_tmpSacco,_tmpMmf,_tmpLand,_tmpReits,_tmpDebt,_tmpCurrentDay,_tmpGameCompleted,_tmpCreatedAt);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<Player>> getCompletedPlayers() {
    final String _sql = "SELECT * FROM players WHERE gameCompleted = 1 ORDER BY createdAt DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"players"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfCash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cash");
        final int _columnIndexOfSacco = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sacco");
        final int _columnIndexOfMmf = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mmf");
        final int _columnIndexOfLand = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "land");
        final int _columnIndexOfReits = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reits");
        final int _columnIndexOfDebt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "debt");
        final int _columnIndexOfCurrentDay = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentDay");
        final int _columnIndexOfGameCompleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gameCompleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final List<Player> _result = new ArrayList<Player>();
        while (_stmt.step()) {
          final Player _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final Role _tmpRole;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfRole)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfRole);
          }
          _tmpRole = __converters.toRole(_tmp);
          final double _tmpCash;
          _tmpCash = _stmt.getDouble(_columnIndexOfCash);
          final double _tmpSacco;
          _tmpSacco = _stmt.getDouble(_columnIndexOfSacco);
          final double _tmpMmf;
          _tmpMmf = _stmt.getDouble(_columnIndexOfMmf);
          final double _tmpLand;
          _tmpLand = _stmt.getDouble(_columnIndexOfLand);
          final double _tmpReits;
          _tmpReits = _stmt.getDouble(_columnIndexOfReits);
          final double _tmpDebt;
          _tmpDebt = _stmt.getDouble(_columnIndexOfDebt);
          final int _tmpCurrentDay;
          _tmpCurrentDay = (int) (_stmt.getLong(_columnIndexOfCurrentDay));
          final boolean _tmpGameCompleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfGameCompleted));
          _tmpGameCompleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _item = new Player(_tmpId,_tmpName,_tmpRole,_tmpCash,_tmpSacco,_tmpMmf,_tmpLand,_tmpReits,_tmpDebt,_tmpCurrentDay,_tmpGameCompleted,_tmpCreatedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object deletePlayerById(final long playerId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM players WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, playerId);
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAllPlayers(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM players";
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
