package com.ntatvr.core.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ntatvr.core.utils.MapUtils;
import com.ntatvr.domain.entities.BaseEntity;

public abstract class AbstractRepository<T extends BaseEntity> implements BaseRepository<T> {

  protected Map<String, T> entityByIdMap = new HashMap<>();
  protected Map<String, T> entityByUrlMap = new HashMap<>();
  protected Map<String, T> entityByExternalIdMap = new HashMap<>();
  protected Map<String, List<T>> entityByTagMap = new HashMap<>();
  protected Map<String, List<T>> entityByCreatedAtMap = new HashMap<>();


  @Override
  public Optional<T> getById(final String id) {
    return MapUtils.getValue(entityByIdMap, id);
  }

  @Override
  public Optional<T> getByUrl(final String url) {
    return MapUtils.getValue(entityByUrlMap, url);
  }

  @Override
  public Optional<T> getByExternalId(final String externalId) {
    return MapUtils.getValue(entityByExternalIdMap, externalId);
  }

  @Override
  public List<T> getByTag(final String tag) {
    return MapUtils.getValueAsList(entityByTagMap, tag);
  }

  @Override
  public List<T> getByCreatedAt(final String createdAt) {
    return MapUtils.getValueAsList(entityByCreatedAtMap, createdAt);
  }
}
