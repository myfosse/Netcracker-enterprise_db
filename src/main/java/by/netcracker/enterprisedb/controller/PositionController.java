package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.PositionDTO;
import by.netcracker.enterprisedb.payload.response.MessageResponse;
import by.netcracker.enterprisedb.service.PositionService;
import io.swagger.annotations.ApiOperation;
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

  @ApiOperation(value = "Add new position", notes = "This method allows admin add new position")
  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final PositionDTO positionDTO) {
    return ResponseEntity.ok(positionService.save(positionDTO));
  }

  @ApiOperation(value = "Update position", notes = "This method allows admin update position")
  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final PositionDTO positionDTO) {
    if (positionDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    return ResponseEntity.ok(positionService.update(positionDTO));
  }

  @ApiOperation(
      value = "Get position by ID",
      notes = "This method allows everyone get position by ID")
  @GetMapping("/all/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(positionService.findById(id));
  }

  @ApiOperation(value = "Get positions", notes = "This method allows everyone get positions")
  @GetMapping("/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(positionService.getAll());
  }

  @ApiOperation(value = "Delete position", notes = "This method allows admin delete position by ID")
  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    positionService.deleteById(id);
    return ResponseEntity.ok(new MessageResponse("Position deleted"));
  }
}
