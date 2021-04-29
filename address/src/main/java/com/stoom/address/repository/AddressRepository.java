package com.stoom.address.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.stoom.address.model.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

	Optional<Address> findById(Long id);

}
