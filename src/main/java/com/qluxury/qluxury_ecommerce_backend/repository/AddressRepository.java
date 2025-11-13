package com.qluxury.qluxury_ecommerce_backend.repository;

import com.qluxury.qluxury_ecommerce_backend.modal.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
