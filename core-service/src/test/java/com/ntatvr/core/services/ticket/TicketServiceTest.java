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
    MatcherAssert.assertThat(searchableFields, Matchers.containsInAnyOrder(ID_FIELD, CREATED_AT_FIELD, EXTERNAL_ID_FIELD,
        TAGS_FIELD, URL_FIELD, TYPE_FIELD, SUBJECT_FIELD, DESCRIPTION_FIELD, PRIORITY_FIELD, STATUS_FIELD,
        SUBMITTER_ID_FIELD, ORGANIZATION_ID_FIELD, ASSIGNEE_ID_FIELD, HAS_INCIDENTS_FIELD, DUE_AT_FIELD, VIA_FIELD));
  }
}