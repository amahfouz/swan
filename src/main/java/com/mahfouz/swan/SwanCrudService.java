package com.mahfouz.swan;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

@RestController
public class SwanCrudService {

    private static final Log log
        = LogFactory.getLog(SwanCrudService.class.getName());

    @Autowired
    private SwanRepository swanRepo;

    private final JsonJsonParser jsonParser = new JsonJsonParser();

    @RequestMapping(value="/*", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String save(HttpServletRequest request, @RequestBody String entityJson) {

         log.info("Entered save method.");

         String typeName = trimUrlToEntityName(request);

         Map<String, Object> parseMap = jsonParser.parseMap(entityJson);
         String id = (String) parseMap.get("id");

         if (id == null)
             throw new MissingObjectIdException();

         JsonEntity entity = new JsonEntity(id, entityJson);
         swanRepo.insert(entity);

        return "PUT success:" + typeName;

    }

    @RequestMapping(value="/*/{id}", method=RequestMethod.GET)
    public String load(@PathVariable("id") int id, HttpServletRequest request) {

        log.info("Entered load method. Id = " + id);

        //String typeName = trimUrlToEntityName(request);

        JsonEntity object = swanRepo.findOne(String.valueOf(id));
        if (object == null)
            throw new ObjectNotFoundException();

        return object.getJsonContent();
    }

    //
    // private methods
    //

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

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Missing ID.")
    public final class MissingObjectIdException extends RuntimeException {

    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Entity not found.")
    public final class ObjectNotFoundException extends RuntimeException {

    }
}
