package com.ntatvr.core.services.user;

import static com.ntatvr.domain.entities.BaseEntity.CREATED_AT_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.EXTERNAL_ID_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.ID_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.TAGS_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.URL_FIELD;
import static com.ntatvr.domain.entities.UserEntity.ACTIVE_FIELD;
import static com.ntatvr.domain.entities.UserEntity.ALIAS_FIELD;
import static com.ntatvr.domain.entities.UserEntity.EMAIL_FIELD;
import static com.ntatvr.domain.entities.UserEntity.LAST_LOGIN_AT_FIELD;
import static com.ntatvr.domain.entities.UserEntity.LOCALE_FIELD;
import static com.ntatvr.domain.entities.UserEntity.NAME_FIELD;
import static com.ntatvr.domain.entities.UserEntity.ORGANIZATION_ID_FIELD;
import static com.ntatvr.domain.entities.UserEntity.PHONE_FIELD;
import static com.ntatvr.domain.entities.UserEntity.ROLE_FIELD;
import static com.ntatvr.domain.entities.UserEntity.SHARED_FIELD;
import static com.ntatvr.domain.entities.UserEntity.SIGNATURE_FIELD;
import static com.ntatvr.domain.entities.UserEntity.SUSPENDED_FIELD;
import static com.ntatvr.domain.entities.UserEntity.TIMEZONE_FIELD;
import static com.ntatvr.domain.entities.UserEntity.VERIFIED_FIELD;

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
import com.ntatvr.core.exceptions.BooleanFormatException;
import com.ntatvr.core.repositories.user.UserRepository;
import com.ntatvr.domain.entities.UserEntity;

public class UserServiceTest extends MockTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  private UserEntity userEntity;

  @Before
  public void setup() {
    userEntity = TestDataProvider.buildUserEntity();
  }

  @Test
  public void searchById_shouldReturnEmpty() {
    Mockito.when(userRepository.getById(Mockito.any())).thenReturn(Optional.empty());
    final Optional<UserEntity> userEntityOpt =
        userService.searchById(userEntity.getId());
    MatcherAssert.assertThat(userEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void searchById_shouldReturnData() {
    Mockito.when(userRepository.getById(Mockito.any())).thenReturn(Optional.of(userEntity));
    final Optional<UserEntity> userEntityOpt =
        userService.searchById(userEntity.getId());
    MatcherAssert.assertThat(userEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(userEntityOpt.get(), Matchers.is(userEntity));
  }

  @Test
  public void getSearchableFields_shouldReturnData() {
    final List<String> searchableFields = userService.getSearchableFields();
    MatcherAssert.assertThat(searchableFields, Matchers.hasSize(19));
    MatcherAssert.assertThat(searchableFields, Matchers.containsInAnyOrder(ID_FIELD, CREATED_AT_FIELD,
        EXTERNAL_ID_FIELD, TAGS_FIELD, URL_FIELD, ACTIVE_FIELD, ALIAS_FIELD, EMAIL_FIELD, LAST_LOGIN_AT_FIELD,
        LOCALE_FIELD, NAME_FIELD, PHONE_FIELD, ORGANIZATION_ID_FIELD, ROLE_FIELD, SHARED_FIELD, SIGNATURE_FIELD,
        SUSPENDED_FIELD, VERIFIED_FIELD, TIMEZONE_FIELD));
  }


  @Test(expected = UnsupportedOperationException.class)
  public void getByUnsupportedField_shouldThrowException() {
    userService.searchByField("unsupported-field", "123");
  }


  @Test
  public void getById_shouldReturnEmpty() {
    Mockito.when(userRepository.getById(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(userService.searchByField(ID_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getById_shouldReturnData() {
    Mockito.when(userRepository.getById(Mockito.any())).thenReturn(Optional.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(ID_FIELD,
        userEntity.getId());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByUrl_shouldReturnEmpty() {
    Mockito.when(userRepository.getByUrl(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(userService.searchByField(URL_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByUrl_shouldReturnData() {
    Mockito.when(userRepository.getByUrl(Mockito.any())).thenReturn(Optional.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(URL_FIELD,
        userEntity.getUrl());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByExternalId_shouldReturnEmpty() {
    Mockito.when(userRepository.getByExternalId(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(userService.searchByField(EXTERNAL_ID_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByExternalId_shouldReturnData() {
    Mockito.when(userRepository.getByExternalId(Mockito.any())).thenReturn(Optional.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(EXTERNAL_ID_FIELD,
        userEntity.getExternalId());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByName_shouldReturnEmpty() {
    Mockito.when(userRepository.getByName(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(NAME_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByName_shouldReturnData() {
    Mockito.when(userRepository.getByName(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(NAME_FIELD,
        userEntity.getName());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByAlias_shouldReturnEmpty() {
    Mockito.when(userRepository.getByAlias(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(ALIAS_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByAlias_shouldReturnData() {
    Mockito.when(userRepository.getByAlias(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(ALIAS_FIELD,
        userEntity.getAlias());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test(expected = BooleanFormatException.class)
  public void getByActive_shouldThrowException() {
    userService.searchByField(ACTIVE_FIELD, "INVALID");
  }

  @Test
  public void getByActive_shouldReturnEmpty() {
    Mockito.when(userRepository.getByActive(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(ACTIVE_FIELD, "False"), Matchers.empty());
  }

  @Test
  public void getByActive_shouldReturnData() {
    Mockito.when(userRepository.getByActive(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(ACTIVE_FIELD,
        userEntity.getActive().toString());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test(expected = BooleanFormatException.class)
  public void getByVerified_shouldThrowException() {
    userService.searchByField(VERIFIED_FIELD, "INVALID");
  }

  @Test
  public void getByVerified_shouldReturnEmpty() {
    Mockito.when(userRepository.getByVerified(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(VERIFIED_FIELD, "True"), Matchers.empty());
  }

  @Test
  public void getByVerified_shouldReturnData() {
    Mockito.when(userRepository.getByVerified(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(VERIFIED_FIELD,
        userEntity.getVerified().toString());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test(expected = BooleanFormatException.class)
  public void getByShared_shouldThrowException() {
    userService.searchByField(SHARED_FIELD, "INVALID");
  }

  @Test
  public void getByShared_shouldReturnEmpty() {
    Mockito.when(userRepository.getByShared(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(SHARED_FIELD, "True"), Matchers.empty());
  }

  @Test
  public void getByShared_shouldReturnData() {
    Mockito.when(userRepository.getByShared(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(SHARED_FIELD,
        userEntity.getShared().toString());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }


  @Test
  public void getByLocale_shouldReturnEmpty() {
    Mockito.when(userRepository.getByLocale(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(LOCALE_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByLocale_shouldReturnData() {
    Mockito.when(userRepository.getByLocale(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(LOCALE_FIELD,
        userEntity.getLocale());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByTimezone_shouldReturnEmpty() {
    Mockito.when(userRepository.getByTimezone(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(TIMEZONE_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByTimezone_shouldReturnData() {
    Mockito.when(userRepository.getByTimezone(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(TIMEZONE_FIELD,
        userEntity.getTimezone());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByEmail_shouldReturnEmpty() {
    Mockito.when(userRepository.getByEmail(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(userService.searchByField(EMAIL_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByEmail_shouldReturnData() {
    Mockito.when(userRepository.getByEmail(Mockito.any())).thenReturn(Optional.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(EMAIL_FIELD,
        userEntity.getEmail());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByPhone_shouldReturnEmpty() {
    Mockito.when(userRepository.getByPhone(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(userService.searchByField(PHONE_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByPhone_shouldReturnData() {
    Mockito.when(userRepository.getByPhone(Mockito.any())).thenReturn(Optional.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(PHONE_FIELD,
        userEntity.getPhone());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getBySignature_shouldReturnEmpty() {
    Mockito.when(userRepository.getBySignature(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(SIGNATURE_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getBySignature_shouldReturnData() {
    Mockito.when(userRepository.getBySignature(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(SIGNATURE_FIELD,
        userEntity.getSignature());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test(expected = BooleanFormatException.class)
  public void getBySuspended_shouldThrowException() {
    userService.searchByField(SUSPENDED_FIELD, "INVALID");
  }

  @Test
  public void getBySuspended_shouldReturnEmpty() {
    Mockito.when(userRepository.getBySuspended(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(SUSPENDED_FIELD, "True"), Matchers.empty());
  }

  @Test
  public void getBySuspended_shouldReturnData() {
    Mockito.when(userRepository.getBySuspended(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(SUSPENDED_FIELD,
        userEntity.getSuspended().toString());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test(expected = NumberFormatException.class)
  public void getByOrganizationId_shouldThrowException() {
    userService.searchByField(ORGANIZATION_ID_FIELD, "INVALID");
  }

  @Test
  public void getByOrganizationId_shouldReturnEmpty() {
    Mockito.when(userRepository.getByOrganizationId(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(ORGANIZATION_ID_FIELD, "1"), Matchers.empty());
  }

  @Test
  public void getByOrganizationId_shouldReturnData() {
    Mockito.when(userRepository.getByOrganizationId(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(ORGANIZATION_ID_FIELD,
        userEntity.getOrganizationId().toString());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }

  @Test
  public void getByRole_shouldReturnEmpty() {
    Mockito.when(userRepository.getByRole(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(userService.searchByField(ROLE_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByRole_shouldReturnData() {
    Mockito.when(userRepository.getByRole(Mockito.any())).thenReturn(List.of(userEntity));
    final List<UserEntity> userEntities = userService.searchByField(ROLE_FIELD,
        userEntity.getRole());
    MatcherAssert.assertThat(userEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(userEntities.get(0), Matchers.is(userEntity));
  }
}