package com.stoom.address.controller.dto;

import javax.validation.constraints.NotBlank;

import com.stoom.address.model.Address;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
public class AddressDTO {

	@ApiModelProperty(required = true, value = "Address's ID")
	private Long id;
	@ApiModelProperty(required = true, value = "Street Name")
	@NotBlank(message = "Street Name is mandatory")
	private String streetName;
	@ApiModelProperty(required = true, value = "Address's number")
	@NotBlank(message = "Address number is mandatory")
	private String number;
	@ApiModelProperty(value = "Address's complement")
	private String complement;
	@ApiModelProperty(required = true, value = "Address's Neighbourhood")
	@NotBlank(message = "Neighbourhood is mandatory")
	private String neighbourhood;
	@ApiModelProperty(required = true, value = "Address's City ")
	@NotBlank(message = "City is mandatory")
	private String city;
	@ApiModelProperty(required = true, value = "Address's State ")
	@NotBlank(message = "State is mandatory")
	private String state;
	@ApiModelProperty(required = true, value = "Address's Country ")
	@NotBlank(message = "Country is mandatory")
	private String country;
	@ApiModelProperty(required = true, value = "Address's Zip Code")
	@NotBlank(message = "Zip Code is mandatory")
	private String zipcode;
	@ApiModelProperty(value = "Address's latitude")
	private Double latitude;
	@ApiModelProperty(value = "Address's longitude")
	private Double longitude;

	public AddressDTO(Address address) {
		this.id = address.getId();
		this.streetName = address.getStreetName();
		this.number = address.getNumber();
		this.complement = address.getComplement();
		this.neighbourhood = address.getNeighbourhood();
		this.city = address.getCity();
		this.state = address.getState();
		this.country = address.getCountry();
		this.zipcode = address.getZipcode();
		this.latitude = address.getLatitude();
		this.longitude = address.getLongitude();
	}

	public Address toAddress() {
		Address address = new Address();
		address.setId(this.id);
		address.setStreetName(this.streetName);
		address.setNumber(this.number);
		address.setComplement(this.complement);
		address.setNeighbourhood(this.neighbourhood);
		address.setCity(this.city);
		address.setState(this.state);
		address.setCountry(this.country);
		address.setZipcode(this.zipcode);
		address.setLatitude(this.latitude);
		address.setLongitude(this.longitude);
		return address;
	}

}
