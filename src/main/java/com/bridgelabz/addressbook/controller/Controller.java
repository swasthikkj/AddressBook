package com.bridgelabz.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.addressbook.dto.AddressDTO;
import com.bridgelabz.addressbook.model.AddressBookModel;
import com.bridgelabz.addressbook.service.IAddressBookService;

@RestController
@RequestMapping("/addressbook")
public class Controller {
	@Autowired
	IAddressBookService addressBookService;
	
	@PostMapping("/addaddressbook")
	public AddressBookModel addAddressBook(@RequestBody AddressDTO addressdto) {
		return addressBookService.addAddressBook(addressdto);
	}
	
	@PutMapping("updateaddressbook/{id}")
	public AddressBookModel updateAddressBook(@RequestBody AddressDTO addressdto, @PathVariable Long id) {
		return addressBookService.updateAddressBook(addressdto, id);
	}
	
	@GetMapping("/getAdressBookdata/{id}")
    public List<AddressBookModel> getAllAddressBooks(@PathVariable Long id) {
        return addressBookService.getAddressBookData(id);
    }
	
	@DeleteMapping("deleteAddressbook/{id}")
	public AddressBookModel deleteAddressBookData(@PathVariable Long id) {
		return addressBookService.deleteAddressBookData(id);
	}
}
