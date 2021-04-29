package com.stoom.address.model;

import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@lombok.Data
@Entity
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ID", allocationSize = 1)
@Table(name = "ADDRESS")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@Column(nullable = false)
	private Long id;
	@Column(nullable = false)
	private String streetName;
	@Column(nullable = false)
	private String number;
	private String complement;
	@Column(nullable = false)
	private String neighbourhood;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private String country;
	@Column(nullable = false)
	private String zipcode;
	private Double latitude;
	private Double longitude;
}
