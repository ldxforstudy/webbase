package com.zls.dao.model;

import java.io.Serializable;
import java.util.Date;

public class WebUser implements Serializable {

  private static final long serialVersionUID = -607108024313030530L;

  private Long id;

  private String usercode;

  private String username;

  private String password;

  private Date create_time;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsercode() {
    return usercode;
  }

  public void setUsercode(String usercode) {
    this.usercode = usercode == null ? null : usercode.trim();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username == null ? null : username.trim();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password == null ? null : password.trim();
  }

  public Date getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Date create_time) {
    this.create_time = create_time;
  }

  @Override
  public String toString() {
    return "WebUser [id=" + id + ", usercode=" + usercode + ", username=" + username
        + ", password=" + password + ", create_time=" + create_time + "]";
  }
}
