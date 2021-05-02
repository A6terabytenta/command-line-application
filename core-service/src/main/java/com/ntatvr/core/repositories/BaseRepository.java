package com.ntatvr.core.repositories;

import java.util.List;
import java.util.Optional;

import com.ntatvr.domain.entities.BaseEntity;

public interface BaseRepository<T extends BaseEntity> {

  Optional<T> getById(final String id);

  Optional<T> getByUrl(final String url);

  Optional<T> getByExternalId(final String externalId);

  List<T> getByTag(final String tag);
}
