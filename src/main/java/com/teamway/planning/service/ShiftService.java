package com.teamway.planning.service;

import com.teamway.planning.entity.Shift;
import com.teamway.planning.entity.Worker;
import com.teamway.planning.exception.SchedulerException;
import com.teamway.planning.repository.ShiftRepository;
import com.teamway.planning.util.TimeTable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public Shift saveShift(final Worker worker, final Date date, final TimeTable timeTable){
       var shiftExist = findByWorkerAndDate(worker,date);
        if(shiftExist.isEmpty()){
            var shift = new Shift();
            shift.setDate(date);
            shift.setTimeTable(timeTable);
            shift.setWorker(worker);
            return shiftRepository.save(shift);
        }
       throw new SchedulerException("There is already a shift for this worker on this day.");
    }

    public String deleteByWorkerAndDate(Worker worker, Date date){
        var shift = shiftRepository.findByWorkerAndDate(worker,date);
        if(shift.isPresent()){
            shiftRepository.delete(shift.get());
            return "Success";
        }
        throw new SchedulerException("Shift not found");
    }

    public Optional<Shift> findByWorkerAndDate(final Worker worker, final Date date){
        return shiftRepository.findByWorkerAndDate(worker,date);
    }

}
