package com.teamway.planning.service;

import com.teamway.planning.entity.Shift;
import com.teamway.planning.entity.Worker;
import com.teamway.planning.exception.SchedulerException;
import com.teamway.planning.repository.ShiftRepository;
import com.teamway.planning.util.TimeTable;
import lombok.SneakyThrows;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
class ShiftServiceTest {


    private static ShiftService service;
    private static ShiftRepository repositoryMock;
    private static Worker worker;
    private static Shift shift;
    private static Date date;


    @BeforeAll
    @SneakyThrows
    static void configure(){
        repositoryMock = mock(ShiftRepository.class);
        service = new ShiftService(repositoryMock);

        worker = new Worker();
        worker.setBadgeNumber(555);
        worker.setDateOfBirthday(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1980"));
        worker.setFirstName("Jhon");
        worker.setLastName("Smith");

        date = new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2022");
        shift = new Shift();
        shift.setDate(date);
        shift.setTimeTable(TimeTable.Morning);
        shift.setWorker(worker);
    }

    @Test
    @SneakyThrows
    void saveShiftSuccessWhenItDoesNotExist(){
        //Arrange
        when(repositoryMock.findByWorkerAndDate(any(),any())).thenReturn(Optional.empty());
        when(repositoryMock.save(any())).thenReturn(shift);

        //Action
       var response = service.saveShift(worker,date,TimeTable.Morning);

        //Assert
        assertThat(response.getTimeTable()).isEqualTo(TimeTable.Morning);

    }

    @Test
    @SneakyThrows
    void givenAnExistedWorkerShiftWillReturnException(){
        //Arrange
        when(repositoryMock.findByWorkerAndDate(any(),any())).thenReturn(Optional.of(shift));

        //Action


        //Assert
        assertThatThrownBy(()-> service.saveShift(worker,date,TimeTable.Morning)).isInstanceOf(SchedulerException.class);
    }

    @Test
    @SneakyThrows
    void findShiftByWorkerAndDateSuccess(){
        //Arrange
        when(repositoryMock.findByWorkerAndDate(any(),any())).thenReturn(Optional.of(shift));

        //Action
        var response = service.findByWorkerAndDate(worker,date);

        //Assert
        assertThat(response).isNotEmpty();
    }

    @Test
    void deleteShiftSuccess(){
        //Arrange
        when(repositoryMock.findByWorkerAndDate(any(),any())).thenReturn(Optional.of(shift));

        //Action
        var response = service.deleteByWorkerAndDate(worker,date);

        //Assert
        assertThat(response).isEqualTo("Success");

    }

    @Test
    void deleteShiftNotFound(){
        //Arrange
        when(repositoryMock.findByWorkerAndDate(any(),any())).thenReturn(Optional.empty());

        //Action


        //Assert
        assertThatThrownBy(()-> service.deleteByWorkerAndDate(worker,date)).isInstanceOf(SchedulerException.class);

    }




}