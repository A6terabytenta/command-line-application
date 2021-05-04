package com.ntatvr.core.repositories.user;

import java.util.List;
import java.util.Optional;

import com.ntatvr.core.repositories.BaseRepository;
import com.ntatvr.domain.entities.UserEntity;

public interface UserRepository extends BaseRepository<UserEntity> {

  List<UserEntity> getByName(final String name);

  List<UserEntity> getByAlias(final String alias);

  List<UserEntity> getByActive(final Boolean active);

  List<UserEntity> getByVerified(final Boolean verified);

  List<UserEntity> getByShared(final Boolean shared);

  List<UserEntity> getByLocale(final String locale);

  List<UserEntity> getByTimezone(final String timezone);

  Optional<UserEntity> getByEmail(final String email);

  Optional<UserEntity> getByPhone(final String phone);

  List<UserEntity> getBySignature(final String signature);

  List<UserEntity> getByOrganizationId(final Long organizationId);

  List<UserEntity> getBySuspended(final Boolean suspended);

  List<UserEntity> getByRole(final String role);

  List<UserEntity> getByLastLoginAt(final String lastLoginAt);

}
