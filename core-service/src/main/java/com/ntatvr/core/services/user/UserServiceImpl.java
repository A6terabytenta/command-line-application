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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

import com.ntatvr.core.repositories.user.UserRepository;
import com.ntatvr.core.utils.Validator;
import com.ntatvr.domain.entities.UserEntity;

@SuppressWarnings({"PMD.TooManyStaticImports", "PMD.CyclomaticComplexity"})
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private static final List<String> SEARCHABLE_FIELDS = new ArrayList<>();

  static {
    SEARCHABLE_FIELDS.add(ID_FIELD);
    SEARCHABLE_FIELDS.add(CREATED_AT_FIELD);
    SEARCHABLE_FIELDS.add(EXTERNAL_ID_FIELD);
    SEARCHABLE_FIELDS.add(TAGS_FIELD);
    SEARCHABLE_FIELDS.add(URL_FIELD);
    SEARCHABLE_FIELDS.add(ACTIVE_FIELD);
    SEARCHABLE_FIELDS.add(ALIAS_FIELD);
    SEARCHABLE_FIELDS.add(EMAIL_FIELD);
    SEARCHABLE_FIELDS.add(LAST_LOGIN_AT_FIELD);
    SEARCHABLE_FIELDS.add(LOCALE_FIELD);
    SEARCHABLE_FIELDS.add(NAME_FIELD);
    SEARCHABLE_FIELDS.add(ORGANIZATION_ID_FIELD);
    SEARCHABLE_FIELDS.add(PHONE_FIELD);
    SEARCHABLE_FIELDS.add(ROLE_FIELD);
    SEARCHABLE_FIELDS.add(TIMEZONE_FIELD);
    SEARCHABLE_FIELDS.add(SHARED_FIELD);
    SEARCHABLE_FIELDS.add(SIGNATURE_FIELD);
    SEARCHABLE_FIELDS.add(SUSPENDED_FIELD);
    SEARCHABLE_FIELDS.add(VERIFIED_FIELD);
  }

  @Override
  public Optional<UserEntity> searchById(final String value) {
    return userRepository.getById(value);
  }

  @Override
  public List<UserEntity> searchByField(final String field, final String value) {
    final List<UserEntity> userEntities = new ArrayList<>();
    switch (field) {
      case ID_FIELD:
        userRepository.getById(value).ifPresent(userEntities::add);
        break;
      case URL_FIELD:
        userRepository.getByUrl(value).ifPresent(userEntities::add);
        break;
      case EXTERNAL_ID_FIELD:
        userRepository.getByExternalId(value).ifPresent(userEntities::add);
        break;
      case TAGS_FIELD:
        return userRepository.getByTag(value);
      case NAME_FIELD:
        return userRepository.getByName(value);
      case ALIAS_FIELD:
        return userRepository.getByAlias(value);
      case ACTIVE_FIELD:
        Validator.validateBooleanFormat(value);
        return userRepository.getByActive(BooleanUtils.toBoolean(value));
      case VERIFIED_FIELD:
        Validator.validateBooleanFormat(value);
        return userRepository.getByVerified(BooleanUtils.toBoolean(value));
      case SHARED_FIELD:
        Validator.validateBooleanFormat(value);
        return userRepository.getByShared(BooleanUtils.toBoolean(value));
      case LOCALE_FIELD:
        return userRepository.getByLocale(value);
      case EMAIL_FIELD:
        userRepository.getByEmail(value).ifPresent(userEntities::add);
        break;
      case PHONE_FIELD:
        userRepository.getByPhone(value).ifPresent(userEntities::add);
        break;
      case SIGNATURE_FIELD:
        return userRepository.getBySignature(value);
      case ORGANIZATION_ID_FIELD:
        Validator.validateNumberFormat(value);
        return userRepository.getByOrganizationId(Long.parseLong(value));
      case SUSPENDED_FIELD:
        Validator.validateBooleanFormat(value);
        return userRepository.getBySuspended(BooleanUtils.toBoolean(value));
      case TIMEZONE_FIELD:
        return userRepository.getByTimezone(value);
      case ROLE_FIELD:
        return userRepository.getByRole(value);
      case CREATED_AT_FIELD:
        return userRepository.getByCreatedAt(value);
      case LAST_LOGIN_AT_FIELD:
        return userRepository.getByLastLoginAt(value);
      default:
        throw new UnsupportedOperationException();
    }
    return userEntities;
  }

  @Override
  public List<String> getSearchableFields() {
    return SEARCHABLE_FIELDS;
  }
}
