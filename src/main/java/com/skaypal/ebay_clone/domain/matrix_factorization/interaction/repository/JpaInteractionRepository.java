package com.skaypal.ebay_clone.domain.matrix_factorization.interaction.repository;

import com.skaypal.ebay_clone.domain.matrix_factorization.interaction.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInteractionRepository extends JpaRepository<Interaction, Integer> { }
