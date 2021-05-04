package com.ntatvr.core.repositories.ticket;

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
import com.ntatvr.domain.entities.TicketEntity;

@Repository
public class TicketRepositoryImpl extends AbstractRepository<TicketEntity> implements TicketRepository {

  @Value("${resource.data.ticket}")
  private String ticketJsonData;

  private final Map<String, List<TicketEntity>> ticketByTypeMap = new HashMap<>();
  private final Map<String, List<TicketEntity>> ticketBySubjectMap = new HashMap<>();
  private final Map<String, List<TicketEntity>> ticketByDescriptionMap = new HashMap<>();
  private final Map<String, List<TicketEntity>> ticketByPriorityMap = new HashMap<>();
  private final Map<String, List<TicketEntity>> ticketByStatusMap = new HashMap<>();
  private final Map<Long, List<TicketEntity>> ticketBySubmitterIdMap = new HashMap<>();
  private final Map<Long, List<TicketEntity>> ticketByAssigneeIdMap = new HashMap<>();
  private final Map<Long, List<TicketEntity>> ticketByOrganizationIdMap = new HashMap<>();
  private final Map<Boolean, List<TicketEntity>> ticketByHasIncidentsMap = new HashMap<>();
  private final Map<String, List<TicketEntity>> ticketByViaMap = new HashMap<>();
  private final Map<String, List<TicketEntity>> ticketByDueAtMap = new HashMap<>();

  @Autowired
  private JsonReader jsonReader;

  @PostConstruct
  public void init() throws IOException {
    jsonReader.readFromFile(ticketJsonData, new TypeReference<List<TicketEntity>>() {
    }).forEach(ticket -> {
      entityByIdMap.put(ticket.getId(), ticket);
      entityByUrlMap.put(ticket.getUrl(), ticket);
      entityByExternalIdMap.put(ticket.getExternalId(), ticket);
      MapUtils.addValueToMap(entityByCreatedAtMap, ticket.getCreatedAt(), ticket);
      CollectionUtils.emptyIfNull(ticket.getTags()).forEach(tag -> MapUtils.addValueToMap(entityByTagMap, tag, ticket));
      MapUtils.addValueToMap(ticketByTypeMap, ticket.getType(), ticket);
      MapUtils.addValueToMap(ticketBySubjectMap, ticket.getSubject(), ticket);
      MapUtils.addValueToMap(ticketByDescriptionMap, ticket.getDescription(), ticket);
      MapUtils.addValueToMap(ticketByPriorityMap, ticket.getPriority(), ticket);
      MapUtils.addValueToMap(ticketByStatusMap, ticket.getStatus(), ticket);
      MapUtils.addValueToMap(ticketBySubmitterIdMap, ticket.getSubmitterId(), ticket);
      MapUtils.addValueToMap(ticketByAssigneeIdMap, ticket.getAssigneeId(), ticket);
      MapUtils.addValueToMap(ticketByOrganizationIdMap, ticket.getOrganizationId(), ticket);
      MapUtils.addValueToMap(ticketByHasIncidentsMap, ticket.getHasIncidents(), ticket);
      MapUtils.addValueToMap(ticketByViaMap, ticket.getVia(), ticket);
      MapUtils.addValueToMap(ticketByDueAtMap, ticket.getDueAt(), ticket);
    });
  }

  @Override
  public List<TicketEntity> getByType(final String type) {
    return MapUtils.getValueAsList(ticketByTypeMap, type);
  }

  @Override
  public List<TicketEntity> getBySubject(final String subject) {
    return MapUtils.getValueAsList(ticketBySubjectMap, subject);
  }

  @Override
  public List<TicketEntity> getByDescription(final String description) {
    return MapUtils.getValueAsList(ticketByDescriptionMap, description);
  }

  @Override
  public List<TicketEntity> getByPriority(final String priority) {
    return MapUtils.getValueAsList(ticketByPriorityMap, priority);
  }

  @Override
  public List<TicketEntity> getByStatus(final String status) {
    return MapUtils.getValueAsList(ticketByStatusMap, status);
  }

  @Override
  public List<TicketEntity> getBySubmitterId(final Long submitterId) {
    return MapUtils.getValueAsList(ticketBySubmitterIdMap, submitterId);
  }

  @Override
  public List<TicketEntity> getByAssigneeId(final Long assigneeId) {
    return MapUtils.getValueAsList(ticketByAssigneeIdMap, assigneeId);
  }

  @Override
  public List<TicketEntity> getByOrganizationId(final Long organizationId) {
    return MapUtils.getValueAsList(ticketByOrganizationIdMap, organizationId);
  }

  @Override
  public List<TicketEntity> getByHasIncidents(final Boolean hasIncidents) {
    return MapUtils.getValueAsList(ticketByHasIncidentsMap, hasIncidents);
  }

  @Override
  public List<TicketEntity> getByVia(final String via) {
    return MapUtils.getValueAsList(ticketByViaMap, via);
  }

  @Override
  public List<TicketEntity> getByDueAt(final String dueAt) {
    return MapUtils.getValueAsList(ticketByDueAtMap, dueAt);
  }
}
