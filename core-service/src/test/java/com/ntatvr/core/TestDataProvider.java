package com.ntatvr.core;

import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.ntatvr.domain.entities.OrganizationEntity;
import com.ntatvr.domain.entities.TicketEntity;
import com.ntatvr.domain.entities.UserEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataProvider {

  public static UserEntity buildUserEntity() {
    final UserEntity userEntity = new UserEntity();
    userEntity.setId("38");
    userEntity.setUrl("http://initech.tokoin.io.com/api/v2/users/1.json");
    userEntity.setExternalId("74341f74-9c79-49d5-9611-87ef9b6eb75f");
    userEntity.setName("Francisca Rasmussen");
    userEntity.setAlias("Miss Coffey");
    userEntity.setCreatedAt("2016-04-15T05:19:46 -10:00");
    userEntity.setActive(true);
    userEntity.setVerified(true);
    userEntity.setShared(true);
    userEntity.setLocale("en-AU");
    userEntity.setTimezone("Sri Lanka");
    userEntity.setLastLoginAt("2013-08-04T01:03:27 -10:00");
    userEntity.setEmail("coffeyrasmussen@flotonic.com");
    userEntity.setPhone("8335-422-718");
    userEntity.setSignature("Don't Worry Be Happy!");
    userEntity.setOrganizationId(101L);
    userEntity.setTags(List.of("Springville",
        "Sutton",
        "Hartsville/Hartley",
        "Diaperville"));
    userEntity.setSuspended(false);
    userEntity.setRole("admin");
    return userEntity;
  }

  public static TicketEntity buildTiketEntity() {
    final TicketEntity ticketEntity = new TicketEntity();
    ticketEntity.setId("436bf9b0-1147-4c0a-8439-6f79833bff5b");
    ticketEntity.setUrl("http://initech.tokoin.io.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json");
    ticketEntity.setExternalId("9210cdc9-4bee-485f-a078-35396cd74063");
    ticketEntity.setCreatedAt("2016-04-28T11:19:34 -10:00");
    ticketEntity.setType("incident");
    ticketEntity.setSubject("A Catastrophe in Korea (North)");
    ticketEntity.setDescription("Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet " +
        "et proident.");
    ticketEntity.setPriority("high");
    ticketEntity.setStatus("pending");
    ticketEntity.setSubmitterId(38L);
    ticketEntity.setAssigneeId(38L);
    ticketEntity.setOrganizationId(101L);
    ticketEntity.setTags(List.of("Ohio",
        "Pennsylvania",
        "American Samoa",
        "Northern Mariana Islands"));
    ticketEntity.setHasIncidents(false);
    ticketEntity.setDueAt("2016-07-31T02:37:50 -10:00");
    ticketEntity.setVia("web");
    return ticketEntity;
  }

  public static OrganizationEntity buildOrganizationEntity() {
    final OrganizationEntity organizationEntity = new OrganizationEntity();
    organizationEntity.setId("101");
    organizationEntity.setUrl("http://initech.tokoin.io.com/api/v2/organizations/101.json");
    organizationEntity.setExternalId("9270ed79-35eb-4a38-a46f-35725197ea8d");
    organizationEntity.setDetails("MegaCorp");
    organizationEntity.setDomainNames(List.of("kage.com",
        "ecratic.com",
        "endipin.com",
        "zentix.com"));
    organizationEntity.setName("Enthaze");
    organizationEntity.setCreatedAt("2016-05-21T11:10:28 -10:00");
    organizationEntity.setTags(List.of("Fulton",
        "West",
        "Rodriguez",
        "Farley"));
    organizationEntity.setSharedTickets(true);
    return organizationEntity;
  }
}
