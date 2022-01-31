package com.teamway.planning.repository;

import com.teamway.planning.entity.Shift;
import com.teamway.planning.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift,Integer> {

    Optional<Shift> findByWorkerAndDate(final Worker worker,final Date date);

}
