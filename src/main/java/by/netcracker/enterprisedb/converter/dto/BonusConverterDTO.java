package by.netcracker.enterprisedb.converter.dto;

import by.netcracker.enterprisedb.dao.entity.Bonus;
import by.netcracker.enterprisedb.dto.model.BonusDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BonusConverterDTO {

  public static BonusDTO convertEntityToDTO(final Bonus bonusEntity) {
    BonusDTO bonusDTO =
        BonusDTO.builder()
            .employee(EmployeeConverterDTO.convertEntityToDTO(bonusEntity.getEmployee()))
            .month(bonusEntity.getMonth())
            .year(bonusEntity.getYear())
            .value(bonusEntity.getValue())
            .build();
    bonusDTO.setId(bonusEntity.getId());
    return bonusDTO;
  }

  public static Bonus convertDTOToEntity(final BonusDTO bonusDTO) {
    Bonus bonus =
        Bonus.builder()
            .employee(EmployeeConverterDTO.convertDTOToEntity(bonusDTO.getEmployee()))
            .month(bonusDTO.getMonth())
            .year(bonusDTO.getYear())
            .value(bonusDTO.getValue())
            .build();
    bonus.setId(bonusDTO.getId());
    return bonus;
  }

  public static List<BonusDTO> convertListEntityToDTO(final List<Bonus> bonusList) {
    return bonusList.stream()
        .map(BonusConverterDTO::convertEntityToDTO)
        .collect(Collectors.toList());
  }

  public static List<Bonus> convertListDTOToEntity(final List<BonusDTO> bonusDTOList) {
    return bonusDTOList.stream()
        .map(BonusConverterDTO::convertDTOToEntity)
        .collect(Collectors.toList());
  }
}
