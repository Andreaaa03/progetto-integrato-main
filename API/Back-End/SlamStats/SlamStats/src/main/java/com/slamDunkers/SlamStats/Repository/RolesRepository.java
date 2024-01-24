package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
	/**
 * This method is used to find a Roles entity by its role.
 * @param role This is the role of the Roles entity to be found.
 * @return Optional<Roles> This returns the Roles entity with the given role, if it exists.
 */
Optional<Roles> findByRole(String role);
}
