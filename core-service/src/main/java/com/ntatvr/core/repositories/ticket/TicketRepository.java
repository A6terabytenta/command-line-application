package com.ntatvr.core.repositories.ticket;

import java.util.List;

import com.ntatvr.core.repositories.BaseRepository;
import com.ntatvr.domain.entities.TicketEntity;

public interface TicketRepository extends BaseRepository<TicketEntity> {

  List<TicketEntity> getByType(final String type);

  List<TicketEntity> getBySubject(final String subject);

  List<TicketEntity> getByDescription(final String description);

  List<TicketEntity> getByPriority(final String priority);

  List<TicketEntity> getByStatus(final String status);

  List<TicketEntity> getBySubmitterId(final Long submitterId);

  List<TicketEntity> getByAssigneeId(final Long assigneeId);

  List<TicketEntity> getByOrganizationId(final Long organizationId);

  List<TicketEntity> getByHasIncidents(final Boolean hasIncidents);

  List<TicketEntity> getByVia(final String via);

  List<TicketEntity> getByDueAt(final String dueAt);

}
