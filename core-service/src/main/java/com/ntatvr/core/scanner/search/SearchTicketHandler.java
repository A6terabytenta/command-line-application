package com.ntatvr.core.scanner.search;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.ntatvr.core.scanner.SearchInterface;
import com.ntatvr.core.services.SearchService;
import com.ntatvr.core.services.organization.OrganizationService;
import com.ntatvr.core.services.ticket.TicketService;
import com.ntatvr.core.services.user.UserService;
import com.ntatvr.domain.entities.OrganizationEntity;
import com.ntatvr.domain.entities.TicketEntity;
import com.ntatvr.domain.entities.UserEntity;

@Component
@RequiredArgsConstructor
public class SearchTicketHandler extends AbstractSearchHandler<TicketEntity> implements SearchInterface {

  private final TicketService ticketService;
  private final UserService userService;
  private final OrganizationService organizationService;

  @Override
  public SearchService getSearchService() {
    return ticketService;
  }

  @Override
  public String getEntityName() {
    return TicketEntity.COLLECTION_NAME;
  }

  @Override
  public void printResultByEntity(final TicketEntity entity) {
    System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.TYPE_FIELD, entity.getType());
    System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.SUBJECT_FIELD, entity.getSubject());
    System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.DESCRIPTION_FIELD, entity.getDescription());
    System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.PRIORITY_FIELD, entity.getPriority());
    System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.STATUS_FIELD, entity.getStatus());
    System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.HAS_INCIDENTS_FIELD, entity.getHasIncidents());
    System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.DUE_AT_FIELD, entity.getDueAt());
    System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.VIA_FIELD, entity.getVia());
    System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.SUBMITTER_ID_FIELD, entity.getSubmitterId());
    System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.ASSIGNEE_ID_FIELD, entity.getAssigneeId());
    printReferenceOrganization(entity.getOrganizationId());
    printReferenceUsers(entity);
  }

  private void printReferenceOrganization(final Long organizationId) {
    final Optional<OrganizationEntity> organizationOpt = organizationService.searchById(organizationId.toString());
    if (organizationOpt.isEmpty()) {
      System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.ORGANIZATION_NAME_FIELD, "Organization not found");
    } else {
      System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.ORGANIZATION_NAME_FIELD, organizationOpt.get().getName());
    }
  }

  private void printReferenceUsers(final TicketEntity entity) {

    final Optional<UserEntity> submitterOpt = userService.searchById(entity.getSubmitterId().toString());
    if (submitterOpt.isEmpty()) {
      System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.SUBMITTER_NAME_FIELD, "Submitter not found");
    } else {
      System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.SUBMITTER_NAME_FIELD, submitterOpt.get().getName());
    }

    final Optional<UserEntity> assigneeOpt = userService.searchById(entity.getAssigneeId().toString());
    if (assigneeOpt.isEmpty()) {
      System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.ASSIGNEE_NAME_FIELD, "Assignee not found");
    } else {
      System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.ASSIGNEE_NAME_FIELD, assigneeOpt.get().getName());
    }
  }
}
