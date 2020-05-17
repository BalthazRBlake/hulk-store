package org.dev.fhhf.hulkstore.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.dev.fhhf.hulkstore.model.Employee;
import org.dev.fhhf.hulkstore.model.MovedUnits;
import org.dev.fhhf.hulkstore.model.Movement;
import org.dev.fhhf.hulkstore.repository.MovementRepo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovementServiceTest {

	@Mock
	private MovementRepo moveRepo;
	@InjectMocks
	private MovementServiceImpl movementService;
	
	@Test
	@DisplayName("Encontrando todos los movimientos")
	void testFindAllMovements() {
	
		Movement move1 = new Movement(1, "1 0 50,4 5 20", "Input", new Employee(1, "Juan"));
		Movement move2 = new Movement(2, "2 45 5", "Output", new Employee(2, "Laura"));
		List<Movement> movements = Arrays.asList(move1, move2);
		
		when(moveRepo.findAll()).thenReturn(movements);

		List<Movement> actual = movementService.findAllMovements();

		assertEquals(movements, actual);
	}

	@Test
	@DisplayName("Obteniendo las unidades movidas por operacion")
	void testGetMovedUnitsPerMove() {
		
		Movement move1 = new Movement(1, "1 0 50,4 5 20", "Input", new Employee(1, "Juan"));
		Movement move2 = new Movement(2, "2 45 5", "Output", new Employee(2, "Laura"));
		List<Movement> movements = Arrays.asList(move1, move2);
		
		MovedUnits movedU0 = new MovedUnits(0, 0, 0);
		MovedUnits movedU1 = new MovedUnits(1, 0, 50);
		MovedUnits movedU2 = new MovedUnits(4, 5, 20);
		MovedUnits movedU3 = new MovedUnits(2, 45, 5);
		
		final List<MovedUnits> EXPECTED = Arrays.asList(movedU0, movedU1, movedU2, movedU0, movedU3); 
		
		final List<MovedUnits> ACTUAL =  movementService.getMovedUnitsPerMove(movements);
		
		assertAll(() -> {
					for(int i = 0; i < ACTUAL.size(); i++) {

						assertEquals(EXPECTED.get(i).getProductId(), ACTUAL.get(i).getProductId(), "el Id de producto debe ser el mismo");
						assertEquals(EXPECTED.get(i).getInitialUnits(), ACTUAL.get(i).getInitialUnits(), "las unidades iniciales deben ser iguales");
						assertEquals(EXPECTED.get(i).getMovedUnits(), ACTUAL.get(i).getMovedUnits(), "las unidades movidas deben ser las mismas");
					}
				});
	}

}
