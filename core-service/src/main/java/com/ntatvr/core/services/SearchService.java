package com.ntatvr.core.services;

import java.util.List;
import java.util.Optional;

import com.ntatvr.domain.entities.BaseEntity;

public interface SearchService<T extends BaseEntity> {

  Optional<T> searchById(final String value);

  List<T> searchByField(final String field, final String value);

  List<String> getSearchableFields();
}
