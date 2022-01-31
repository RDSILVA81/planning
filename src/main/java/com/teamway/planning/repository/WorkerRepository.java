package com.teamway.planning.repository;

import com.teamway.planning.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    Optional<Worker> findByBadgeNumber(Integer badgeNUmber);
}
