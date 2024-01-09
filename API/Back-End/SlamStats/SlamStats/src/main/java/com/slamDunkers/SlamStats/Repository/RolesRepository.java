package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
	Optional<Roles> findByRole(String role);
}
