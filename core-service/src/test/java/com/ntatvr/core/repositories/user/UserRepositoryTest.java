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

  @Test
  public void getByName_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getByName("INVALID_NAME");
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getByPriority_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getByName(userEntity.getName());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByAlias_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getByAlias("INVALID_ALIAS");
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getByAlias_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getByAlias(userEntity.getAlias());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByActive_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getByActive(false);
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getByActive_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getByActive(userEntity.getActive());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByVerified_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getByVerified(false);
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getByVerified_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getByVerified(userEntity.getVerified());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByShared_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getByShared(false);
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getByShared_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getByShared(userEntity.getShared());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByLocale_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getByLocale("INVALID_LOCALE");
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getByLocale_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getByLocale(userEntity.getLocale());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByTimezone_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getByTimezone("INVALID_TIMEZONE");
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getByTimezone_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getByTimezone(userEntity.getTimezone());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByEmail_shouldReturnEmpty() {
    final Optional<UserEntity> userEntityOpt = userRepository.getByEmail("INVALID_Email");
    MatcherAssert.assertThat(userEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getByEmail_shouldReturnData() {
    final Optional<UserEntity> userEntityOpt = userRepository.getByEmail(userEntity.getEmail());
    MatcherAssert.assertThat(userEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(userEntityOpt.get(), Matchers.is(userEntity));
  }

  @Test
  public void getByPhone_shouldReturnEmpty() {
    final Optional<UserEntity> userEntityOpt = userRepository.getByPhone("INVALID_Phone");
    MatcherAssert.assertThat(userEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getByPhone_shouldReturnData() {
    final Optional<UserEntity> userEntityOpt = userRepository.getByPhone(userEntity.getPhone());
    MatcherAssert.assertThat(userEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(userEntityOpt.get(), Matchers.is(userEntity));
  }

  @Test
  public void getBySignature_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getBySignature("INVALID_Signature");
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getBySignature_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getBySignature(userEntity.getSignature());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getBySuspended_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getBySuspended(!userEntity.getSuspended());
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getBySuspended_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getBySuspended(userEntity.getSuspended());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByOrganizationId_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getByOrganizationId(0L);
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getByOrganizationId_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getByOrganizationId(userEntity.getOrganizationId());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByRole_shouldReturnEmpty() {
    final List<UserEntity> userEntities = userRepository.getByRole("User");
    MatcherAssert.assertThat(userEntities, Matchers.empty());
  }

  @Test
  public void getByRole_shouldReturnData() {
    final List<UserEntity> userEntities = userRepository.getByRole(userEntity.getRole());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }
}