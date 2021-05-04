package com.ntatvr.core.services.organization;

import static com.ntatvr.domain.entities.BaseEntity.CREATED_AT_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.EXTERNAL_ID_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.ID_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.TAGS_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.URL_FIELD;
import static com.ntatvr.domain.entities.OrganizationEntity.DETAILS_FIELD;
import static com.ntatvr.domain.entities.OrganizationEntity.DOMAIN_NAMES_FIELD;
import static com.ntatvr.domain.entities.OrganizationEntity.NAME_FIELD;
import static com.ntatvr.domain.entities.OrganizationEntity.SHARED_TICKETS_FIELD;

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
import com.ntatvr.core.repositories.organization.OrganizationRepository;
import com.ntatvr.domain.entities.OrganizationEntity;

public class OrganizationServiceTest extends MockTest {

  @InjectMocks
  private OrganizationServiceImpl organizationService;

  @Mock
  private OrganizationRepository organizationRepository;

  private OrganizationEntity organizationEntity;

  @Before
  public void setup() {
    organizationEntity = TestDataProvider.buildOrganizationEntity();
  }

  @Test
  public void searchById_shouldReturnEmpty() {
    Mockito.when(organizationRepository.getById(Mockito.any())).thenReturn(Optional.empty());
    final Optional<OrganizationEntity> organizationEntityOpt =
        organizationService.searchById(organizationEntity.getId());
    MatcherAssert.assertThat(organizationEntityOpt, Matchers.is(Optional.empty()));
  }

  @Test
  public void searchById_shouldReturnData() {
    Mockito.when(organizationRepository.getById(Mockito.any())).thenReturn(Optional.of(organizationEntity));
    final Optional<OrganizationEntity> organizationEntityOpt =
        organizationService.searchById(organizationEntity.getId());
    MatcherAssert.assertThat(organizationEntityOpt, Matchers.not(Optional.empty()));
    MatcherAssert.assertThat(organizationEntityOpt.get(), Matchers.is(organizationEntity));
  }

  @Test
  public void getSearchableFields_shouldReturnData() {
    final List<String> searchableFields = organizationService.getSearchableFields();
    MatcherAssert.assertThat(searchableFields, Matchers.hasSize(9));
    MatcherAssert.assertThat(searchableFields, Matchers.containsInAnyOrder(ID_FIELD, CREATED_AT_FIELD,
        EXTERNAL_ID_FIELD, NAME_FIELD, DOMAIN_NAMES_FIELD, DETAILS_FIELD, SHARED_TICKETS_FIELD, URL_FIELD, TAGS_FIELD));
  }

  @Test
  public void getById_shouldReturnEmpty() {
    Mockito.when(organizationRepository.getById(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(organizationService.searchByField(ID_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getById_shouldReturnData() {
    Mockito.when(organizationRepository.getById(Mockito.any())).thenReturn(Optional.of(organizationEntity));
    final List<OrganizationEntity> organizationEntities = organizationService.searchByField(ID_FIELD,
        organizationEntity.getId());
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test
  public void getByUrl_shouldReturnEmpty() {
    Mockito.when(organizationRepository.getByUrl(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(organizationService.searchByField(URL_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByUrl_shouldReturnData() {
    Mockito.when(organizationRepository.getByUrl(Mockito.any())).thenReturn(Optional.of(organizationEntity));
    final List<OrganizationEntity> organizationEntities = organizationService.searchByField(URL_FIELD,
        organizationEntity.getUrl());
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test
  public void getByExternalId_shouldReturnEmpty() {
    Mockito.when(organizationRepository.getByExternalId(Mockito.any())).thenReturn(Optional.empty());
    MatcherAssert.assertThat(organizationService.searchByField(URL_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByExternalId_shouldReturnData() {
    Mockito.when(organizationRepository.getByExternalId(Mockito.any())).thenReturn(Optional.of(organizationEntity));
    final List<OrganizationEntity> organizationEntities = organizationService.searchByField(EXTERNAL_ID_FIELD,
        organizationEntity.getExternalId());
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test
  public void getByName_shouldReturnEmpty() {
    Mockito.when(organizationRepository.getByName(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(organizationService.searchByField(URL_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByName_shouldReturnData() {
    Mockito.when(organizationRepository.getByName(Mockito.any())).thenReturn(List.of(organizationEntity));
    final List<OrganizationEntity> organizationEntities = organizationService.searchByField(NAME_FIELD,
        organizationEntity.getName());
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test
  public void getByDetails_shouldReturnEmpty() {
    Mockito.when(organizationRepository.getByDetails(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(organizationService.searchByField(URL_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByDetails_shouldReturnData() {
    Mockito.when(organizationRepository.getByDetails(Mockito.any())).thenReturn(List.of(organizationEntity));
    final List<OrganizationEntity> organizationEntities = organizationService.searchByField(DETAILS_FIELD,
        organizationEntity.getDetails());
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }


  @Test
  public void getByDomainName_shouldReturnEmpty() {
    Mockito.when(organizationRepository.getByDomainName(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(organizationService.searchByField(URL_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByDomainName_shouldReturnData() {
    Mockito.when(organizationRepository.getByDomainName(Mockito.any())).thenReturn(List.of(organizationEntity));
    final List<OrganizationEntity> organizationEntities = organizationService.searchByField(DOMAIN_NAMES_FIELD,
        organizationEntity.getDomainNames().get(0));
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test
  public void getBySharedTickets_shouldReturnEmpty() {
    Mockito.when(organizationRepository.getBySharedTickets(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(organizationService.searchByField(URL_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getBySharedTickets_shouldReturnData() {
    Mockito.when(organizationRepository.getBySharedTickets(Mockito.any())).thenReturn(List.of(organizationEntity));
    final List<OrganizationEntity> organizationEntities = organizationService.searchByField(SHARED_TICKETS_FIELD,
        organizationEntity.getSharedTickets().toString());
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test
  public void getByTag_shouldReturnEmpty() {
    Mockito.when(organizationRepository.getByTag(Mockito.any())).thenReturn(List.of());
    MatcherAssert.assertThat(organizationService.searchByField(URL_FIELD, "INVALID"), Matchers.empty());
  }

  @Test
  public void getByTag_shouldReturnData() {
    Mockito.when(organizationRepository.getByTag(Mockito.any())).thenReturn(List.of(organizationEntity));
    final List<OrganizationEntity> organizationEntities = organizationService.searchByField(TAGS_FIELD,
        organizationEntity.getTags().get(0));
    MatcherAssert.assertThat(organizationEntities, Matchers.hasSize(1));
    MatcherAssert.assertThat(organizationEntities.get(0), Matchers.is(organizationEntity));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getByUnsupportedField_shouldThrowException() {
    organizationService.searchByField("unsupported-field", "123");
  }
}