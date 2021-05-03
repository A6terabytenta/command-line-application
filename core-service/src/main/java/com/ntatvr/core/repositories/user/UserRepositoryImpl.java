package com.ntatvr.core.repositories.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ntatvr.core.repositories.AbstractRepository;
import com.ntatvr.core.utils.JsonReader;
import com.ntatvr.core.utils.MapUtils;
import com.ntatvr.domain.entities.UserEntity;

@Repository
public class UserRepositoryImpl extends AbstractRepository<UserEntity> implements UserRepository {

  @Value("${resource.data.user}")
  private String userJsonData;

  private final Map<String, List<UserEntity>> userByNameMap = new HashMap<>();
  private final Map<String, List<UserEntity>> userByAliasMap = new HashMap<>();
  private final Map<Boolean, List<UserEntity>> userByActiveMap = new HashMap<>();
  private final Map<Boolean, List<UserEntity>> userByVerifiedMap = new HashMap<>();
  private final Map<Boolean, List<UserEntity>> userBySharedMap = new HashMap<>();
  private final Map<String, List<UserEntity>> userByLocaleMap = new HashMap<>();
  private final Map<String, List<UserEntity>> userByTimezoneMap = new HashMap<>();
  private final Map<String, UserEntity> userByEmailMap = new HashMap<>();
  private final Map<String, UserEntity> userByPhoneMap = new HashMap<>();
  private final Map<String, List<UserEntity>> userBySignatureMap = new HashMap<>();
  private final Map<Long, List<UserEntity>> userByOrganizationIdMap = new HashMap<>();
  private final Map<Boolean, List<UserEntity>> userBySuspendedMap = new HashMap<>();
  private final Map<String, List<UserEntity>> userByRoleMap = new HashMap<>();

  @Autowired
  private JsonReader jsonReader;

  @PostConstruct
  public void init() throws IOException {
    System.out.println("Loading Users from: " + userJsonData);
    jsonReader.readFromFile(userJsonData, new TypeReference<List<UserEntity>>() {
    }).forEach(user -> {
      userByIdMap.put(user.getId(), user);
      userByUrlMap.put(user.getUrl(), user);
      userByExternalIdMap.put(user.getExternalId(), user);
      CollectionUtils.emptyIfNull(user.getTags()).forEach(tag -> MapUtils.addValueToMap(userByTagMap, tag, user));

      MapUtils.addValueToMap(userByNameMap, user.getName(), user);
      MapUtils.addValueToMap(userByAliasMap, user.getAlias(), user);
      MapUtils.addValueToMap(userByActiveMap, user.getActive(), user);
      MapUtils.addValueToMap(userByVerifiedMap, user.getVerified(), user);
      MapUtils.addValueToMap(userBySharedMap, user.getShared(), user);
      MapUtils.addValueToMap(userByLocaleMap, user.getLocale(), user);
      MapUtils.addValueToMap(userByTimezoneMap, user.getTimezone(), user);
      userByEmailMap.put(user.getEmail(), user);
      userByPhoneMap.put(user.getPhone(), user);
      MapUtils.addValueToMap(userBySignatureMap, user.getSignature(), user);
      MapUtils.addValueToMap(userByOrganizationIdMap, user.getOrganizationId(), user);
      MapUtils.addValueToMap(userBySuspendedMap, user.getSuspended(), user);
      MapUtils.addValueToMap(userByRoleMap, user.getRole(), user);
    });
    System.out.println("Load Users successful");
  }

  @Override
  public List<UserEntity> getByName(final String name) {
    return MapUtils.getValueAsList(userByNameMap, name);
  }

  @Override
  public List<UserEntity> getByAlias(final String alias) {
    return MapUtils.getValueAsList(userByAliasMap, alias);
  }

  @Override
  public List<UserEntity> getByActive(final Boolean active) {
    return MapUtils.getValueAsList(userByActiveMap, active);
  }

  @Override
  public List<UserEntity> getByVerified(final Boolean verified) {
    return MapUtils.getValueAsList(userByVerifiedMap, verified);
  }

  @Override
  public List<UserEntity> getByShared(final Boolean shared) {
    return MapUtils.getValueAsList(userBySharedMap, shared);
  }

  @Override
  public List<UserEntity> getByLocale(final String locale) {
    return MapUtils.getValueAsList(userByLocaleMap, locale);
  }

  @Override
  public List<UserEntity> getByTimezone(final String timezone) {
    return MapUtils.getValueAsList(userByTimezoneMap, timezone);
  }

  @Override
  public Optional<UserEntity> getByEmail(final String email) {
    return MapUtils.getValue(userByEmailMap, email);
  }

  @Override
  public Optional<UserEntity> getByPhone(final String phone) {
    return MapUtils.getValue(userByPhoneMap, phone);
  }

  @Override
  public List<UserEntity> getBySignature(final String signature) {
    return MapUtils.getValueAsList(userBySignatureMap, signature);
  }

  @Override
  public List<UserEntity> getByOrganizationId(final Long organizationId) {
    return MapUtils.getValueAsList(userByOrganizationIdMap, organizationId);
  }

  @Override
  public List<UserEntity> getBySuspended(final Boolean suspended) {
    return MapUtils.getValueAsList(userBySuspendedMap, suspended);
  }

  @Override
  public List<UserEntity> getByRole(final String role) {
    return MapUtils.getValueAsList(userByRoleMap, role);
  }
}
