package com.sourav.learning.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Table(name = "AUTHOR")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;

	@Column(name = "FIRST_NAME")
	private final String firstName;

	@Column(name = "LAST_NAME")
	private final String lastName;

	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

}
