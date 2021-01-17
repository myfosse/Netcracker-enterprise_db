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

  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final RequestDTO requestDTO) {
    return ResponseEntity.ok(requestService.save(requestDTO));
  }

  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final RequestDTO requestDTO) {
    if(requestDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    return ResponseEntity.ok(requestService.update(requestDTO));
  }

  @GetMapping("/admin/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(requestService.findById(id));
  }

  @GetMapping("/admin/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(requestService.getAll());
  }

  @GetMapping("/user/all/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByEmployeeId(
      @PathVariable("employeeId") final Long employeeId) {
    return ResponseEntity.ok(requestService.getAllByEmployeeId(employeeId));
  }

  @GetMapping("/admin/all/{adminId}")
  public @ResponseBody ResponseEntity<?> getAllByAdminId(
      @PathVariable("adminId") final Long adminId) {
    return ResponseEntity.ok(requestService.getAllByAdminId(adminId));
  }

  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    requestService.deleteById(id);
    return ResponseEntity.ok("Request deleted");
  }
}
