package com.ntatvr.core.scanner.search;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.ntatvr.core.scanner.SearchInterface;
import com.ntatvr.core.services.SearchService;
import com.ntatvr.core.services.organization.OrganizationService;
import com.ntatvr.domain.entities.OrganizationEntity;

@Component
@RequiredArgsConstructor
public class SearchOrganizationHandler extends AbstractSearchHandler<OrganizationEntity> implements SearchInterface {

  private final OrganizationService organizationService;

  @Override
  public SearchService<OrganizationEntity> getSearchService() {
    return organizationService;
  }

  @Override
  public String getEntityName() {
    return OrganizationEntity.COLLECTION_NAME;
  }

  @Override
  public void printResultByEntity(final OrganizationEntity entity) {
    System.out.format(LEFT_ALIGN_FORMAT, OrganizationEntity.NAME_FIELD, entity.getName());
    System.out.format(LEFT_ALIGN_FORMAT, OrganizationEntity.DOMAIN_NAMES_FIELD, entity.getDomainNames());
    System.out.format(LEFT_ALIGN_FORMAT, OrganizationEntity.DETAILS_FIELD, entity.getDetails());
    System.out.format(LEFT_ALIGN_FORMAT, OrganizationEntity.SHARED_TICKETS_FIELD, entity.getSharedTickets());
  }
}
