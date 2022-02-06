package com.teamway.planning.service;

import com.teamway.planning.entity.Worker;
import com.teamway.planning.repository.WorkerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class WorkerServiceTest {

    private static WorkerRepository repositoryMock;
    private static WorkerService service;
    private static Worker worker;

    @BeforeAll
    @SneakyThrows
    static void configureService(){
        repositoryMock = mock(WorkerRepository.class);
        service = new WorkerService(repositoryMock);

        worker = new Worker();
        worker.setBadgeNumber(555);
        worker.setDateOfBirthday(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1980"));
        worker.setFirstName("Jhon");
        worker.setLastName("Smith");
    }

    @Test
    void saveWorkerSuccessWhenItDoesNotExist(){
        //Arrange
        when(repositoryMock.findByBadgeNumber(any())).thenReturn(Optional.empty());
        when(repositoryMock.save(any())).thenReturn(worker);

        //Action
        var response = service.saveWorker(worker);

        //Assert
        assertThat(response.getBadgeNumber()).isEqualTo(555);
    }

    @Test
    void givenAnExistedWorkerWillReturnTheFindOne(){
        //Arrange
        when(repositoryMock.findByBadgeNumber(any())).thenReturn(Optional.of(worker));

        //Action
        var response = service.saveWorker(worker);

        //Assert
        assertThat(response.getBadgeNumber()).isEqualTo(555);

    }

}