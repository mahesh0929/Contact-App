package com.example.contacts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.contacts.model.Contacts;
import com.example.contacts.service.ContactsService;

@Controller
public class ContactsController {

	@Autowired
	private ContactsService contactsService;
	
	// display list of Contacts
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);		
	}
	
	@GetMapping("/showNewContactsForm")
	public String showNewContactsForm(Model model) {
		// create model attribute to bind form data
		Contacts contacts = new Contacts();
		model.addAttribute("contacts", contacts);
		return "new_contacts";
	}
	
	@PostMapping("/saveContacts")
	public String saveContacts(@ModelAttribute("contacts") Contacts contacts) {
		// save contacts to database
		contactsService.saveContact(contacts);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get contacts from the service
		Contacts contacts = contactsService.getContactsById(id);
		
		// set contacts as a model attribute to pre-populate the form
		model.addAttribute("contacts", contacts);
		return "update_contacts";
	}
	
	@GetMapping("/deleteContacts/{id}")
	public String deleteContacts(@PathVariable (value = "id") long id) {
		
		// call delete contacts method 
		this.contactsService.deleteContactsById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Contacts> page = contactsService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Contacts> listContacts = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listContacts", listContacts);
		
		Contacts contacts = new Contacts();
		model.addAttribute("contacts", contacts);
		return "index";
	}
}
