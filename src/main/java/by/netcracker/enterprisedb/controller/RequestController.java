package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.RequestDTO;
import by.netcracker.enterprisedb.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {

  private final RequestService requestService;

  @Autowired
  public RequestController(final RequestService requestService) {
    this.requestService = requestService;
  }

  @PostMapping("/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final RequestDTO requestDTO) {
    return ResponseEntity.ok(requestService.save(requestDTO));
  }

  @PutMapping("/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final RequestDTO requestDTO) {
    return ResponseEntity.ok(requestService.update(requestDTO));
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(requestService.findById(id));
  }

  @GetMapping("/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(requestService.getAll());
  }

  @GetMapping("/all/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByEmployeeId(
      @PathVariable("employeeId") final Long employeeId) {
    return ResponseEntity.ok(requestService.getAllByEmployeeId(employeeId));
  }

  @GetMapping("/all/{adminId}")
  public @ResponseBody ResponseEntity<?> getAllByAdminId(
      @PathVariable("adminId") final Long adminId) {
    return ResponseEntity.ok(requestService.getAllByAdminId(adminId));
  }

  @DeleteMapping("/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    requestService.deleteById(id);
    return ResponseEntity.ok("Request deleted");
  }
}
