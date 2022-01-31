package com.teamway.planning.service;

import com.teamway.planning.entity.Worker;
import com.teamway.planning.exception.SchedulerException;
import com.teamway.planning.repository.WorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;

    public Worker saveWorker(Worker worker){

        var existWorker = workerRepository.findByBadgeNumber(worker.getBadgeNumber());
        if(existWorker.isEmpty()){
            return workerRepository.save(worker);
        }
        return existWorker.get();
    }

    public Worker findByBadgeNumber(Integer badgeNumber){
        return workerRepository.findByBadgeNumber(badgeNumber).orElseThrow(()-> new SchedulerException("Worker not found."));
    }

}
