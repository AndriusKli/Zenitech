package com.example.andriuskli.repository;

import com.example.andriuskli.entity.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnershipRepository extends JpaRepository<Ownership, Long> {
}
