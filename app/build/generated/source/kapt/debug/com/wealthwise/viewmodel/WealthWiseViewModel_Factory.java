package com.wealthwise.viewmodel;

import com.wealthwise.data.repository.DatabaseRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class WealthWiseViewModel_Factory implements Factory<WealthWiseViewModel> {
  private final Provider<DatabaseRepository> databaseRepositoryProvider;

  public WealthWiseViewModel_Factory(Provider<DatabaseRepository> databaseRepositoryProvider) {
    this.databaseRepositoryProvider = databaseRepositoryProvider;
  }

  @Override
  public WealthWiseViewModel get() {
    return newInstance(databaseRepositoryProvider.get());
  }

  public static WealthWiseViewModel_Factory create(
      Provider<DatabaseRepository> databaseRepositoryProvider) {
    return new WealthWiseViewModel_Factory(databaseRepositoryProvider);
  }

  public static WealthWiseViewModel newInstance(DatabaseRepository databaseRepository) {
    return new WealthWiseViewModel(databaseRepository);
  }
}
