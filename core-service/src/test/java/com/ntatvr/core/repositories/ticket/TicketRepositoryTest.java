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

}