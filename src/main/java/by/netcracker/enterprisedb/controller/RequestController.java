package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.RequestDTO;
import by.netcracker.enterprisedb.payload.response.MessageResponse;
import by.netcracker.enterprisedb.service.EmployeeService;
import by.netcracker.enterprisedb.service.RequestService;
import by.netcracker.enterprisedb.service.impl.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {

  private final RequestService requestService;
  private final EmployeeService employeeService;

  @Autowired
  public RequestController(
      final RequestService requestService, final EmployeeService employeeService) {
    this.requestService = requestService;
    this.employeeService = employeeService;
  }

  @ApiOperation(
      value = "Add new request",
      notes = "This method allows admin or user add new request")
  @PostMapping("/user/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody RequestDTO requestDTO) {
    requestDTO.setAdmin(employeeService.findById(getAuthenticationUserID()));
    return ResponseEntity.ok(requestService.save(requestDTO));
  }

  @ApiOperation(value = "Update request", notes = "This method allows admin or user update request")
  @PutMapping("/user/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final RequestDTO requestDTO) {
    if (requestDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    requestDTO.setAdmin(employeeService.findById(getAuthenticationUserID()));
    return ResponseEntity.ok(requestService.update(requestDTO));
  }

  @ApiOperation(value = "Get request by ID", notes = "This method allows admin get request by ID")
  @GetMapping("/admin/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(requestService.findById(id));
  }

  @ApiOperation(value = "Get all requests", notes = "This method allows admin get all requests")
  @GetMapping("/admin/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(requestService.getAll());
  }

  @ApiOperation(
      value = "Get all requests by employee ID",
      notes = "This method allows admin or user get all requests by employee ID")
  @GetMapping("/user/all/send/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByEmployeeId(
      @PathVariable("employeeId") final Long employeeId) {
    return (getAuthenticationUserID().equals(employeeId) && getAuthenticationUserID() > 0)
        ? ResponseEntity.ok(requestService.getAllByEmployeeId(employeeId))
        : ResponseEntity.badRequest().body(new MessageResponse("You have no right"));
  }

  @ApiOperation(
      value = "Get all requests by admin ID",
      notes = "This method allows admin get all requests by admin ID")
  @GetMapping("/user/all/get/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByAdminId(
      @PathVariable("employeeId") final Long employeeId) {
    return (getAuthenticationUserID().equals(employeeId) && getAuthenticationUserID() > 0)
        ? ResponseEntity.ok(requestService.getAllByAdminId(employeeId))
        : ResponseEntity.badRequest().body(new MessageResponse("You have no right"));
  }

  @ApiOperation(
      value = "Delete requests by ID",
      notes = "This method allows admin delete requests by  ID")
  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    requestService.deleteById(id);
    return ResponseEntity.ok(new MessageResponse("Request deleted"));
  }

  public Long getAuthenticationUserID() {
    return SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
        ? ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            .getId()
        : 0;
  }
}
