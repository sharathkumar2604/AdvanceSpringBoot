package com.sharath.ghee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharath.ghee.entity.GheeEntity;
import com.sharath.ghee.repo.GheeRepo;

@RestController
public class GheeController {

	@Autowired
	private GheeRepo repo;

	@PostMapping("/saveAndSaveAll")
	public ResponseEntity<List<GheeEntity>> saveAndSaveAll(@RequestBody GheeEntity... entities) {

		List<GheeEntity> list = new ArrayList<GheeEntity>();
		for (GheeEntity en : entities) {
			repo.save(en);
			list.add(en);
		}
		return new ResponseEntity<>(list, HttpStatus.CREATED);

	}

	@GetMapping("/readAll")
	public ResponseEntity<List<GheeEntity>> readAll() {

		List<GheeEntity> list = repo.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@GetMapping("/readByName/{name}")
	public ResponseEntity<List<GheeEntity>> readByName(@PathVariable String name) {

		List<GheeEntity> lis = repo.findByNameIgnoreCase(name);

		return ResponseEntity.status(HttpStatus.FOUND).body(lis);
	}

	@GetMapping("/readById/{id}")
	public ResponseEntity<List<GheeEntity>> readById(@PathVariable List<Integer> id) {
		List<GheeEntity> list = new ArrayList<GheeEntity>();
		for (int i : id) {
			Optional<GheeEntity> lis = repo.findById(i);

			lis.ifPresent(list::add);
		}
		if (!list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/readByNameAndId/{name}/{id}")
	public ResponseEntity<List<GheeEntity>> readByNameAndId(@PathVariable String name, @PathVariable int id) {
		List<GheeEntity> list = new ArrayList<GheeEntity>();
		List<GheeEntity> list2 = readAll().getBody();
		for (GheeEntity dd : list2) {
			if (dd.getId() == id && name.equalsIgnoreCase(dd.getName())) {
				list.add(dd);

			}

		}
		/*
		 * // if (!list.isEmpty()) { // return
		 * ResponseEntity.status(HttpStatus.OK).body(list); // } else { // // return
		 * ResponseEntity.notFound().build(); // }
		 */ return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PutMapping("/update/{name}/{id}")
	public ResponseEntity<GheeEntity> updatePriceByNameAndId(@PathVariable String name, @PathVariable int id,
			@RequestBody Map<String, Integer> priceMap) {
		int price = priceMap.get("price");
		List<GheeEntity> ls = readAll().getBody();

		for (GheeEntity entity : ls) {
			if (name.equalsIgnoreCase(entity.getName()) && id == entity.getId()) {
				entity.setPrice(price);
				repo.save(entity);
			}

		}

		return ResponseEntity.status(HttpStatus.OK).build();

	}

	@PutMapping("/updateQty/{name}/{id}")
	public ResponseEntity<GheeEntity> updateQtyByNameAndId(@PathVariable String name, @PathVariable int id,
			@RequestBody Map<String, String> qtyMap) {
		String qty = qtyMap.get("qty");
		List<GheeEntity> ls = readAll().getBody();

		for (GheeEntity entity : ls) {
			if (name.equalsIgnoreCase(entity.getName()) && id == entity.getId()) {
				entity.setQty(qty);
				repo.save(entity);
			}

		}

		return ResponseEntity.status(HttpStatus.OK).build();

	}

	@DeleteMapping("/delete/{price}/{name}/{id}")
	public ResponseEntity<GheeEntity> deleteByPriceNameAndId(@PathVariable int price,@PathVariable String name, @PathVariable int id)
	{
			List<GheeEntity> ls = readAll().getBody(); // assuming you have a repo method to get all entities

    for (GheeEntity entity : ls) {
        if (price == entity.getPrice() && name.equalsIgnoreCase(entity.getName()) && id == entity.getId()) {
            repo.delete(entity);
            break; 
        }
    }
    return ResponseEntity.status(HttpStatus.OK).build();
}
	
}
