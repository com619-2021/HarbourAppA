
package HmController;

import dto.Pilot;

import service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;



    
    @RestController
public class PilotController {
	private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	PilotService pilotService;

	@RequestMapping(value = "/api/createPilot", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> createPilot(@RequestBody Pilot pilot) {
		log.info("/api/createPilot: entered.");
		log.info("/api/createPilot: creation of " + pilot + " requested.");

		pilotService.createNewPilot(pilot);

		return pilotService.createNewPilot(pilot) ? new ResponseEntity<>(pilot, HttpStatus.OK)
			: new ResponseEntity<>("Unable to create new pilot.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

    
    

