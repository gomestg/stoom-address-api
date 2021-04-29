package com.stoom.address.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stoom.address.controller.dto.AddressDTO;
import com.stoom.address.exception.EntityNotFoundException;
import com.stoom.address.model.Address;
import com.stoom.address.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1")
@Api(value = "API Address data access", tags = { "Address" })
public class AddressController {

	@Autowired
	AddressService addressService;

	final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

	@Transactional
	@ApiOperation(value = "Save new Address", tags = { "Address" })
	@PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AddressDTO> create(@RequestBody AddressDTO addressDTO) {
		if (!addressService.exists(addressDTO.getId())) {
			LOGGER.info("The address with id: " + addressDTO.getId() + ", already exists.");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		LOGGER.info("creating new Address: {}", addressDTO);
		Address address = addressService.save(addressDTO);
		return new ResponseEntity<>(new AddressDTO(address), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Find address by id", tags = { "Address" })
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AddressDTO> read(@PathVariable Long id) {
		LOGGER.info("getting address with id: {}", id);
		Address address = addressService.findByAddress(id);
		return new ResponseEntity<>(new AddressDTO(address), HttpStatus.OK);
	}

	@Transactional
	@ApiOperation(value = "Update address", tags = { "Address" })
	@PatchMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AddressDTO> update(@PathVariable Long id, @RequestBody AddressDTO addressDTO)
			throws MissingServletRequestParameterException {
		LOGGER.info("updating address: {}", addressDTO);
		addressDTO.setId(id);
		addressService.update(addressDTO);
		return new ResponseEntity<>(addressDTO, HttpStatus.OK);
	}
	
	@Transactional
	@ApiOperation(value = "Delete address", tags = { "Address" })
	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void delete(@PathVariable Long id) {
		LOGGER.info("deleting address with id: {}", id);
		Address address = addressService.findByAddress(id);
		if (address == null) {
			LOGGER.debug("Unable to delete. Address with id {} not found", id);
			throw new EntityNotFoundException(Address.class, "Id", id);
		}
		addressService.delete(id);
	}

}
