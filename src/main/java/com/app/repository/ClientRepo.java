package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Client;

public interface ClientRepo extends JpaRepository<Client, Integer>{

}
