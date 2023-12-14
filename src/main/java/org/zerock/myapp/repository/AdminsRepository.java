package org.zerock.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.Admins;

import java.util.Optional;


@Repository
public interface AdminsRepository extends JpaRepository<Admins, Long> {
    Optional<Admins> findById(String id);
} // end interface
