package com.ntatvr.core.services.organization;

import static com.ntatvr.domain.entities.BaseEntity.CREATED_AT_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.EXTERNAL_ID_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.ID_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.TAGS_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.URL_FIELD;
import static com.ntatvr.domain.entities.OrganizationEntity.DETAILS_FIELD;
import static com.ntatvr.domain.entities.OrganizationEntity.DOMAIN_NAMES_FIELD;
import static com.ntatvr.domain.entities.OrganizationEntity.NAME_FIELD;
import static com.ntatvr.domain.entities.OrganizationEntity.SHARED_TICKETS_FIELD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

import com.ntatvr.core.repositories.organization.OrganizationRepository;
import com.ntatvr.core.utils.Validator;
import com.ntatvr.domain.entities.OrganizationEntity;

@SuppressWarnings({"PMD.TooManyStaticImports", "PMD.CyclomaticComplexity"})
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

  private final OrganizationRepository organizationRepository;
  private static final List<String> SEARCHABLE_FIELDS = new ArrayList<>();

  static {
    SEARCHABLE_FIELDS.add(ID_FIELD);
    SEARCHABLE_FIELDS.add(URL_FIELD);
    SEARCHABLE_FIELDS.add(CREATED_AT_FIELD);
    SEARCHABLE_FIELDS.add(EXTERNAL_ID_FIELD);
    SEARCHABLE_FIELDS.add(NAME_FIELD);
    SEARCHABLE_FIELDS.add(DOMAIN_NAMES_FIELD);
    SEARCHABLE_FIELDS.add(DETAILS_FIELD);
    SEARCHABLE_FIELDS.add(SHARED_TICKETS_FIELD);
    SEARCHABLE_FIELDS.add(TAGS_FIELD);
  }

  @Override
  public Optional<OrganizationEntity> searchById(final String value) {
    return organizationRepository.getById(value);
  }

  @Override
  public List<OrganizationEntity> searchByField(final String field, final String value) {
    final List<OrganizationEntity> organizationEntities = new ArrayList<>();
    switch (field) {
      case ID_FIELD:
        organizationRepository.getById(value).ifPresent(organizationEntities::add);
        break;
      case URL_FIELD:
        organizationRepository.getByUrl(value).ifPresent(organizationEntities::add);
        break;
      case EXTERNAL_ID_FIELD:
        organizationRepository.getByExternalId(value).ifPresent(organizationEntities::add);
        break;
      case TAGS_FIELD:
        return organizationRepository.getByTag(value);
      case NAME_FIELD:
        return organizationRepository.getByName(value);
      case DOMAIN_NAMES_FIELD:
        return organizationRepository.getByDomainName(value);
      case DETAILS_FIELD:
        return organizationRepository.getByDetails(value);
      case SHARED_TICKETS_FIELD:
        Validator.validateBooleanFormat(value);
        return organizationRepository.getBySharedTickets(BooleanUtils.toBoolean(value));
      case CREATED_AT_FIELD:
        return organizationRepository.getByCreatedAt(value);
      default:
        throw new UnsupportedOperationException();
    }
    return organizationEntities;
  }

  @Override
  public List<String> getSearchableFields() {
    return SEARCHABLE_FIELDS;
  }
}
