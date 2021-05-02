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
public class UserEntity extends BaseEntity {

  public static final String COLLECTION_NAME = "Users";
  public static final String NAME_FIELD = "name";
  public static final String ALIAS_FIELD = "alias";
  public static final String ACTIVE_FIELD = "active";
  public static final String VERIFIED_FIELD = "verified";
  public static final String SHARED_FIELD = "shared";
  public static final String LOCALE_FIELD = "locale";
  public static final String TIMEZONE_FIELD = "timezone";
  public static final String LAST_LOGIN_AT_FIELD = "last_login_at";
  public static final String EMAIL_FIELD = "email";
  public static final String PHONE_FIELD = "phone";
  public static final String SIGNATURE_FIELD = "signature";
  public static final String ORGANIZATION_ID_FIELD = "organization_id";
  public static final String ORGANIZATION_NAME_FIELD = "organization_name";
  public static final String SUSPENDED_FIELD = "suspended";
  public static final String ROLE_FIELD = "role";
  public static final String TICKET_FIELD = "ticket-";

  private String name;

  private String alias;

  private Boolean active;

  private Boolean verified;

  private Boolean shared;

  private String locale;

  private String timezone;

  @JsonProperty("last_login_at")
  private String lastLoginAt;

  private String email;

  private String phone;

  private String signature;

  @JsonProperty("organization_id")
  private Long organizationId;

  private Boolean suspended;

  private String role;
}
