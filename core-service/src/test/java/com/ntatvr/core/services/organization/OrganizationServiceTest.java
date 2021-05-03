package com.ntatvr.core.services.organization;

import static com.ntatvr.domain.entities.BaseEntity.CREATED_AT_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.EXTERNAL_ID_FIELD;
import static com.ntatvr.domain.entities.BaseEntity.ID_FIELD;
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
    MatcherAssert.assertThat(searchableFields, Matchers.hasSize(7));
    MatcherAssert.assertThat(searchableFields, Matchers.containsInAnyOrder(ID_FIELD, CREATED_AT_FIELD, EXTERNAL_ID_FIELD,
        NAME_FIELD, DOMAIN_NAMES_FIELD, DETAILS_FIELD, SHARED_TICKETS_FIELD));
  }
}