package com.ntatvr.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.ntatvr.domain.entities.BaseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapUtils {

  public static <T extends BaseEntity, K> Optional<T> getValue(final Map<K, T> map,
                                                               final K key) {

    if (Objects.nonNull(map) && map.containsKey(key)) {
      return Optional.of(map.get(key));
    }
    return Optional.empty();
  }

  public static <T extends BaseEntity, K> List<T> getValueAsList(final Map<K, List<T>> map,
                                                                 final K key) {

    if (Objects.nonNull(map) && map.containsKey(key)) {
      return map.get(key);
    }
    return Collections.emptyList();
  }

  public static <T extends BaseEntity, K> void addValueToMap(final Map<K, List<T>> map,
                                                             final K key,
                                                             final T value) {
    Preconditions.checkArgument(Objects.nonNull(map), "Map couldn't be null");
    Preconditions.checkArgument(Objects.nonNull(value), "Value couldn't be null");

    K customKey = key;
    if (Objects.isNull(key)) {
      customKey = (K) StringUtils.EMPTY;
    }

    if (map.containsKey(customKey)) {
      map.get(customKey).add(value);
    } else {
      final List<T> list = new ArrayList<>();
      list.add(value);
      map.put(customKey, list);
    }
  }
}
