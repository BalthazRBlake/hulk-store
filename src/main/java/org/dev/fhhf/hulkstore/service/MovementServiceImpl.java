package org.dev.fhhf.hulkstore.service;

import java.util.List;

import org.dev.fhhf.hulkstore.model.Movement;
import org.dev.fhhf.hulkstore.repository.MovementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementServiceImpl implements MovementService{

	@Autowired
	private MovementRepo moveRepo;

	@Override
	public List<Movement> finsAllMovements() {
		return moveRepo.findAll();
	}

	@Override
	public Movement findMovemenetById(int id) {
		return moveRepo.findById(id).get();
	}

	@Override
	public Movement saveMovement(Movement movement) {
		return moveRepo.save(movement);
	}

	@Override
	public void deleteMovement(Movement movement) {
		moveRepo.delete(movement);
	}
}
