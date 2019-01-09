package group.bridge.web.controller;

import group.bridge.web.entity.Car;
import group.bridge.web.entity.Person;
import group.bridge.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Controller
public class DemoController {


}
