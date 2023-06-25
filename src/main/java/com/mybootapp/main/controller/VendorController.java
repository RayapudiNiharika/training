package com.mybootapp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.model.Vendor;
import com.mybootapp.main.service.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
@Autowired
private VendorService vendorservice;

@PostMapping("/add")
public Vendor addvendor(@RequestBody Vendor vendor)
{
	vendor=vendorservice.insert(vendor);
	return vendor;
}


}
