package com.udt.udt_ecommerce.core.repository.agency;

import com.udt.udt_ecommerce.core.entity.agency.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {}
