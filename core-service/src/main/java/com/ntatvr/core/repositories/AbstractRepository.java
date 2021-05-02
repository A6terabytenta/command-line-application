package com.ntatvr.core.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ntatvr.core.utils.MapUtils;
import com.ntatvr.domain.entities.BaseEntity;

public abstract class AbstractRepository<T extends BaseEntity> implements BaseRepository<T> {

  protected Map<String, T> userByIdMap = new HashMap<>();
  protected Map<String, T> userByUrlMap = new HashMap<>();
  protected Map<String, T> userByExternalIdMap = new HashMap<>();
  protected Map<String, List<T>> userByTagMap = new HashMap<>();

  @Override
  public Optional<T> getById(final String id) {
    return MapUtils.getValue(userByIdMap, id);
  }

  @Override
  public Optional<T> getByUrl(final String url) {
    return MapUtils.getValue(userByUrlMap, url);
  }

  @Override
  public Optional<T> getByExternalId(final String externalId) {
    return MapUtils.getValue(userByExternalIdMap, externalId);
  }

  @Override
  public List<T> getByTag(final String tag) {
    return MapUtils.getValueAsList(userByTagMap, tag);
  }
}
