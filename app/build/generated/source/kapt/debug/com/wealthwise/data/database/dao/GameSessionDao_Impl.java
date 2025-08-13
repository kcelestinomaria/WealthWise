package com.wealthwise.data.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
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
import com.wealthwise.data.database.entities.GameSessionEntity;
import com.wealthwise.data.model.Goal;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class GameSessionDao_Impl implements GameSessionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GameSessionEntity> __insertionAdapterOfGameSessionEntity;

  private Converters __converters;

  private final EntityDeletionOrUpdateAdapter<GameSessionEntity> __deletionAdapterOfGameSessionEntity;

  private final EntityDeletionOrUpdateAdapter<GameSessionEntity> __updateAdapterOfGameSessionEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarkSessionAsSynced;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLastPlayed;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSessionById;

  public GameSessionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGameSessionEntity = new EntityInsertionAdapter<GameSessionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `game_sessions` (`session_id`,`user_id`,`current_day`,`balance`,`savings`,`debt`,`monthly_income`,`monthly_expenses`,`career`,`career_id`,`goals`,`is_completed`,`completion_score`,`started_at`,`completed_at`,`last_played_at`,`is_synced`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final GameSessionEntity entity) {
        if (entity.getSessionId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getSessionId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        statement.bindLong(3, entity.getCurrentDay());
        statement.bindLong(4, entity.getBalance());
        statement.bindLong(5, entity.getSavings());
        statement.bindLong(6, entity.getDebt());
        statement.bindLong(7, entity.getMonthlyIncome());
        statement.bindLong(8, entity.getMonthlyExpenses());
        if (entity.getCareer() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getCareer());
        }
        if (entity.getCareerId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCareerId());
        }
        final String _tmp = __converters().fromGoalList(entity.getGoals());
        if (_tmp == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, _tmp);
        }
        final int _tmp_1 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
        statement.bindLong(13, entity.getCompletionScore());
        final Long _tmp_2 = __converters().dateToTimestamp(entity.getStartedAt());
        if (_tmp_2 == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, _tmp_2);
        }
        final Long _tmp_3 = __converters().dateToTimestamp(entity.getCompletedAt());
        if (_tmp_3 == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, _tmp_3);
        }
        final Long _tmp_4 = __converters().dateToTimestamp(entity.getLastPlayedAt());
        if (_tmp_4 == null) {
          statement.bindNull(16);
        } else {
          statement.bindLong(16, _tmp_4);
        }
        final int _tmp_5 = entity.isSynced() ? 1 : 0;
        statement.bindLong(17, _tmp_5);
      }
    };
    this.__deletionAdapterOfGameSessionEntity = new EntityDeletionOrUpdateAdapter<GameSessionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `game_sessions` WHERE `session_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final GameSessionEntity entity) {
        if (entity.getSessionId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getSessionId());
        }
      }
    };
    this.__updateAdapterOfGameSessionEntity = new EntityDeletionOrUpdateAdapter<GameSessionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `game_sessions` SET `session_id` = ?,`user_id` = ?,`current_day` = ?,`balance` = ?,`savings` = ?,`debt` = ?,`monthly_income` = ?,`monthly_expenses` = ?,`career` = ?,`career_id` = ?,`goals` = ?,`is_completed` = ?,`completion_score` = ?,`started_at` = ?,`completed_at` = ?,`last_played_at` = ?,`is_synced` = ? WHERE `session_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final GameSessionEntity entity) {
        if (entity.getSessionId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getSessionId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        statement.bindLong(3, entity.getCurrentDay());
        statement.bindLong(4, entity.getBalance());
        statement.bindLong(5, entity.getSavings());
        statement.bindLong(6, entity.getDebt());
        statement.bindLong(7, entity.getMonthlyIncome());
        statement.bindLong(8, entity.getMonthlyExpenses());
        if (entity.getCareer() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getCareer());
        }
        if (entity.getCareerId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCareerId());
        }
        final String _tmp = __converters().fromGoalList(entity.getGoals());
        if (_tmp == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, _tmp);
        }
        final int _tmp_1 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
        statement.bindLong(13, entity.getCompletionScore());
        final Long _tmp_2 = __converters().dateToTimestamp(entity.getStartedAt());
        if (_tmp_2 == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, _tmp_2);
        }
        final Long _tmp_3 = __converters().dateToTimestamp(entity.getCompletedAt());
        if (_tmp_3 == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, _tmp_3);
        }
        final Long _tmp_4 = __converters().dateToTimestamp(entity.getLastPlayedAt());
        if (_tmp_4 == null) {
          statement.bindNull(16);
        } else {
          statement.bindLong(16, _tmp_4);
        }
        final int _tmp_5 = entity.isSynced() ? 1 : 0;
        statement.bindLong(17, _tmp_5);
        if (entity.getSessionId() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getSessionId());
        }
      }
    };
    this.__preparedStmtOfMarkSessionAsSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE game_sessions SET is_synced = 1 WHERE session_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateLastPlayed = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE game_sessions SET last_played_at = ? WHERE session_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteSessionById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM game_sessions WHERE session_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertSession(final GameSessionEntity session,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfGameSessionEntity.insertAndReturnId(session);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSession(final GameSessionEntity session,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfGameSessionEntity.handle(session);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSession(final GameSessionEntity session,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfGameSessionEntity.handle(session);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markSessionAsSynced(final String sessionId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkSessionAsSynced.acquire();
        int _argIndex = 1;
        if (sessionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, sessionId);
        }
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
          __preparedStmtOfMarkSessionAsSynced.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLastPlayed(final String sessionId, final Date timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLastPlayed.acquire();
        int _argIndex = 1;
        final Long _tmp = __converters().dateToTimestamp(timestamp);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        _argIndex = 2;
        if (sessionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, sessionId);
        }
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
          __preparedStmtOfUpdateLastPlayed.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSessionById(final String sessionId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSessionById.acquire();
        int _argIndex = 1;
        if (sessionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, sessionId);
        }
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
          __preparedStmtOfDeleteSessionById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<GameSessionEntity>> getSessionsByUserFlow(final String userId) {
    final String _sql = "SELECT * FROM game_sessions WHERE user_id = ? ORDER BY last_played_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"game_sessions"}, new Callable<List<GameSessionEntity>>() {
      @Override
      @NonNull
      public List<GameSessionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "session_id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "current_day");
          final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
          final int _cursorIndexOfSavings = CursorUtil.getColumnIndexOrThrow(_cursor, "savings");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfMonthlyIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_income");
          final int _cursorIndexOfMonthlyExpenses = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_expenses");
          final int _cursorIndexOfCareer = CursorUtil.getColumnIndexOrThrow(_cursor, "career");
          final int _cursorIndexOfCareerId = CursorUtil.getColumnIndexOrThrow(_cursor, "career_id");
          final int _cursorIndexOfGoals = CursorUtil.getColumnIndexOrThrow(_cursor, "goals");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "is_completed");
          final int _cursorIndexOfCompletionScore = CursorUtil.getColumnIndexOrThrow(_cursor, "completion_score");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "started_at");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completed_at");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "last_played_at");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "is_synced");
          final List<GameSessionEntity> _result = new ArrayList<GameSessionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GameSessionEntity _item;
            final String _tmpSessionId;
            if (_cursor.isNull(_cursorIndexOfSessionId)) {
              _tmpSessionId = null;
            } else {
              _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            final int _tmpBalance;
            _tmpBalance = _cursor.getInt(_cursorIndexOfBalance);
            final int _tmpSavings;
            _tmpSavings = _cursor.getInt(_cursorIndexOfSavings);
            final int _tmpDebt;
            _tmpDebt = _cursor.getInt(_cursorIndexOfDebt);
            final int _tmpMonthlyIncome;
            _tmpMonthlyIncome = _cursor.getInt(_cursorIndexOfMonthlyIncome);
            final int _tmpMonthlyExpenses;
            _tmpMonthlyExpenses = _cursor.getInt(_cursorIndexOfMonthlyExpenses);
            final String _tmpCareer;
            if (_cursor.isNull(_cursorIndexOfCareer)) {
              _tmpCareer = null;
            } else {
              _tmpCareer = _cursor.getString(_cursorIndexOfCareer);
            }
            final String _tmpCareerId;
            if (_cursor.isNull(_cursorIndexOfCareerId)) {
              _tmpCareerId = null;
            } else {
              _tmpCareerId = _cursor.getString(_cursorIndexOfCareerId);
            }
            final List<Goal> _tmpGoals;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoals)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoals);
            }
            _tmpGoals = __converters().toGoalList(_tmp);
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final int _tmpCompletionScore;
            _tmpCompletionScore = _cursor.getInt(_cursorIndexOfCompletionScore);
            final Date _tmpStartedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            _tmpStartedAt = __converters().fromTimestamp(_tmp_2);
            final Date _tmpCompletedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _tmpCompletedAt = __converters().fromTimestamp(_tmp_3);
            final Date _tmpLastPlayedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastPlayedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastPlayedAt);
            }
            _tmpLastPlayedAt = __converters().fromTimestamp(_tmp_4);
            final boolean _tmpIsSynced;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp_5 != 0;
            _item = new GameSessionEntity(_tmpSessionId,_tmpUserId,_tmpCurrentDay,_tmpBalance,_tmpSavings,_tmpDebt,_tmpMonthlyIncome,_tmpMonthlyExpenses,_tmpCareer,_tmpCareerId,_tmpGoals,_tmpIsCompleted,_tmpCompletionScore,_tmpStartedAt,_tmpCompletedAt,_tmpLastPlayedAt,_tmpIsSynced);
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
  public Object getCurrentSession(final String userId,
      final Continuation<? super GameSessionEntity> $completion) {
    final String _sql = "SELECT * FROM game_sessions WHERE user_id = ? AND is_completed = 0 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<GameSessionEntity>() {
      @Override
      @Nullable
      public GameSessionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "session_id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "current_day");
          final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
          final int _cursorIndexOfSavings = CursorUtil.getColumnIndexOrThrow(_cursor, "savings");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfMonthlyIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_income");
          final int _cursorIndexOfMonthlyExpenses = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_expenses");
          final int _cursorIndexOfCareer = CursorUtil.getColumnIndexOrThrow(_cursor, "career");
          final int _cursorIndexOfCareerId = CursorUtil.getColumnIndexOrThrow(_cursor, "career_id");
          final int _cursorIndexOfGoals = CursorUtil.getColumnIndexOrThrow(_cursor, "goals");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "is_completed");
          final int _cursorIndexOfCompletionScore = CursorUtil.getColumnIndexOrThrow(_cursor, "completion_score");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "started_at");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completed_at");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "last_played_at");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "is_synced");
          final GameSessionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpSessionId;
            if (_cursor.isNull(_cursorIndexOfSessionId)) {
              _tmpSessionId = null;
            } else {
              _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            final int _tmpBalance;
            _tmpBalance = _cursor.getInt(_cursorIndexOfBalance);
            final int _tmpSavings;
            _tmpSavings = _cursor.getInt(_cursorIndexOfSavings);
            final int _tmpDebt;
            _tmpDebt = _cursor.getInt(_cursorIndexOfDebt);
            final int _tmpMonthlyIncome;
            _tmpMonthlyIncome = _cursor.getInt(_cursorIndexOfMonthlyIncome);
            final int _tmpMonthlyExpenses;
            _tmpMonthlyExpenses = _cursor.getInt(_cursorIndexOfMonthlyExpenses);
            final String _tmpCareer;
            if (_cursor.isNull(_cursorIndexOfCareer)) {
              _tmpCareer = null;
            } else {
              _tmpCareer = _cursor.getString(_cursorIndexOfCareer);
            }
            final String _tmpCareerId;
            if (_cursor.isNull(_cursorIndexOfCareerId)) {
              _tmpCareerId = null;
            } else {
              _tmpCareerId = _cursor.getString(_cursorIndexOfCareerId);
            }
            final List<Goal> _tmpGoals;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoals)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoals);
            }
            _tmpGoals = __converters().toGoalList(_tmp);
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final int _tmpCompletionScore;
            _tmpCompletionScore = _cursor.getInt(_cursorIndexOfCompletionScore);
            final Date _tmpStartedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            _tmpStartedAt = __converters().fromTimestamp(_tmp_2);
            final Date _tmpCompletedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _tmpCompletedAt = __converters().fromTimestamp(_tmp_3);
            final Date _tmpLastPlayedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastPlayedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastPlayedAt);
            }
            _tmpLastPlayedAt = __converters().fromTimestamp(_tmp_4);
            final boolean _tmpIsSynced;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp_5 != 0;
            _result = new GameSessionEntity(_tmpSessionId,_tmpUserId,_tmpCurrentDay,_tmpBalance,_tmpSavings,_tmpDebt,_tmpMonthlyIncome,_tmpMonthlyExpenses,_tmpCareer,_tmpCareerId,_tmpGoals,_tmpIsCompleted,_tmpCompletionScore,_tmpStartedAt,_tmpCompletedAt,_tmpLastPlayedAt,_tmpIsSynced);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<GameSessionEntity> getCurrentSessionFlow(final String userId) {
    final String _sql = "SELECT * FROM game_sessions WHERE user_id = ? AND is_completed = 0 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"game_sessions"}, new Callable<GameSessionEntity>() {
      @Override
      @Nullable
      public GameSessionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "session_id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "current_day");
          final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
          final int _cursorIndexOfSavings = CursorUtil.getColumnIndexOrThrow(_cursor, "savings");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfMonthlyIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_income");
          final int _cursorIndexOfMonthlyExpenses = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_expenses");
          final int _cursorIndexOfCareer = CursorUtil.getColumnIndexOrThrow(_cursor, "career");
          final int _cursorIndexOfCareerId = CursorUtil.getColumnIndexOrThrow(_cursor, "career_id");
          final int _cursorIndexOfGoals = CursorUtil.getColumnIndexOrThrow(_cursor, "goals");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "is_completed");
          final int _cursorIndexOfCompletionScore = CursorUtil.getColumnIndexOrThrow(_cursor, "completion_score");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "started_at");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completed_at");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "last_played_at");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "is_synced");
          final GameSessionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpSessionId;
            if (_cursor.isNull(_cursorIndexOfSessionId)) {
              _tmpSessionId = null;
            } else {
              _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            final int _tmpBalance;
            _tmpBalance = _cursor.getInt(_cursorIndexOfBalance);
            final int _tmpSavings;
            _tmpSavings = _cursor.getInt(_cursorIndexOfSavings);
            final int _tmpDebt;
            _tmpDebt = _cursor.getInt(_cursorIndexOfDebt);
            final int _tmpMonthlyIncome;
            _tmpMonthlyIncome = _cursor.getInt(_cursorIndexOfMonthlyIncome);
            final int _tmpMonthlyExpenses;
            _tmpMonthlyExpenses = _cursor.getInt(_cursorIndexOfMonthlyExpenses);
            final String _tmpCareer;
            if (_cursor.isNull(_cursorIndexOfCareer)) {
              _tmpCareer = null;
            } else {
              _tmpCareer = _cursor.getString(_cursorIndexOfCareer);
            }
            final String _tmpCareerId;
            if (_cursor.isNull(_cursorIndexOfCareerId)) {
              _tmpCareerId = null;
            } else {
              _tmpCareerId = _cursor.getString(_cursorIndexOfCareerId);
            }
            final List<Goal> _tmpGoals;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoals)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoals);
            }
            _tmpGoals = __converters().toGoalList(_tmp);
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final int _tmpCompletionScore;
            _tmpCompletionScore = _cursor.getInt(_cursorIndexOfCompletionScore);
            final Date _tmpStartedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            _tmpStartedAt = __converters().fromTimestamp(_tmp_2);
            final Date _tmpCompletedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _tmpCompletedAt = __converters().fromTimestamp(_tmp_3);
            final Date _tmpLastPlayedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastPlayedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastPlayedAt);
            }
            _tmpLastPlayedAt = __converters().fromTimestamp(_tmp_4);
            final boolean _tmpIsSynced;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp_5 != 0;
            _result = new GameSessionEntity(_tmpSessionId,_tmpUserId,_tmpCurrentDay,_tmpBalance,_tmpSavings,_tmpDebt,_tmpMonthlyIncome,_tmpMonthlyExpenses,_tmpCareer,_tmpCareerId,_tmpGoals,_tmpIsCompleted,_tmpCompletionScore,_tmpStartedAt,_tmpCompletedAt,_tmpLastPlayedAt,_tmpIsSynced);
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
  public Object getSessionById(final String sessionId,
      final Continuation<? super GameSessionEntity> $completion) {
    final String _sql = "SELECT * FROM game_sessions WHERE session_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (sessionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sessionId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<GameSessionEntity>() {
      @Override
      @Nullable
      public GameSessionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "session_id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "current_day");
          final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
          final int _cursorIndexOfSavings = CursorUtil.getColumnIndexOrThrow(_cursor, "savings");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfMonthlyIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_income");
          final int _cursorIndexOfMonthlyExpenses = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_expenses");
          final int _cursorIndexOfCareer = CursorUtil.getColumnIndexOrThrow(_cursor, "career");
          final int _cursorIndexOfCareerId = CursorUtil.getColumnIndexOrThrow(_cursor, "career_id");
          final int _cursorIndexOfGoals = CursorUtil.getColumnIndexOrThrow(_cursor, "goals");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "is_completed");
          final int _cursorIndexOfCompletionScore = CursorUtil.getColumnIndexOrThrow(_cursor, "completion_score");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "started_at");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completed_at");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "last_played_at");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "is_synced");
          final GameSessionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpSessionId;
            if (_cursor.isNull(_cursorIndexOfSessionId)) {
              _tmpSessionId = null;
            } else {
              _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            final int _tmpBalance;
            _tmpBalance = _cursor.getInt(_cursorIndexOfBalance);
            final int _tmpSavings;
            _tmpSavings = _cursor.getInt(_cursorIndexOfSavings);
            final int _tmpDebt;
            _tmpDebt = _cursor.getInt(_cursorIndexOfDebt);
            final int _tmpMonthlyIncome;
            _tmpMonthlyIncome = _cursor.getInt(_cursorIndexOfMonthlyIncome);
            final int _tmpMonthlyExpenses;
            _tmpMonthlyExpenses = _cursor.getInt(_cursorIndexOfMonthlyExpenses);
            final String _tmpCareer;
            if (_cursor.isNull(_cursorIndexOfCareer)) {
              _tmpCareer = null;
            } else {
              _tmpCareer = _cursor.getString(_cursorIndexOfCareer);
            }
            final String _tmpCareerId;
            if (_cursor.isNull(_cursorIndexOfCareerId)) {
              _tmpCareerId = null;
            } else {
              _tmpCareerId = _cursor.getString(_cursorIndexOfCareerId);
            }
            final List<Goal> _tmpGoals;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoals)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoals);
            }
            _tmpGoals = __converters().toGoalList(_tmp);
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final int _tmpCompletionScore;
            _tmpCompletionScore = _cursor.getInt(_cursorIndexOfCompletionScore);
            final Date _tmpStartedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            _tmpStartedAt = __converters().fromTimestamp(_tmp_2);
            final Date _tmpCompletedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _tmpCompletedAt = __converters().fromTimestamp(_tmp_3);
            final Date _tmpLastPlayedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastPlayedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastPlayedAt);
            }
            _tmpLastPlayedAt = __converters().fromTimestamp(_tmp_4);
            final boolean _tmpIsSynced;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp_5 != 0;
            _result = new GameSessionEntity(_tmpSessionId,_tmpUserId,_tmpCurrentDay,_tmpBalance,_tmpSavings,_tmpDebt,_tmpMonthlyIncome,_tmpMonthlyExpenses,_tmpCareer,_tmpCareerId,_tmpGoals,_tmpIsCompleted,_tmpCompletionScore,_tmpStartedAt,_tmpCompletedAt,_tmpLastPlayedAt,_tmpIsSynced);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTopCompletedSessions(final int limit,
      final Continuation<? super List<GameSessionEntity>> $completion) {
    final String _sql = "SELECT * FROM game_sessions WHERE is_completed = 1 ORDER BY completion_score DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<GameSessionEntity>>() {
      @Override
      @NonNull
      public List<GameSessionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "session_id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "current_day");
          final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
          final int _cursorIndexOfSavings = CursorUtil.getColumnIndexOrThrow(_cursor, "savings");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfMonthlyIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_income");
          final int _cursorIndexOfMonthlyExpenses = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_expenses");
          final int _cursorIndexOfCareer = CursorUtil.getColumnIndexOrThrow(_cursor, "career");
          final int _cursorIndexOfCareerId = CursorUtil.getColumnIndexOrThrow(_cursor, "career_id");
          final int _cursorIndexOfGoals = CursorUtil.getColumnIndexOrThrow(_cursor, "goals");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "is_completed");
          final int _cursorIndexOfCompletionScore = CursorUtil.getColumnIndexOrThrow(_cursor, "completion_score");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "started_at");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completed_at");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "last_played_at");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "is_synced");
          final List<GameSessionEntity> _result = new ArrayList<GameSessionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GameSessionEntity _item;
            final String _tmpSessionId;
            if (_cursor.isNull(_cursorIndexOfSessionId)) {
              _tmpSessionId = null;
            } else {
              _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            final int _tmpBalance;
            _tmpBalance = _cursor.getInt(_cursorIndexOfBalance);
            final int _tmpSavings;
            _tmpSavings = _cursor.getInt(_cursorIndexOfSavings);
            final int _tmpDebt;
            _tmpDebt = _cursor.getInt(_cursorIndexOfDebt);
            final int _tmpMonthlyIncome;
            _tmpMonthlyIncome = _cursor.getInt(_cursorIndexOfMonthlyIncome);
            final int _tmpMonthlyExpenses;
            _tmpMonthlyExpenses = _cursor.getInt(_cursorIndexOfMonthlyExpenses);
            final String _tmpCareer;
            if (_cursor.isNull(_cursorIndexOfCareer)) {
              _tmpCareer = null;
            } else {
              _tmpCareer = _cursor.getString(_cursorIndexOfCareer);
            }
            final String _tmpCareerId;
            if (_cursor.isNull(_cursorIndexOfCareerId)) {
              _tmpCareerId = null;
            } else {
              _tmpCareerId = _cursor.getString(_cursorIndexOfCareerId);
            }
            final List<Goal> _tmpGoals;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoals)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoals);
            }
            _tmpGoals = __converters().toGoalList(_tmp);
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final int _tmpCompletionScore;
            _tmpCompletionScore = _cursor.getInt(_cursorIndexOfCompletionScore);
            final Date _tmpStartedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            _tmpStartedAt = __converters().fromTimestamp(_tmp_2);
            final Date _tmpCompletedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _tmpCompletedAt = __converters().fromTimestamp(_tmp_3);
            final Date _tmpLastPlayedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastPlayedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastPlayedAt);
            }
            _tmpLastPlayedAt = __converters().fromTimestamp(_tmp_4);
            final boolean _tmpIsSynced;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp_5 != 0;
            _item = new GameSessionEntity(_tmpSessionId,_tmpUserId,_tmpCurrentDay,_tmpBalance,_tmpSavings,_tmpDebt,_tmpMonthlyIncome,_tmpMonthlyExpenses,_tmpCareer,_tmpCareerId,_tmpGoals,_tmpIsCompleted,_tmpCompletionScore,_tmpStartedAt,_tmpCompletedAt,_tmpLastPlayedAt,_tmpIsSynced);
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

  @Override
  public Object getUnsyncedSessions(
      final Continuation<? super List<GameSessionEntity>> $completion) {
    final String _sql = "SELECT * FROM game_sessions WHERE is_synced = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<GameSessionEntity>>() {
      @Override
      @NonNull
      public List<GameSessionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "session_id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "current_day");
          final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
          final int _cursorIndexOfSavings = CursorUtil.getColumnIndexOrThrow(_cursor, "savings");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfMonthlyIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_income");
          final int _cursorIndexOfMonthlyExpenses = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_expenses");
          final int _cursorIndexOfCareer = CursorUtil.getColumnIndexOrThrow(_cursor, "career");
          final int _cursorIndexOfCareerId = CursorUtil.getColumnIndexOrThrow(_cursor, "career_id");
          final int _cursorIndexOfGoals = CursorUtil.getColumnIndexOrThrow(_cursor, "goals");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "is_completed");
          final int _cursorIndexOfCompletionScore = CursorUtil.getColumnIndexOrThrow(_cursor, "completion_score");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "started_at");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completed_at");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "last_played_at");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "is_synced");
          final List<GameSessionEntity> _result = new ArrayList<GameSessionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GameSessionEntity _item;
            final String _tmpSessionId;
            if (_cursor.isNull(_cursorIndexOfSessionId)) {
              _tmpSessionId = null;
            } else {
              _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            final int _tmpBalance;
            _tmpBalance = _cursor.getInt(_cursorIndexOfBalance);
            final int _tmpSavings;
            _tmpSavings = _cursor.getInt(_cursorIndexOfSavings);
            final int _tmpDebt;
            _tmpDebt = _cursor.getInt(_cursorIndexOfDebt);
            final int _tmpMonthlyIncome;
            _tmpMonthlyIncome = _cursor.getInt(_cursorIndexOfMonthlyIncome);
            final int _tmpMonthlyExpenses;
            _tmpMonthlyExpenses = _cursor.getInt(_cursorIndexOfMonthlyExpenses);
            final String _tmpCareer;
            if (_cursor.isNull(_cursorIndexOfCareer)) {
              _tmpCareer = null;
            } else {
              _tmpCareer = _cursor.getString(_cursorIndexOfCareer);
            }
            final String _tmpCareerId;
            if (_cursor.isNull(_cursorIndexOfCareerId)) {
              _tmpCareerId = null;
            } else {
              _tmpCareerId = _cursor.getString(_cursorIndexOfCareerId);
            }
            final List<Goal> _tmpGoals;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoals)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoals);
            }
            _tmpGoals = __converters().toGoalList(_tmp);
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final int _tmpCompletionScore;
            _tmpCompletionScore = _cursor.getInt(_cursorIndexOfCompletionScore);
            final Date _tmpStartedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            _tmpStartedAt = __converters().fromTimestamp(_tmp_2);
            final Date _tmpCompletedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _tmpCompletedAt = __converters().fromTimestamp(_tmp_3);
            final Date _tmpLastPlayedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastPlayedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastPlayedAt);
            }
            _tmpLastPlayedAt = __converters().fromTimestamp(_tmp_4);
            final boolean _tmpIsSynced;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp_5 != 0;
            _item = new GameSessionEntity(_tmpSessionId,_tmpUserId,_tmpCurrentDay,_tmpBalance,_tmpSavings,_tmpDebt,_tmpMonthlyIncome,_tmpMonthlyExpenses,_tmpCareer,_tmpCareerId,_tmpGoals,_tmpIsCompleted,_tmpCompletionScore,_tmpStartedAt,_tmpCompletedAt,_tmpLastPlayedAt,_tmpIsSynced);
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
    return Arrays.asList(Converters.class);
  }

  private synchronized Converters __converters() {
    if (__converters == null) {
      __converters = __db.getTypeConverter(Converters.class);
    }
    return __converters;
  }
}
