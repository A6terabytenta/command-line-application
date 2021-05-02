package com.ntatvr.domain.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationEntity extends BaseEntity {

  public static final String COLLECTION_NAME = "Organizations";
  public static final String NAME_FIELD = "name";
  public static final String DOMAIN_NAMES_FIELD = "domain_names";
  public static final String DETAILS_FIELD = "details";
  public static final String SHARED_TICKETS_FIELD = "shared_tickets";

  private String name;

  @JsonProperty("domain_names")
  private List<String> domainNames;

  private String details;

  @JsonProperty("shared_tickets")
  private Boolean sharedTickets;
}
