package com.mahfouz.swan;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

@RestController
public class SwanHealthService {

    private static final Log log
        = LogFactory.getLog(SwanHealthService.class.getName());

    @RequestMapping(value="/**", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String save(HttpServletRequest request) {

         log.info("Entered save method.");

         String typeName = trimUrlToEntityName(request);

        return "PUT success:" + typeName;

    }

    @RequestMapping(value="/**", method=RequestMethod.GET)
    public String load(HttpServletRequest request) {

        log.info("Entered load method.");

        String typeName = trimUrlToEntityName(request);

        return "GET success:" + typeName;
    }

    private String trimUrlToEntityName(HttpServletRequest request) {
        String restOfTheUrl = (String) request.getAttribute
            (HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        if (restOfTheUrl.startsWith("/"))
            restOfTheUrl = restOfTheUrl.substring(1);

        if (restOfTheUrl.contains("/"))
            throw new UnrecognizedUrlException();

        return restOfTheUrl;
    }

    //
    // Nested
    //

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="URL unrecognized.")
    public final class UnrecognizedUrlException extends RuntimeException {

    }
}
