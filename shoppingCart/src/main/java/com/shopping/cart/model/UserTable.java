package com.shopping.cart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
/*
 * User Entity Class for all the registered Users
 *
 */
@SuppressWarnings("unused")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="USER_TABLE")
public class UserTable {

	@Id
	@Column(name="USER_NME")
	private String userNme;

	@Column(name="USER_ROLE")
	private String userRole;

	public String getUserName() {
		return userNme;
	}

	public void setUserName(String userNme) {
		this.userNme = userNme;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	
}
