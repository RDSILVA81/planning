package com.teamway.planning.controller;

import com.teamway.planning.entity.Shift;
import com.teamway.planning.entity.Worker;
import com.teamway.planning.service.ShiftService;
import com.teamway.planning.service.WorkerService;
import com.teamway.planning.util.TimeTable;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path="/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final WorkerService workerService;
    private final ShiftService shiftService;

    @Operation(summary= "Service to create a worker.", description="This service will create a worker if it doesn't exist otherwise will return the existent one.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/worker")
    public Worker createWorker(@RequestBody final Worker worker){
        return workerService.saveWorker(worker);
    }

    @Operation(summary= "Will find a worker by badge number.", description="Will find a worker by badge number.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/worker")
    public Worker getWorker(@RequestBody final Integer badgeNumber){
        return workerService.findByBadgeNumber(badgeNumber);
    }

    @Operation(summary= "Will find a shift list of worker by badge number.", description="Will find a shift list of worker by badge number.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/shift")
    public Worker getWorkerShiftList(@RequestParam Integer badgeNumber){
        return workerService.findByBadgeNumber(badgeNumber);
    }

    @Operation(summary= "Service to create a shift for a worker.",
            description= "Service to create a shift for a worker. \n**Morning** = 0-8 \n**Afternoon** = 8-16 \n**Night** = 16-24")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value ="/shift")
    public Shift createShift(@RequestParam Integer badgeNumber, @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date date, @RequestParam TimeTable timeTable){
        var worker = workerService.findByBadgeNumber(badgeNumber);
        return shiftService.saveShift(worker,date,timeTable);
    }

    @Operation(summary= "Service to update a shift for a worker.",
            description= "Service to update a shift for a worker. \n**Morning** = 0-8 \n**Afternoon** = 8-16 \n**Night** = 16-24")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value ="/shift")
    public Shift updateShift(@RequestParam Integer badgeNumber, @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date date, @RequestParam TimeTable timeTable){
        var worker = workerService.findByBadgeNumber(badgeNumber);
        return shiftService.updateShift(worker,date,timeTable);
    }


    @Operation(summary= "Service to delete a shift for a worker.", description="Service to delete a shift for a worker.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value="/shift")
    public void deleteShift(@RequestParam Integer badgeNumber, @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date date){
        var worker = workerService.findByBadgeNumber(badgeNumber);
        shiftService.deleteByWorkerAndDate(worker, date);
    }

}
