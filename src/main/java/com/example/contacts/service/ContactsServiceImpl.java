package com.example.contacts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.contacts.model.Contacts;
import com.example.contacts.repository.ContactsRepository;

@Service
public class ContactsServiceImpl implements ContactsService {

	@Autowired
	private ContactsRepository contactsRepository;

	@Override
	public List<Contacts> getAllContacts() {
		return contactsRepository.findAll();
	}

	@Override
	public void saveContact(Contacts contacts) {
		this.contactsRepository.save(contacts);
	}

	@Override
	public Contacts getContactsById(long id) {
		Optional<Contacts> optional = contactsRepository.findById(id);
		Contacts contacts = null;
		if (optional.isPresent()) {
			contacts = optional.get();
		} else {
			throw new RuntimeException(" contacts not found for id :: " + id);
		}
		return contacts;
	}

	@Override
	public void deleteContactsById(long id) {
		this.contactsRepository.deleteById(id);
	}

	@Override
	public Page<Contacts> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.contactsRepository.findAll(pageable);
	}
}
