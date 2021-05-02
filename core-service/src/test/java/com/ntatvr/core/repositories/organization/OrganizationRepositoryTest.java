package com.ntatvr.core.repositories.organization;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ntatvr.core.MockTest;
import com.ntatvr.core.utils.JsonReader;

public class OrganizationRepositoryTest extends MockTest {

  @InjectMocks
  private OrganizationRepositoryImpl organizationRepository;

  @Mock
  private JsonReader jsonReader;

  @Test
  public void test() {
    MatcherAssert.assertThat(1, Matchers.is(1));
  }
}