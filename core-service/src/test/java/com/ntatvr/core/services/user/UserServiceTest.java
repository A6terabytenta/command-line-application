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
    MatcherAssert.assertThat(searchableFields, Matchers.hasSize(18));
    MatcherAssert.assertThat(searchableFields, Matchers.containsInAnyOrder(ID_FIELD, CREATED_AT_FIELD, EXTERNAL_ID_FIELD,
        TAGS_FIELD, URL_FIELD, ACTIVE_FIELD, ALIAS_FIELD, EMAIL_FIELD,  LAST_LOGIN_AT_FIELD, LOCALE_FIELD,
        NAME_FIELD, PHONE_FIELD, ORGANIZATION_ID_FIELD, ROLE_FIELD, SHARED_FIELD, SIGNATURE_FIELD, SUSPENDED_FIELD,
         VERIFIED_FIELD));
  }
}