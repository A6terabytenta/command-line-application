package com.ntatvr.core.repositories.organization;

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
import com.ntatvr.domain.entities.OrganizationEntity;

public class OrganizationRepositoryTest extends MockTest {

  @InjectMocks
  private OrganizationRepositoryImpl organizationRepository;

  @Mock
  private JsonReader jsonReader;

  private OrganizationEntity organizationEntity;

  @Before
  public void setup() throws IOException {
    organizationEntity = TestDataProvider.buildOrganizationEntity();
    Mockito.when(jsonReader.readFromFile(Mockito.any(), Mockito.any()))
        .thenReturn(List.of(organizationEntity));
    organizationRepository.init();
  }

  @Test
  public void getById_shouldReturnEmpty() {
    final Optional<OrganizationEntity> organizationEntityOpt = organizationRepository.getById("102");
    MatcherAssert.assertThat(organizationEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getById_shouldReturnData() {
    final Optional<OrganizationEntity> organizationEntityOpt =
        organizationRepository.getById(organizationEntity.getId());
    MatcherAssert.assertThat(organizationEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(organizationEntityOpt.get(), Matchers.is(organizationEntity));
  }

  @Test
  public void getByUrl_shouldReturnEmpty() {
    final Optional<OrganizationEntity> organizationEntityOpt = organizationRepository.getByUrl("INVALID_URL");
    MatcherAssert.assertThat(organizationEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getByUrl_shouldReturnData() {
    final Optional<OrganizationEntity> organizationEntityOpt =
        organizationRepository.getByUrl(organizationEntity.getUrl());
    MatcherAssert.assertThat(organizationEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(organizationEntityOpt.get(), Matchers.is(organizationEntity));
  }

  @Test
  public void getByExternalId_shouldReturnEmpty() {
    final Optional<OrganizationEntity> organizationEntityOpt
        = organizationRepository.getByExternalId("INVALID_EXTERNAL_ID");
    MatcherAssert.assertThat(organizationEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void getByExternalId_shouldReturnData() {
    final Optional<OrganizationEntity> organizationEntityOpt =
        organizationRepository.getByExternalId(organizationEntity.getExternalId());
    MatcherAssert.assertThat(organizationEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(organizationEntityOpt.get(), Matchers.is(organizationEntity));
  }

  @Test
  public void getByName_shouldReturnEmpty() {
    final List<OrganizationEntity> organizationEntities
        = organizationRepository.getByName("INVALID_NAME");
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(0));
  }

  @Test
  public void getByName_shouldReturnData() {
    final List<OrganizationEntity> organizationEntities =
        organizationRepository.getByName(organizationEntity.getName());
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test
  public void getByDetails_shouldReturnEmpty() {
    final List<OrganizationEntity> organizationEntities
        = organizationRepository.getByDetails("INVALID_DETAILS");
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(0));
  }

  @Test
  public void getByDetails_shouldReturnData() {
    final List<OrganizationEntity> organizationEntities =
        organizationRepository.getByDetails(organizationEntity.getDetails());
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test
  public void getByDomainName_shouldReturnEmpty() {
    final List<OrganizationEntity> organizationEntities
        = organizationRepository.getByDomainName("INVALID_DOMAIN");
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(0));
  }

  @Test
  public void getByDomainName_shouldReturnData() {
    final List<OrganizationEntity> organizationEntities =
        organizationRepository.getByDomainName(organizationEntity.getDomainNames().get(0));
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test
  public void getBySharedTickets_shouldReturnEmpty() {
    final List<OrganizationEntity> organizationEntities
        = organizationRepository.getBySharedTickets(false);
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(0));
  }

  @Test
  public void getBySharedTickets_shouldReturnData() {
    final List<OrganizationEntity> organizationEntities =
        organizationRepository.getBySharedTickets(organizationEntity.getSharedTickets());
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test
  public void getByTag_shouldReturnEmpty() {
    final List<OrganizationEntity> organizationEntities
        = organizationRepository.getByTag("INVALID_TAG");
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(0));
  }

  @Test
  public void getByTag_shouldReturnData() {
    final List<OrganizationEntity> organizationEntities =
        organizationRepository.getByTag(organizationEntity.getTags().get(0));
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }
}