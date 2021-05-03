package com.ntatvr.core.repositories.ticket;

import java.io.IOException;
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
import com.ntatvr.core.utils.JsonReader;
import com.ntatvr.domain.entities.TicketEntity;

public class TicketRepositoryTest extends MockTest {

  @InjectMocks
  private TicketRepositoryImpl ticketRepository;

  @Mock
  private JsonReader jsonReader;

  private TicketEntity ticketEntity;

  @Before
  public void setup() throws IOException {
    ticketEntity = TestDataProvider.buildTiketEntity();
    Mockito.when(jsonReader.readFromFile(Mockito.any(), Mockito.any()))
        .thenReturn(List.of(ticketEntity));
    ticketRepository.init();
  }

  @Test
  public void getById_shouldReturnEmpty() {
    final Optional<TicketEntity> ticketEntityOpt = ticketRepository.getById("102");
    MatcherAssert.assertThat(ticketEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getById_shouldReturnData() {
    final Optional<TicketEntity> ticketEntityOpt =
        ticketRepository.getById(ticketEntity.getId());
    MatcherAssert.assertThat(ticketEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(ticketEntityOpt.get(), Matchers.is(ticketEntity));
  }

  @Test
  public void getByUrl_shouldReturnEmpty() {
    final Optional<TicketEntity> ticketEntityOpt = ticketRepository.getByUrl("INVALID_URL");
    MatcherAssert.assertThat(ticketEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getByUrl_shouldReturnData() {
    final Optional<TicketEntity> ticketEntityOpt =
        ticketRepository.getByUrl(ticketEntity.getUrl());
    MatcherAssert.assertThat(ticketEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(ticketEntityOpt.get(), Matchers.is(ticketEntity));
  }

  @Test
  public void getByExternalId_shouldReturnEmpty() {
    final Optional<TicketEntity> ticketEntityOpt
        = ticketRepository.getByExternalId("INVALID_EXTERNAL_ID");
    MatcherAssert.assertThat(ticketEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getByExternalId_shouldReturnData() {
    final Optional<TicketEntity> ticketEntityOpt =
        ticketRepository.getByExternalId(ticketEntity.getExternalId());
    MatcherAssert.assertThat(ticketEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(ticketEntityOpt.get(), Matchers.is(ticketEntity));
  }

  @Test
  public void getByType_shouldReturnEmpty() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByType("INVALID_TYPE");
    MatcherAssert.assertThat(ticketEntities, Matchers.empty());
  }

  @Test
  public void getByType_shouldReturnData() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByType(ticketEntity.getType());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getBySubject_shouldReturnEmpty() {
    final List<TicketEntity> ticketEntities = ticketRepository.getBySubject("INVALID_SUBJECT");
    MatcherAssert.assertThat(ticketEntities, Matchers.empty());
  }

  @Test
  public void getBySubject_shouldReturnData() {
    final List<TicketEntity> ticketEntities = ticketRepository.getBySubject(ticketEntity.getSubject());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByDescription_shouldReturnEmpty() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByDescription("INVALID_Description");
    MatcherAssert.assertThat(ticketEntities, Matchers.empty());
  }

  @Test
  public void getByDescription_shouldReturnData() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByDescription(ticketEntity.getDescription());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }


  @Test
  public void getByPriority_shouldReturnEmpty() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByPriority("INVALID_PRIORITY");
    MatcherAssert.assertThat(ticketEntities, Matchers.empty());
  }

  @Test
  public void getByPriority_shouldReturnData() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByPriority(ticketEntity.getPriority());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByStatus_shouldReturnEmpty() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByStatus("INVALID_STATUS");
    MatcherAssert.assertThat(ticketEntities, Matchers.empty());
  }

  @Test
  public void getByStatus_shouldReturnData() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByStatus(ticketEntity.getStatus());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getBySubmitterId_shouldReturnEmpty() {
    final List<TicketEntity> ticketEntities = ticketRepository.getBySubmitterId(0L);
    MatcherAssert.assertThat(ticketEntities, Matchers.empty());
  }

  @Test
  public void getBySubmitterId_shouldReturnData() {
    final List<TicketEntity> ticketEntities = ticketRepository.getBySubmitterId(ticketEntity.getSubmitterId());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByAssigneeId_shouldReturnEmpty() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByAssigneeId(0L);
    MatcherAssert.assertThat(ticketEntities, Matchers.empty());
  }

  @Test
  public void getByAssigneeId_shouldReturnData() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByAssigneeId(ticketEntity.getAssigneeId());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByOrganizationId_shouldReturnEmpty() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByOrganizationId(0L);
    MatcherAssert.assertThat(ticketEntities, Matchers.empty());
  }

  @Test
  public void getByOrganizationId_shouldReturnData() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByOrganizationId(ticketEntity.getOrganizationId());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByHasIncidents_shouldReturnEmpty() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByHasIncidents(true);
    MatcherAssert.assertThat(ticketEntities, Matchers.empty());
  }

  @Test
  public void getByHasIncidents_shouldReturnData() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByHasIncidents(ticketEntity.getHasIncidents());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }

  @Test
  public void getByVia_shouldReturnEmpty() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByVia("INVALID_VIA");
    MatcherAssert.assertThat(ticketEntities, Matchers.empty());
  }

  @Test
  public void getByVia_shouldReturnData() {
    final List<TicketEntity> ticketEntities = ticketRepository.getByVia(ticketEntity.getVia());
    MatcherAssert.assertThat(ticketEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(ticketEntities.get(0), Matchers.is(ticketEntity));
  }
}