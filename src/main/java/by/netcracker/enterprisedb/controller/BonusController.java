package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.BonusDTO;
import by.netcracker.enterprisedb.service.BonusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final BonusDTO bonusDTO) {
    return ResponseEntity.ok(bonusService.save(bonusDTO));
  }

  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final BonusDTO bonusDTO) {
    if(bonusDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    return ResponseEntity.ok(bonusService.update(bonusDTO));
  }

  @GetMapping("/admin/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(bonusService.findById(id));
  }

  @GetMapping("/admin/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(bonusService.getAll());
  }

  @GetMapping("/user/all/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByEmployeeID(@PathVariable("employeeId") final Long employeeId) {
    return ResponseEntity.ok(bonusService.getAllByEmployeeId(employeeId));
  }

  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    bonusService.deleteById(id);
    return ResponseEntity.ok("Bonus was deleted");
  }
}
