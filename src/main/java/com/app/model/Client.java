package com.app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer clientId;
private String clientType;
private String clientName;
private String contactPersonName;
private String phone;
private String email;
private String membershipId;
private String discount;
private String otherId;



}
