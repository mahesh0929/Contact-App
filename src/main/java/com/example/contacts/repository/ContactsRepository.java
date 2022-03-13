package com.example.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contacts.model.Contacts;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long>{

}
