package com.olmez.myamango.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseObject {

	private String name;
	private String email;
	private LocalDate dob;
	private boolean salaried;

	public Employee(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, email);
	}

}
