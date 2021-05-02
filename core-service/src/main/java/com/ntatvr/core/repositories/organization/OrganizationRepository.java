package com.ntatvr.core.repositories.organization;

import java.util.List;

import com.ntatvr.core.repositories.BaseRepository;
import com.ntatvr.domain.entities.OrganizationEntity;

public interface OrganizationRepository extends BaseRepository<OrganizationEntity> {

  List<OrganizationEntity> getByName(final String name);

  List<OrganizationEntity> getByDomainName(final String domainName);

  List<OrganizationEntity> getByDetails(final String details);

  List<OrganizationEntity> getBySharedTickets(final Boolean sharedTickets);
}
