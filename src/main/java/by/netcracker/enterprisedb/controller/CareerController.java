package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.CareerDTO;
import by.netcracker.enterprisedb.payload.response.MessageResponse;
import by.netcracker.enterprisedb.service.CareerService;
import by.netcracker.enterprisedb.service.DepartmentService;
import by.netcracker.enterprisedb.service.EmployeeService;
import by.netcracker.enterprisedb.service.PositionService;
import by.netcracker.enterprisedb.service.impl.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/career")
public class CareerController {

  private final CareerService careerService;
  private final EmployeeService employeeService;
  private final DepartmentService departmentService;
  private final PositionService positionService;

  @Autowired
  public CareerController(
      final CareerService careerService,
      final EmployeeService employeeService,
      final DepartmentService departmentService,
      final PositionService positionService) {
    this.careerService = careerService;
    this.employeeService = employeeService;
    this.departmentService = departmentService;
    this.positionService = positionService;
  }

  @ApiOperation(
      value = "Add new career for employee",
      notes = "This method allows admin add new career for employee")
  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final CareerDTO careerDTO) {
    careerDTO.setDepartment(departmentService.findById(careerDTO.getDeptId()));
    careerDTO.setPosition(positionService.findById(careerDTO.getPosId()));
    careerDTO.setEmployee(employeeService.findById(careerDTO.getEmpId()));
    return ResponseEntity.ok(careerService.save(careerDTO));
  }

  @ApiOperation(
      value = "Update career",
      notes = "This method allows admin update career for employee")
  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final CareerDTO careerDTO) {
    if (careerDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    careerDTO.setDepartment(departmentService.findById(careerDTO.getDeptId()));
    careerDTO.setPosition(positionService.findById(careerDTO.getPosId()));
    careerDTO.setEmployee(employeeService.findById(careerDTO.getEmpId()));
    return ResponseEntity.ok(careerService.update(careerDTO));
  }

  @ApiOperation(value = "Get career by id", notes = "This method allows admin get career by ID")
  @GetMapping("/admin/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(careerService.findById(id));
  }

  @ApiOperation(value = "Get all careers", notes = "This method allows admin get all careers")
  @GetMapping("/admin/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(careerService.getAll());
  }

  @ApiOperation(
      value = "Get all careers by employee ID",
      notes = "This method allows admin or user get all careers by employee ID")
  @GetMapping("/user/all/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByEmployeeID(
      @PathVariable("employeeId") final Long employeeId) {
    return (getAuthenticationUserID().equals(employeeId) && getAuthenticationUserID() > 0)
        ? ResponseEntity.ok(careerService.getAllByEmployeeId(employeeId))
        : ResponseEntity.badRequest().body(new MessageResponse("You have no right"));
  }

  @ApiOperation(
      value = "Delete career by ID",
      notes = "This method allows admin delete career by ID")
  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    careerService.deleteById(id);
    return ResponseEntity.ok(new MessageResponse("Career deleted"));
  }

  public Long getAuthenticationUserID() {
    return SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
        ? ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            .getId()
        : 0;
  }
}
