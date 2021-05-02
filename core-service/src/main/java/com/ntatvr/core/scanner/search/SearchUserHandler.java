package com.ntatvr.core.scanner.search;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.ntatvr.core.scanner.SearchInterface;
import com.ntatvr.core.services.organization.OrganizationService;
import com.ntatvr.core.services.ticket.TicketService;
import com.ntatvr.core.services.user.UserService;
import com.ntatvr.domain.entities.OrganizationEntity;
import com.ntatvr.domain.entities.TicketEntity;
import com.ntatvr.domain.entities.UserEntity;

@Component
@RequiredArgsConstructor
public class SearchUserHandler extends AbstractSearchHandler<UserEntity> implements SearchInterface {

  private final UserService userService;
  private final OrganizationService organizationService;
  private final TicketService ticketService;

  @Override
  public UserService getSearchService() {
    return userService;
  }

  @Override
  public String getEntityName() {
    return UserEntity.COLLECTION_NAME;
  }

  @Override
  public void printResultByEntity(final UserEntity entity) {
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.NAME_FIELD, entity.getName());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.ALIAS_FIELD, entity.getAlias());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.ACTIVE_FIELD, entity.getActive());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.VERIFIED_FIELD, entity.getVerified());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.SHARED_FIELD, entity.getShared());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.LOCALE_FIELD, entity.getLocale());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.TIMEZONE_FIELD, entity.getTimezone());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.LAST_LOGIN_AT_FIELD, entity.getLastLoginAt());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.EMAIL_FIELD, entity.getEmail());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.PHONE_FIELD, entity.getPhone());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.SIGNATURE_FIELD, entity.getSignature());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.SUSPENDED_FIELD, entity.getSuspended());
    System.out.format(LEFT_ALIGN_FORMAT, UserEntity.ROLE_FIELD, entity.getRole());
    printReferenceOrganization(entity.getOrganizationId());
    printReferenceTickets(entity);
  }

  private void printReferenceTickets(final UserEntity entity) {
    final List<TicketEntity> ticketEntities = ticketService.searchByField(TicketEntity.ASSIGNEE_ID_FIELD,
        entity.getId());
    if (ticketEntities.isEmpty()) {
      System.out.format(LEFT_ALIGN_FORMAT, UserEntity.ORGANIZATION_NAME_FIELD, "Ticket not found");
    } else {
      for (int i = 0; i < ticketEntities.size(); i++) {
        System.out.format(LEFT_ALIGN_FORMAT, UserEntity.TICKET_FIELD + i, ticketEntities.get(i).getSubject());
      }
    }
  }

  private void printReferenceOrganization(final Long organizationId) {
    final Optional<OrganizationEntity> organizationOpt = organizationService.searchById(organizationId.toString());
    if (organizationOpt.isEmpty()) {
      System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.ORGANIZATION_NAME_FIELD, "Organization not found");
    } else {
      System.out.format(LEFT_ALIGN_FORMAT, TicketEntity.ORGANIZATION_NAME_FIELD, organizationOpt.get().getName());
    }
  }
}
