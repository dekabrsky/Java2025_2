package ru.urfu.domain.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.urfu.domain.repository.CountryRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FindMinUsersUseCaseTest {

    @Mock // MOCKITO создал фальшивый объект
    private CountryRepository mockRepository;

    @InjectMocks // MOCKITO внедряет mock-объект в UseCase
    private FindMinUsersUseCase findMinUsersUseCase;

    @Test
    void execute_ShouldReturnDataFromRepository() throws Exception {
        String expectedResult = "Belarus (7,000,000 чел.)";

        // заводим значение для mock-объекта
        when(mockRepository.findMinUsersInEasternEurope()).thenReturn(expectedResult);

        // вызываем метод UseCase
        String actualResult = findMinUsersUseCase.execute();

        // проверяем, что UseCase вернул именно то, что мы ему скормили
        assertEquals(expectedResult, actualResult);

        verify(mockRepository, times(1)).findMinUsersInEasternEurope();
    }
}