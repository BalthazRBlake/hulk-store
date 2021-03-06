package org.dev.fhhf.hulkstore.service;

import java.util.List;

import org.dev.fhhf.hulkstore.model.MovedUnits;
import org.dev.fhhf.hulkstore.model.Movement;

public interface MovementService {

	List<Movement> findAllMovements();
	
	Movement findMovemenetById(int id);
	
	Movement saveMovement(Movement movement);
	
	void deleteMovement(Movement movement);
	
	List<MovedUnits> getMovedUnitsPerMove(List<Movement> movements);
}
