package by.netcracker.enterprisedb.converter.dto;

import by.netcracker.enterprisedb.dao.entity.Position;
import by.netcracker.enterprisedb.dto.model.PositionDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PositionConverterDTO {

  public static PositionDTO convertEntityToDTO(final Position positionEntity) {
    PositionDTO positionDTO =
        PositionDTO.builder()
            .specialty(positionEntity.getSpecialty())
            .minSalary(positionEntity.getMinSalary())
            .build();
    positionDTO.setId(positionEntity.getId());
    return positionDTO;
  }

  public static Position convertDTOToEntity(final PositionDTO positionDTO) {
    Position position =
        Position.builder()
            .specialty(positionDTO.getSpecialty())
            .minSalary(positionDTO.getMinSalary())
            .build();
    position.setId(positionDTO.getId());
    return position;
  }

  public static List<PositionDTO> convertListEntityToDTO(final List<Position> positionList) {
    return positionList.stream()
        .map(PositionConverterDTO::convertEntityToDTO)
        .collect(Collectors.toList());
  }

  public static List<Position> convertListDTOToEntity(final List<PositionDTO> positionDTOList) {
    return positionDTOList.stream()
        .map(PositionConverterDTO::convertDTOToEntity)
        .collect(Collectors.toList());
  }
}
