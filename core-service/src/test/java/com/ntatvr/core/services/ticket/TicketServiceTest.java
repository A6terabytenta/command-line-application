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

import java.util.List;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ntatvr.core.MockTest;
import com.ntatvr.core.TestDataProvider;
import com.ntatvr.core.exceptions.BooleanFormatException;
import com.ntatvr.core.repositories.ticket.TicketRepository;
import com.ntatvr.domain.entities.TicketEntity;

public class TicketServiceTest extends MockTest {

  @InjectMocks
  private TicketServiceImpl ticketService;

  @Mock
  private TicketRepository ticketRepository;

  private TicketEntity ticketEntity;

  @Before
  public void setup() {
    ticketEntity = TestDataProvider.buildTiketEntity();
  }

  @Test
  public void searchById_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getById(Mockito.any())).thenReturn(Optional.empty());
    final Optional<TicketEntity> ticketEntityOpt =
        ticketService.searchById(ticketEntity.getId());
    MatcherAssert.assertThat(ticketEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void searchById_shouldReturnData() {
    Mockito.when(ticketRepository.getById(Mockito.any())).thenReturn(Optional.of(ticketEntity));
    final Optional<TicketEntity> ticketEntityOpt =
        ticketService.searchById(ticketEntity.getId());
    MatcherAssert.assertThat(ticketEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(ticketEntityOpt.get(), Matchers.is(ticketEntity));
  }

  @Test
  public void getSearchableFields_shouldReturnData() {
    final List<String> searchableFields = ticketService.getSearchableFields();
    MatcherAssert.assertThat(searchableFields, Matchers.hasSize(16));
    MatcherAssert.assertThat(searchableFields, Matchers.containsInAnyOrder(ID_FIELD, CREATED_AT_FIELD,
        EXTERNAL_ID_FIELD, TAGS_FIELD, URL_FIELD, TYPE_FIELD, SUBJECT_FIELD, DESCRIPTION_FIELD, PRIORITY_FIELD,
        STATUS_FIELD, SUBMITTER_ID_FIELD, ORGANIZATION_ID_FIELD, ASSIGNEE_ID_FIELD, HAS_INCIDENTS_FIELD,
        DUE_AT_FIELD, VIA_FIELD));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getByUnsupportedField_shouldThrowException() {
    ticketService.searchByField("unsupported-field", "123");
  }

  @Test
  public void getById_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getById(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(ticketService.searchByField(ID_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getById_shouldReturnData() {
    Mockito.when(ticketRepository.getById(Mockito.any())).thenReturn(Optional.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(ID_FIELD,
        ticketEntity.getId());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByUrl_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getByUrl(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(ticketService.searchByField(URL_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByUrl_shouldReturnData() {
    Mockito.when(ticketRepository.getByUrl(Mockito.any())).thenReturn(Optional.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(URL_FIELD,
        ticketEntity.getUrl());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByExternalId_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getByExternalId(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(ticketService.searchByField(EXTERNAL_ID_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByExternalId_shouldReturnData() {
    Mockito.when(ticketRepository.getByExternalId(Mockito.any())).thenReturn(Optional.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(EXTERNAL_ID_FIELD,
        ticketEntity.getExternalId());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByType_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getByType(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(ticketService.searchByField(TYPE_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByType_shouldReturnData() {
    Mockito.when(ticketRepository.getByType(Mockito.any())).thenReturn(List.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(TYPE_FIELD,
        ticketEntity.getType());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getBySubject_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getBySubject(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(ticketService.searchByField(SUBJECT_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getBySubject_shouldReturnData() {
    Mockito.when(ticketRepository.getBySubject(Mockito.any())).thenReturn(List.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(SUBJECT_FIELD,
        ticketEntity.getSubject());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByDescription_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getByDescription(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(ticketService.searchByField(DESCRIPTION_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByDescription_shouldReturnData() {
    Mockito.when(ticketRepository.getByDescription(Mockito.any())).thenReturn(List.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(DESCRIPTION_FIELD,
        ticketEntity.getDescription());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByPriority_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getByPriority(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(ticketService.searchByField(PRIORITY_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByPriority_shouldReturnData() {
    Mockito.when(ticketRepository.getByPriority(Mockito.any())).thenReturn(List.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(PRIORITY_FIELD,
        ticketEntity.getPriority());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByStatus_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getByStatus(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(ticketService.searchByField(STATUS_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByStatus_shouldReturnData() {
    Mockito.when(ticketRepository.getByStatus(Mockito.any())).thenReturn(List.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(STATUS_FIELD,
        ticketEntity.getStatus());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test(expected = NumberFormatException.class)
  public void getBySubmitterId_shouldThrowException() {
    ticketService.searchByField(SUBMITTER_ID_FIELD, "INVALID_Number_Format");
  }

  @Test
  public void getBySubmitterId_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getBySubmitterId(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(ticketService.searchByField(SUBMITTER_ID_FIELD, "1"), Matchers.empty());
  }

  @Test
  public void getBySubmitterId_shouldReturnData() {
    Mockito.when(ticketRepository.getBySubmitterId(Mockito.any())).thenReturn(List.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(SUBMITTER_ID_FIELD,
        ticketEntity.getSubmitterId().toString());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test(expected = NumberFormatException.class)
  public void getByAssigneeId_shouldThrowException() {
    ticketService.searchByField(ASSIGNEE_ID_FIELD, "INVALID_Number_format");
  }

  @Test
  public void getByAssigneeId_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getByAssigneeId(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(ticketService.searchByField(ASSIGNEE_ID_FIELD, "1"), Matchers.empty());
  }

  @Test
  public void getByAssigneeId_shouldReturnData() {
    Mockito.when(ticketRepository.getByAssigneeId(Mockito.any())).thenReturn(List.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(ASSIGNEE_ID_FIELD,
        ticketEntity.getAssigneeId().toString());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test(expected = NumberFormatException.class)
  public void getByOrganizationId_shouldThrowException() {
    ticketService.searchByField(ORGANIZATION_ID_FIELD, "INVALID_Number_Format");
  }

  @Test
  public void getByOrganizationId_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getByOrganizationId(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(ticketService.searchByField(ORGANIZATION_ID_FIELD, "1"), Matchers.empty());
  }

  @Test
  public void getByOrganizationId_shouldReturnData() {
    Mockito.when(ticketRepository.getByOrganizationId(Mockito.any())).thenReturn(List.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(ORGANIZATION_ID_FIELD,
        ticketEntity.getOrganizationId().toString());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test(expected = BooleanFormatException.class)
  public void getByHasIncidents_shouldThrowException() {
    ticketService.searchByField(HAS_INCIDENTS_FIELD, "INVALID_Boolean_Format");
  }

  @Test
  public void getByHasIncidents_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getByHasIncidents(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(ticketService.searchByField(HAS_INCIDENTS_FIELD, "False"), Matchers.empty());
  }

  @Test
  public void getByHasIncidents_shouldReturnData() {
    Mockito.when(ticketRepository.getByHasIncidents(Mockito.any())).thenReturn(List.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(HAS_INCIDENTS_FIELD,
        ticketEntity.getHasIncidents().toString());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByVia_shouldReturnEmpty() {
    Mockito.when(ticketRepository.getByVia(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(ticketService.searchByField(VIA_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByVia_shouldReturnData() {
    Mockito.when(ticketRepository.getByVia(Mockito.any())).thenReturn(List.of(ticketEntity));
    final List<TicketEntity> ticketEntities = ticketService.searchByField(VIA_FIELD,
        ticketEntity.getVia());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }
}