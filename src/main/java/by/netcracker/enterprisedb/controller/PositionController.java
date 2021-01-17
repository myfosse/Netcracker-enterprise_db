package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.PositionDTO;
import by.netcracker.enterprisedb.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/position")
public class PositionController {

  private final PositionService positionService;

  @Autowired
  public PositionController(final PositionService positionService) {
    this.positionService = positionService;
  }

  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final PositionDTO positionDTO) {
    return ResponseEntity.ok(positionService.save(positionDTO));
  }

  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final PositionDTO positionDTO) {
    if(positionDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    return ResponseEntity.ok(positionService.update(positionDTO));
  }

  @GetMapping("/all/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(positionService.findById(id));
  }

  @GetMapping("/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(positionService.getAll());
  }

  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    positionService.deleteById(id);
    return ResponseEntity.ok("Position deleted");
  }
}
