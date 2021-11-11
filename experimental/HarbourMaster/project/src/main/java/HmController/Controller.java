package HmController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController

public class Controller {
    
    //Logger declaration
    final static Logger LOG = LogManager.getLogger(Controller.class);
    
    {
        LOG.debug("Controller Created");
    }
    
    
    //test path to test ReST api
    @GetMapping("/api/testing")
    public ResponseEntity<Object> get_test(){
        LOG.info("test api success");
        return new ResponseEntity<>("probably working", HttpStatus.OK);
    }
}
