package com.olmez.myamango.model;

import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import com.olmez.myamango.model.enums.UserType;
import com.olmez.myamango.utility.StringUtility;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user")
@Data
@NoArgsConstructor
public class User extends BaseObject {

	private @NonNull String firstName;
	private @NonNull String lastName;
	private @NonNull String username;
	private @NonNull String email;
	private UserType userType = UserType.REGULAR;
	private String passwordHash;

	public User(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String email) {
		this(firstName, lastName, username, email, UserType.REGULAR);
	}

	public User(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String email,
			UserType userType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.userType = (userType != null) ? userType : UserType.REGULAR;
	}

	public String getName() {
		return StringUtility.isEmpty(firstName) ? username : firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean isAdmin() {
		return userType == UserType.ADMIN;
	}

	public String getRole() {
		return userType.getRole();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, email);
	}

}
