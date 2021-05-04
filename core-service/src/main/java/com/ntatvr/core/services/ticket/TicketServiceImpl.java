package com.ntatvr.core.services.ticket;

import static com.ntatvr.domain.entities.BaseEntity.CREATED_AT_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.EXTERNAL_ID_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.ID_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.TAGS_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.URL_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.ASSIGNEE_ID_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.DESCRIPTION_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.DUE_AT_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.HAS_INCIDENTS_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.ORGANIZATION_ID_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.PRIORITY_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.STATUS_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.SUBJECT_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.SUBMITTER_ID_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.TYPE_FIELD;
import static com.ntatvr.domain.entities.TicketEntity.VIA_FIELD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

import com.ntatvr.core.repositories.ticket.TicketRepository;
import com.ntatvr.core.utils.Validator;
import com.ntatvr.domain.entities.TicketEntity;

@SuppressWarnings({"PMD.TooManyStaticImports", "PMD.CyclomaticComplexity"})
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;
  private static final List<String> SEARCHABLE_FIELDS = new ArrayList<>();

  static {
    SEARCHABLE_FIELDS.add(ID_FIELD);
    SEARCHABLE_FIELDS.add(CREATED_AT_FIELD);
    SEARCHABLE_FIELDS.add(EXTERNAL_ID_FIELD);
    SEARCHABLE_FIELDS.add(TAGS_FIELD);
    SEARCHABLE_FIELDS.add(URL_FIELD);
    SEARCHABLE_FIELDS.add(TYPE_FIELD);
    SEARCHABLE_FIELDS.add(SUBJECT_FIELD);
    SEARCHABLE_FIELDS.add(DESCRIPTION_FIELD);
    SEARCHABLE_FIELDS.add(PRIORITY_FIELD);
    SEARCHABLE_FIELDS.add(STATUS_FIELD);
    SEARCHABLE_FIELDS.add(SUBMITTER_ID_FIELD);
    SEARCHABLE_FIELDS.add(ORGANIZATION_ID_FIELD);
    SEARCHABLE_FIELDS.add(ASSIGNEE_ID_FIELD);
    SEARCHABLE_FIELDS.add(HAS_INCIDENTS_FIELD);
    SEARCHABLE_FIELDS.add(DUE_AT_FIELD);
    SEARCHABLE_FIELDS.add(VIA_FIELD);
  }

  @Override
  public Optional<TicketEntity> searchById(final String value) {
    return ticketRepository.getById(value);
  }

  @Override
  public List<TicketEntity> searchByField(final String field, final String value) {
    final List<TicketEntity> ticketEntities = new ArrayList<>();
    switch (field) {
      case ID_FIELD:
        ticketRepository.getById(value).ifPresent(ticketEntities::add);
        break;
      case URL_FIELD:
        ticketRepository.getByUrl(value).ifPresent(ticketEntities::add);
        break;
      case EXTERNAL_ID_FIELD:
        ticketRepository.getByExternalId(value).ifPresent(ticketEntities::add);
        break;
      case TAGS_FIELD:
        return ticketRepository.getByTag(value);
      case TYPE_FIELD:
        return ticketRepository.getByType(value);
      case SUBJECT_FIELD:
        return ticketRepository.getBySubject(value);
      case DESCRIPTION_FIELD:
        return ticketRepository.getByDescription(value);
      case PRIORITY_FIELD:
        return ticketRepository.getByPriority(value);
      case STATUS_FIELD:
        return ticketRepository.getByStatus(value);
      case SUBMITTER_ID_FIELD:
        Validator.validateNumberFormat(value);
        return ticketRepository.getBySubmitterId(Long.valueOf(value));
      case ORGANIZATION_ID_FIELD:
        Validator.validateNumberFormat(value);
        return ticketRepository.getByOrganizationId(Long.valueOf(value));
      case ASSIGNEE_ID_FIELD:
        Validator.validateNumberFormat(value);
        return ticketRepository.getByAssigneeId(Long.valueOf(value));
      case HAS_INCIDENTS_FIELD:
        Validator.validateBooleanFormat(value);
        return ticketRepository.getByHasIncidents(BooleanUtils.toBoolean(value));
      case VIA_FIELD:
        return ticketRepository.getByVia(value);
      case CREATED_AT_FIELD:
        return ticketRepository.getByCreatedAt(value);
      case DUE_AT_FIELD:
        return ticketRepository.getByDueAt(value);
      default:
        throw new UnsupportedOperationException();
    }
    return ticketEntities;
  }

  @Override
  public List<String> getSearchableFields() {
    return SEARCHABLE_FIELDS;
  }
}
