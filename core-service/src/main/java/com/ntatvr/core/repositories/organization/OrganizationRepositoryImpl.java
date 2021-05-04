package com.ntatvr.core.repositories.organization;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ntatvr.core.repositories.AbstractRepository;
import com.ntatvr.core.utils.JsonReader;
import com.ntatvr.core.utils.MapUtils;
import com.ntatvr.domain.entities.OrganizationEntity;

@Repository
public class OrganizationRepositoryImpl extends AbstractRepository<OrganizationEntity> implements OrganizationRepository {

  @Value("${resource.data.organization}")
  private String ticketJsonData;

  private final Map<String, List<OrganizationEntity>> organizationByNameMap = new HashMap<>();
  private final Map<String, List<OrganizationEntity>> organizationByDomainNamesMap = new HashMap<>();
  private final Map<String, List<OrganizationEntity>> organizationByDetailsMap = new HashMap<>();
  private final Map<Boolean, List<OrganizationEntity>> organizationBySharedTicketsMap = new HashMap<>();

  @Autowired
  private JsonReader jsonReader;

  @PostConstruct
  public void init() throws IOException {
    jsonReader.readFromFile(ticketJsonData, new TypeReference<List<OrganizationEntity>>() {
    }).forEach(organization -> {
      entityByIdMap.put(organization.getId(), organization);
      entityByUrlMap.put(organization.getUrl(), organization);
      entityByExternalIdMap.put(organization.getExternalId(), organization);
      MapUtils.addValueToMap(entityByCreatedAtMap, organization.getCreatedAt(), organization);
      CollectionUtils.emptyIfNull(organization.getTags())
          .forEach(tag -> MapUtils.addValueToMap(entityByTagMap, tag, organization));
      CollectionUtils.emptyIfNull(organization.getDomainNames())
          .forEach(domain -> MapUtils.addValueToMap(organizationByDomainNamesMap, domain, organization));
      MapUtils.addValueToMap(organizationByDetailsMap, organization.getDetails(), organization);
      MapUtils.addValueToMap(organizationBySharedTicketsMap, organization.getSharedTickets(), organization);
      MapUtils.addValueToMap(organizationByNameMap, organization.getName(), organization);
    });
  }

  @Override
  public List<OrganizationEntity> getByName(final String name) {
    return MapUtils.getValueAsList(organizationByNameMap, name);
  }

  @Override
  public List<OrganizationEntity> getByDomainName(final String domainName) {
    return MapUtils.getValueAsList(organizationByDomainNamesMap, domainName);
  }

  @Override
  public List<OrganizationEntity> getByDetails(final String details) {
    return MapUtils.getValueAsList(organizationByDetailsMap, details);
  }

  @Override
  public List<OrganizationEntity> getBySharedTickets(final Boolean sharedTickets) {
    return MapUtils.getValueAsList(organizationBySharedTicketsMap, sharedTickets);
  }
}
