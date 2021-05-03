package com.ntatvr.core.repositories.user;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ntatvr.core.MockTest;
import com.ntatvr.core.TestDataProvider;
import com.ntatvr.core.utils.JsonReader;
import com.ntatvr.domain.entities.UserEntity;

public class UserRepositoryTest extends MockTest {

  @InjectMocks
  private UserRepositoryImpl userRepository;

  @Mock
  private JsonReader jsonReader;

  private UserEntity userEntity;

  @Before
  public void setup() throws IOException {
    userEntity = TestDataProvider.buildUserEntity();
    Mockito.when(jsonReader.readFromFile(Mockito.any(), Mockito.any()))
        .thenReturn(List.of(userEntity));
    userRepository.init();
  }

  @Test
  public void getById_shouldReturnEmpty() {
    final Optional<UserEntity> userEntityOpt = userRepository.getById("102");
    MatcherAssert.assertThat(userEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getById_shouldReturnData() {
    final Optional<UserEntity> userEntityOpt =
        userRepository.getById(userEntity.getId());
    MatcherAssert.assertThat(userEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(userEntityOpt.get(), Matchers.is(userEntity));
  }

  @Test
  public void getByUrl_shouldReturnEmpty() {
    final Optional<UserEntity> userEntityOpt = userRepository.getByUrl("INVALID_URL");
    MatcherAssert.assertThat(userEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getByUrl_shouldReturnData() {
    final Optional<UserEntity> userEntityOpt =
        userRepository.getByUrl(userEntity.getUrl());
    MatcherAssert.assertThat(userEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(userEntityOpt.get(), Matchers.is(userEntity));
  }

  @Test
  public void getByExternalId_shouldReturnEmpty() {
    final Optional<UserEntity> userEntityOpt
        = userRepository.getByExternalId("INVALID_EXTERNAL_ID");
    MatcherAssert.assertThat(userEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getByExternalId_shouldReturnData() {
    final Optional<UserEntity> userEntityOpt =
        userRepository.getByExternalId(userEntity.getExternalId());
    MatcherAssert.assertThat(userEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(userEntityOpt.get(), Matchers.is(userEntity));
  }
}