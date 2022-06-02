package com.thoughtmechanix.organization.services;

import com.thoughtmechanix.organization.config.ServiceConfig;
import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository orgRepository;
    
    @Autowired
    ServiceConfig config;

    public Organization getOrg(String organizationId) {
    	System.out.println("Dentro de OrganizationService Recurso: " + organizationId);
    	InetAddress ip;
    	try {
    		ip = InetAddress.getLocalHost();
    		System.out.println("La IP del contenedro es : " + ip);
    	} catch (UnknownHostException e) {	 
            e.printStackTrace();
        }
    	
        return orgRepository.findById(organizationId).withContactName(config.getExampleProperty());
    }

    public void saveOrg(Organization org){
        org.setId( UUID.randomUUID().toString());

        orgRepository.save(org);

    }

    public void updateOrg(Organization org){
        orgRepository.save(org);
    }

    public void deleteOrg(Organization org){
        orgRepository.delete( org.getId());
    }
}
