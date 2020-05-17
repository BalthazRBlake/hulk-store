package org.dev.fhhf.hulkstore.service;

import java.util.ArrayList;
import java.util.List;

import org.dev.fhhf.hulkstore.model.MovedUnits;
import org.dev.fhhf.hulkstore.model.Movement;
import org.dev.fhhf.hulkstore.repository.MovementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementServiceImpl implements MovementService{

	@Autowired
	private MovementRepo moveRepo;

	@Override
	public List<Movement> findAllMovements() {
		return moveRepo.findAll();
	}

	@Override
	public Movement findMovemenetById(int id) {
		return moveRepo.findById(id).get();
	}

	@Override
	public Movement saveMovement(Movement movement) {
		
		if (movement.getMovedUnits().equals("")) {
			return null;
		}
		
		return moveRepo.save(movement);
	}

	@Override
	public void deleteMovement(Movement movement) {
		moveRepo.delete(movement);
	}
	
	@Override
	public List<MovedUnits> getMovedUnitsPerMove(List<Movement> movements) {
		
		List<MovedUnits> transactions = new ArrayList<>();
		
		for (Movement m : movements) {
			
			transactions.add(new MovedUnits(0,0,0)); // Insert blank space to show data in frontEnd
			
			String[] trans = m.getMovedUnits().split(",");

			for (String t : trans) {

				String[] values = t.split(" ");
				
				transactions.add(new MovedUnits(
					Integer.valueOf(values[0]),
					Integer.valueOf(values[1]),
					Integer.valueOf(values[2])
				));
			}
		}		
		return transactions;
	}
}
