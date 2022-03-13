package com.example.contacts.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.contacts.model.Contacts;

public interface ContactsService {
	List<Contacts> getAllContacts();
	void saveContact(Contacts contacts);
	Contacts getContactsById(long id);
	void deleteContactsById(long id);
	Page<Contacts> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
