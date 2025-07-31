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
import com.wealthwise.data.model.Transaction;
import com.wealthwise.data.model.TransactionType;
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
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Transaction> __insertAdapterOfTransaction;

  private final Converters __converters = new Converters();

  private final EntityDeleteOrUpdateAdapter<Transaction> __deleteAdapterOfTransaction;

  private final EntityDeleteOrUpdateAdapter<Transaction> __updateAdapterOfTransaction;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfTransaction = new EntityInsertAdapter<Transaction>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transactions` (`id`,`playerId`,`day`,`type`,`amount`,`description`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPlayerId());
        statement.bindLong(3, entity.getDay());
        final String _tmp = __converters.fromTransactionType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, _tmp);
        }
        statement.bindDouble(5, entity.getAmount());
        if (entity.getDescription() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getDescription());
        }
        statement.bindLong(7, entity.getTimestamp());
      }
    };
    this.__deleteAdapterOfTransaction = new EntityDeleteOrUpdateAdapter<Transaction>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTransaction = new EntityDeleteOrUpdateAdapter<Transaction>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `id` = ?,`playerId` = ?,`day` = ?,`type` = ?,`amount` = ?,`description` = ?,`timestamp` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPlayerId());
        statement.bindLong(3, entity.getDay());
        final String _tmp = __converters.fromTransactionType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, _tmp);
        }
        statement.bindDouble(5, entity.getAmount());
        if (entity.getDescription() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getDescription());
        }
        statement.bindLong(7, entity.getTimestamp());
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insertTransaction(final Transaction transaction,
      final Continuation<? super Long> $completion) {
    if (transaction == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfTransaction.insertAndReturnId(_connection, transaction);
    }, $completion);
  }

  @Override
  public Object insertTransactions(final List<Transaction> transactions,
      final Continuation<? super Unit> $completion) {
    if (transactions == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfTransaction.insert(_connection, transactions);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object deleteTransaction(final Transaction transaction,
      final Continuation<? super Unit> $completion) {
    if (transaction == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfTransaction.handle(_connection, transaction);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateTransaction(final Transaction transaction,
      final Continuation<? super Unit> $completion) {
    if (transaction == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfTransaction.handle(_connection, transaction);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<Transaction>> getTransactionsByPlayer(final long playerId) {
    final String _sql = "SELECT * FROM transactions WHERE playerId = ? ORDER BY day DESC, timestamp DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"transactions"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, playerId);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfPlayerId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "playerId");
        final int _columnIndexOfDay = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "day");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfAmount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "amount");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final List<Transaction> _result = new ArrayList<Transaction>();
        while (_stmt.step()) {
          final Transaction _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final long _tmpPlayerId;
          _tmpPlayerId = _stmt.getLong(_columnIndexOfPlayerId);
          final int _tmpDay;
          _tmpDay = (int) (_stmt.getLong(_columnIndexOfDay));
          final TransactionType _tmpType;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfType);
          }
          _tmpType = __converters.toTransactionType(_tmp);
          final double _tmpAmount;
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount);
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          _item = new Transaction(_tmpId,_tmpPlayerId,_tmpDay,_tmpType,_tmpAmount,_tmpDescription,_tmpTimestamp);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<Transaction>> getTransactionsByPlayerAndDay(final long playerId, final int day) {
    final String _sql = "SELECT * FROM transactions WHERE playerId = ? AND day = ? ORDER BY timestamp DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"transactions"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, playerId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, day);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfPlayerId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "playerId");
        final int _columnIndexOfDay = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "day");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfAmount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "amount");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final List<Transaction> _result = new ArrayList<Transaction>();
        while (_stmt.step()) {
          final Transaction _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final long _tmpPlayerId;
          _tmpPlayerId = _stmt.getLong(_columnIndexOfPlayerId);
          final int _tmpDay;
          _tmpDay = (int) (_stmt.getLong(_columnIndexOfDay));
          final TransactionType _tmpType;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfType);
          }
          _tmpType = __converters.toTransactionType(_tmp);
          final double _tmpAmount;
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount);
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          _item = new Transaction(_tmpId,_tmpPlayerId,_tmpDay,_tmpType,_tmpAmount,_tmpDescription,_tmpTimestamp);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<Transaction>> getTransactionsByPlayerAndType(final long playerId,
      final TransactionType type) {
    final String _sql = "SELECT * FROM transactions WHERE playerId = ? AND type = ? ORDER BY timestamp DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"transactions"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, playerId);
        _argIndex = 2;
        final String _tmp = __converters.fromTransactionType(type);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, _tmp);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfPlayerId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "playerId");
        final int _columnIndexOfDay = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "day");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfAmount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "amount");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final List<Transaction> _result = new ArrayList<Transaction>();
        while (_stmt.step()) {
          final Transaction _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final long _tmpPlayerId;
          _tmpPlayerId = _stmt.getLong(_columnIndexOfPlayerId);
          final int _tmpDay;
          _tmpDay = (int) (_stmt.getLong(_columnIndexOfDay));
          final TransactionType _tmpType;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfType);
          }
          _tmpType = __converters.toTransactionType(_tmp_1);
          final double _tmpAmount;
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount);
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          _item = new Transaction(_tmpId,_tmpPlayerId,_tmpDay,_tmpType,_tmpAmount,_tmpDescription,_tmpTimestamp);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object deleteTransactionsByPlayer(final long playerId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM transactions WHERE playerId = ?";
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
  public Object deleteAllTransactions(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM transactions";
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
