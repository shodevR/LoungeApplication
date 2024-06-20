package com.app.services;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.exceptions.PackageException;
import com.app.model.PackageDetails;
import com.app.repository.PackageRepository;

@Service
public class PackageImpl implements PackageServices {

	@Autowired
	private PackageRepository packageRepository;

	@Override
	public PackageDetails updatePackageFlag(Integer packageId) throws PackageException{
        // Retrieve the package details by packageId
        Optional<PackageDetails> optionalPackage = packageRepository.findById(packageId);

        // Check if the package details exist
        if (optionalPackage.isPresent()) {
            PackageDetails packageDetails = optionalPackage.get();
            if (packageDetails.getFlag()) {
				packageDetails.setFlag(false);
			}else {
				packageDetails.setFlag(true);
			}


            // Update the amount
           
            
            // Save the updated entity back to the repository
            PackageDetails packageDetails1 = packageRepository.save(packageDetails);
			return  packageDetails1;
        } else {
            // Handle the case when the package details with the given ID are not found
            // You can throw an exception or handle it according to your application's logic
        	throw new PackageException("Package not found");
        }

	}

	@Override
	public PackageDetails addPackageDetails(PackageDetails pd) throws PackageException {
		
		
		pd.setFlag(true);
		/*pd.setImage("/images/WalkIn/one.png");*/
		
		PackageDetails pacaDetails = packageRepository.save(pd);
		
		return pacaDetails;
		
	}

	@Override
	public PackageDetails updatePackageDetails(PackageDetails pd) throws PackageException {
		Optional<PackageDetails>optional = packageRepository.findById(pd.getPackageId());
		if(optional.isPresent()) {
			return packageRepository.save(pd);
			
		}else {
			throw new PackageException("Package not found");
		}
		
	}

	@Override
	public PackageDetails removePackageDetails(Integer packageId) throws PackageException {
		PackageDetails existingPackageDetails = packageRepository.findById(packageId).orElseThrow(()->new PackageException("Package not found"));
		packageRepository.delete(existingPackageDetails);
		return existingPackageDetails;
	}

	@Override
	public List<PackageDetails> viewAllPackageDetails() throws PackageException {
		List<PackageDetails> optDetails = packageRepository.findAll();
		if (optDetails.size()>0) {
			return optDetails;
		}else {
			throw new PackageException("No Packages found");
		}
	}

	@Override
	public PackageDetails getPackageDetailsbyId(Integer id) throws PackageException {
		// TODO Auto-generated method stub
		PackageDetails packageDetails = packageRepository.findById(id).orElseThrow(()->new PackageException("Package not found"));
		
		return packageDetails;
	}
	public List<PackageDetails> getPackagesByPartnerId(Integer partnerId) throws PackageException {
		List<PackageDetails> packages = packageRepository.findByPartnerId(partnerId);
		if (packages.isEmpty()) {
			throw new PackageException("No packages found for this partner");
		}
		return packages;
	}

	

}
