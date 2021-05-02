package com.ntatvr.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity extends BaseEntity {

  public static final String COLLECTION_NAME = "Tickets";
  public static final String TYPE_FIELD = "type";
  public static final String SUBJECT_FIELD = "subject";
  public static final String DESCRIPTION_FIELD = "description";
  public static final String PRIORITY_FIELD = "priority";
  public static final String STATUS_FIELD = "status";
  public static final String SUBMITTER_ID_FIELD = "submitter_id";
  public static final String ASSIGNEE_ID_FIELD = "assignee_id";
  public static final String SUBMITTER_NAME_FIELD = "submitter_name";
  public static final String ASSIGNEE_NAME_FIELD = "assignee_name";
  public static final String ORGANIZATION_NAME_FIELD = "organization_name";
  public static final String ORGANIZATION_ID_FIELD = "organization_id";
  public static final String HAS_INCIDENTS_FIELD = "has_incidents";
  public static final String DUE_AT_FIELD = "due_at";
  public static final String VIA_FIELD = "via";

  private String type;

  private String subject;

  private String description;

  private String priority;

  private String status;

  @JsonProperty("submitter_id")
  private Long submitterId;

  @JsonProperty("assignee_id")
  private Long assigneeId;

  @JsonProperty("organization_id")
  private Long organizationId;

  @JsonProperty("has_incidents")
  private Boolean hasIncidents;

  @JsonProperty("due_at")
  private String dueAt;

  private String via;
}
