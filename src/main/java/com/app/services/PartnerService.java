package com.app.services;

import java.util.List;


import com.app.exceptions.PartnerException;

import com.app.model.Partners;

public interface PartnerService {
	public List<Partners>viewAllPartners() throws PartnerException;
	public Partners addPartner(Partners partners) throws PartnerException;
	public Partners removePartner(Integer partnerId) throws PartnerException;
	public Partners getDetails(Integer partnerId) throws PartnerException;
}
