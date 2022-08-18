package com.bridgelabz.addressbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.addressbook.dto.AddressDTO;
import com.bridgelabz.addressbook.exception.AddressBookNotFoundException;
import com.bridgelabz.addressbook.model.AddressBookModel;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import com.bridgelabz.addressbook.util.Response;
import com.bridgelabz.addressbook.util.TokenUtil;


@Service
public class AddressBookService implements IAddressBookService{
	@Autowired
	AddressBookRepository addressRepository;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	MailService mailService;

	@Override
	public AddressBookModel addAddressBook(AddressDTO addressdto) {
		AddressBookModel model = new AddressBookModel(addressdto);
		String body="Address is added succesfully with addressId "+model.getAadharNumber();
		String subject="Employee Registration Succesfull";
		mailService.send(model.getEmailId(),subject,body);
		addressRepository.save(model);
		return model;
	}

	@Override
	public AddressBookModel updateAddressBook(AddressDTO addressdto, Long id, String token) {
		Long addressId=tokenUtil.decodeToken(token);
		Optional<AddressBookModel> isAddressBookPresent = addressRepository.findById(id);
		if(isAddressBookPresent.isPresent()) {
			isAddressBookPresent.get().setFirstName(addressdto.getFirstName());
			isAddressBookPresent.get().setLastName(addressdto.getLastName());
			isAddressBookPresent.get().setMobileNumber(addressdto.getMobileNumber());
			isAddressBookPresent.get().setCity(addressdto.getCity());
			isAddressBookPresent.get().setState(addressdto.getState());
			isAddressBookPresent.get().setPincode(addressdto.getPincode());
			isAddressBookPresent.get().setEmailId(addressdto.getEmailId());
			addressRepository.save(isAddressBookPresent.get());
			return isAddressBookPresent.get();
		}
		throw new AddressBookNotFoundException(400,"address book not present");
	}
	
	@Override
	public List<AddressBookModel> getAddressBookData(Long id, String token) {
		Long addressId=tokenUtil.decodeToken(token);
		Optional<AddressBookModel> isAddressBookPresent = addressRepository.findById(id);
		if (isAddressBookPresent.isPresent()) {
			List<AddressBookModel> getAddressBookData = addressRepository.findAll();
			if (getAddressBookData.size()>0) {
				return getAddressBookData;
			}
			else {
				throw new AddressBookNotFoundException(400,"No Data Present");
			}
		}
		throw new AddressBookNotFoundException(400, "AddressBook not found");
	}

	@Override
	public AddressBookModel deleteAddressBookData(Long id, String token) {
		Long addressId=tokenUtil.decodeToken(token);
		Optional<AddressBookModel> isAddressBookPresent = addressRepository.findById(id);
		if(isAddressBookPresent.isPresent()) {
			addressRepository.delete(isAddressBookPresent.get());
			return isAddressBookPresent.get();
		}
		throw new AddressBookNotFoundException(400, "AddressBook not found");
	}

	@Override
	public Response login(String emailId, String password) {
		Optional<AddressBookModel> isEmailPresent = addressRepository.findByEmailId(emailId);
        if(isEmailPresent.isPresent()){
            if(isEmailPresent.get().getPassword().equals(password)){
                String token = tokenUtil.createToken(isEmailPresent.get().getAadharNumber());
                return new Response(500,"login succesfull",token);
            }
            throw new AddressBookNotFoundException(500, "Invalid credentials");
        }
        throw new AddressBookNotFoundException(500, "User not found");
	}
}



