package com.stoom.address.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.stoom.address.controller.dto.AddressDTO;
import com.stoom.address.exception.EntityNotFoundException;
import com.stoom.address.model.Address;
import com.stoom.address.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	public boolean exists(Long id) {
		return addressRepository.findById(id).isPresent();
	}

	public Address save(AddressDTO dto) {
		
		if (dto.getLatitude() == null || dto.getLatitude().equals("") || dto.getLongitude() == null || dto.getLongitude().equals("")) {
			String addressComplete = dto.getStreetName().concat(", ")
					.concat(dto.getNumber()).concat(" - ")
					.concat(dto.getNeighbourhood()).concat(", ")
					.concat(dto.getCity()).concat(" - ")
					.concat(dto.getState()).concat(", ")
					.concat(dto.getCountry());
			try {
				GeoApiContext context = new GeoApiContext.Builder()
						.apiKey("AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw")
						.build();

				GeocodingResult[] results = GeocodingApi.geocode(context, addressComplete).await();
				LatLng location = results[0].geometry.location;
				dto.setLatitude(location.lat);
				dto.setLongitude(location.lng);
			} catch (ApiException | InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return addressRepository.save(dto.toAddress());
	}

	public Address findByAddress(Long id) {
		Optional<Address> address = addressRepository.findById(id);
		if (!address.isPresent()) {
			throw new EntityNotFoundException(Address.class, "Id", id);
		}
		return address.get();
	}

	public void update(AddressDTO dto) throws MissingServletRequestParameterException {
		if (dto.getId() == null) {
			throw new MissingServletRequestParameterException("id", "int");
		}
		addressRepository.save(dto.toAddress());
	}

	public void delete(Long id) {
		Optional<Address> optional = addressRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(Address.class, "id", id);
		}
		addressRepository.deleteById(id);
	}
}
