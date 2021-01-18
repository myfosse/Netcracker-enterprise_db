package by.netcracker.enterprisedb.converter.dto;

import by.netcracker.enterprisedb.dao.entity.Request;
import by.netcracker.enterprisedb.dto.model.RequestDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RequestConverterDTO {

  public static RequestDTO convertEntityToDTO(final Request requestEntity) {
    RequestDTO requestDTO =
        RequestDTO.builder()
            .employee(EmployeeConverterDTO.convertEntityToDTO(requestEntity.getEmployee()))
            .emp_id(requestEntity.getEmp_id())
            .admin(EmployeeConverterDTO.convertEntityToDTO(requestEntity.getAdmin()))
            .adm_id(requestEntity.getAdm_id())
            .title(requestEntity.getTitle())
            .text(requestEntity.getText())
            .sendDate(requestEntity.getSendDate())
            .agreed(requestEntity.isAgreed())
            .build();
    requestDTO.setId(requestEntity.getId());
    return requestDTO;
  }

  public static Request convertDTOToEntity(final RequestDTO requestDTO) {
    Request request =
        Request.builder()
            .employee(EmployeeConverterDTO.convertDTOToEntity(requestDTO.getEmployee()))
            .emp_id(requestDTO.getEmp_id())
            .admin(EmployeeConverterDTO.convertDTOToEntity(requestDTO.getAdmin()))
            .adm_id(requestDTO.getAdm_id())
            .title(requestDTO.getTitle())
            .text(requestDTO.getText())
            .sendDate(requestDTO.getSendDate())
            .agreed(requestDTO.isAgreed())
            .build();
    request.setId(requestDTO.getId());
    return request;
  }

  public static List<RequestDTO> convertListEntityToDTO(final List<Request> requestList) {
    return requestList.stream()
        .map(RequestConverterDTO::convertEntityToDTO)
        .collect(Collectors.toList());
  }

  public static List<Request> convertListDTOToEntity(final List<RequestDTO> requestDTOList) {
    return requestDTOList.stream()
        .map(RequestConverterDTO::convertDTOToEntity)
        .collect(Collectors.toList());
  }
}
