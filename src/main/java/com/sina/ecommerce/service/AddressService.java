package com.sina.ecommerce.service;

import com.sina.ecommerce.model.Address;
import com.sina.ecommerce.repository.AddressRepository;
import org.springframework.stereotype.Service;

/**
 * @author Sina Ramezani, 7/17/2025
 */
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
