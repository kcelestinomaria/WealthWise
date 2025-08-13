package com.wealthwise.data.repository;

import com.wealthwise.data.database.WealthWiseDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DatabaseRepository_Factory implements Factory<DatabaseRepository> {
  private final Provider<WealthWiseDatabase> databaseProvider;

  public DatabaseRepository_Factory(Provider<WealthWiseDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public DatabaseRepository get() {
    return newInstance(databaseProvider.get());
  }

  public static DatabaseRepository_Factory create(Provider<WealthWiseDatabase> databaseProvider) {
    return new DatabaseRepository_Factory(databaseProvider);
  }

  public static DatabaseRepository newInstance(WealthWiseDatabase database) {
    return new DatabaseRepository(database);
  }
}
