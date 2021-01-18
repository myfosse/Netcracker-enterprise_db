package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.BonusDTO;
import by.netcracker.enterprisedb.payload.response.MessageResponse;
import by.netcracker.enterprisedb.service.BonusService;
import by.netcracker.enterprisedb.service.impl.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bonus")
@Slf4j
public class BonusController {

  private final BonusService bonusService;

  @Autowired
  public BonusController(final BonusService bonusService) {
    this.bonusService = bonusService;
  }

  @ApiOperation(
      value = "Add new bonus for employee",
      notes = "This method allows admin add new bonus for employee")
  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final BonusDTO bonusDTO) {
    return ResponseEntity.ok(bonusService.save(bonusDTO));
  }

  @ApiOperation(
      value = "Update bonus",
      notes = "This method allows admin update bonus for employee")
  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final BonusDTO bonusDTO) {
    if (bonusDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    return ResponseEntity.ok(bonusService.update(bonusDTO));
  }

  @ApiOperation(value = "Get bonus by id", notes = "This method allows admin get bonus by ID")
  @GetMapping("/admin/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(bonusService.findById(id));
  }

  @ApiOperation(value = "Get all bonuses", notes = "This method allows admin get all bonuses")
  @GetMapping("/admin/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(bonusService.getAll());
  }

  @ApiOperation(
      value = "Get all bonuses by employee ID",
      notes = "This method allows admin or user get all bonuses by employee ID")
  @GetMapping("/user/all/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByEmployeeID(
      @PathVariable("employeeId") final Long employeeId) {
    return getAuthenticationUserID().equals(employeeId)
        ? ResponseEntity.ok(bonusService.getAllByEmployeeId(employeeId))
        : ResponseEntity.badRequest().body(new MessageResponse("You have no right"));
  }

  @ApiOperation(value = "Delete bonus by ID", notes = "This method allows admin delete bonus by ID")
  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    bonusService.deleteById(id);
    return ResponseEntity.ok(new MessageResponse("Bonus was deleted"));
  }

  public Long getAuthenticationUserID() {
    return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .getId();
  }
}
