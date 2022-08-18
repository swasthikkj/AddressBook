package com.bridgelabz.addressbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.addressbook.model.AddressBookModel;
@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookModel, Long>{
	Optional<AddressBookModel> findByEmailId(String email);
}
