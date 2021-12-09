
package org.solent.com504.project.impl.web;


import java.util.UUID;

import org.solent.com504.project.model.dto.Pilot;
import org.solent.com504.project.impl.resource.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@Api
public class PilotController {
	private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	PilotService pilotService;

	@RequestMapping(value = "/api/pilot/create", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation("Creates a pilot using the given pilot object.")
	public ResponseEntity<Object> createPilot(@RequestBody Pilot pilot) {
		log.info("/api/pilot/create: entered.");
		log.info("/api/pilot/create: creation of " + pilot + " requested.");

		pilotService.createNewPilot(pilot);

		return new ResponseEntity<>(pilot, HttpStatus.CREATED);
	}

	@GetMapping(value = "/api/pilot/{uuid}", produces = "application/json")
	@ApiOperation("Returns the pilot of the given UUID.")
	public ResponseEntity<Object> findPilot(@PathVariable UUID uuid) {
		log.info("(GET) /api/pilot: entered.");
		log.info("(GET) /api/pilot: query of pilot '" + uuid + "' requested.");

		Pilot pilot = pilotService.findPilot(uuid);

		return pilot != null ? new ResponseEntity<>(pilot, HttpStatus.OK)
			: new ResponseEntity<>(String.format("Unable to find pilot '%s' in the database.", uuid), HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/api/pilot/getAll")
	@ApiOperation("Returns an array of all pilots registered in the system.")
	public ResponseEntity<Object> getAllPilots() {
		log.info("/api/pilot/getAll: entered.");
		return new ResponseEntity<>(pilotService.getAllPilots(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/api/pilot/{uuid}")
	@Transactional
	@ApiOperation("Deletes the pilot of the given UUID.")
	public ResponseEntity<Object> deletePilot(@PathVariable UUID uuid) {
		log.info("(DELETE) /api/pilot: entered.");
		log.info("(DELETE) /api/pilot: deletion of pilot '" + uuid + "' requested.");

		return pilotService.deletePilot(uuid) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
			: new ResponseEntity<>(String.format("Unable to delete pilot '%s'. They may not exist in the database.", uuid), HttpStatus.NOT_FOUND);
	}

        
        
        //needs to be transactional
	@DeleteMapping(value = "/api/pilot/deleteAll")
	@Transactional
	@ApiOperation("DEBUG: Deletes all pilots.")
	public ResponseEntity<Object> deleteAllPilots() {
		log.info("(DELETE) /api/pilot: entered.");

		pilotService.deleteAllPilots();

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}