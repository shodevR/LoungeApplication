package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.PartnerException;
import com.app.model.Partners;
import com.app.repository.PartnerRepo;

@Service
public class PartnerServiceImpl implements PartnerService{

	@Autowired
	private PartnerRepo partnerRepo;
	@Override
	public List<Partners> viewAllPartners() throws PartnerException {
			List<Partners>list = partnerRepo.findAll();
		
		if (list.size()>0) {
			return list;
		}else {
			throw new PartnerException("No Partners Found");
		}	
		
		
	}

	@Override
	public Partners addPartner(Partners partners) throws PartnerException {
		Partners partners2 = partnerRepo.save(partners);
		return partners2;
	}

	@Override
	public Partners removePartner(Integer partnerId) throws PartnerException {
		Partners partners = partnerRepo.findById(partnerId).orElseThrow(()-> new PartnerException("Partner does not Exist with id: "+ partnerId));
		
		partnerRepo.delete(partners);
		return partners;
	}

	@Override
	public Partners getDetails(Integer partnerId) throws PartnerException {
		
		Partners partnerDetailsPartners = partnerRepo.findById(partnerId).orElseThrow(()-> new PartnerException("No partner found with the gicen id"));
		return partnerDetailsPartners;
	}

}
