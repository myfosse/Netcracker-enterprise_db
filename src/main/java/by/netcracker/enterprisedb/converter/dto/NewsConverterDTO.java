package by.netcracker.enterprisedb.converter.dto;

import by.netcracker.enterprisedb.dao.entity.News;
import by.netcracker.enterprisedb.dto.model.NewsDTO;

import java.util.List;
import java.util.stream.Collectors;

public class NewsConverterDTO {

  public static NewsDTO convertEntityToDTO(final News newsEntity) {
    NewsDTO newsDTO =
        NewsDTO.builder()
            .admin(EmployeeConverterDTO.convertEntityToDTO(newsEntity.getAdmin()))
            .adm_id(newsEntity.getAdm_id())
            .title(newsEntity.getTitle())
            .text(newsEntity.getText())
            .postDate(newsEntity.getPostDate())
            .build();
    newsDTO.setId(newsEntity.getId());
    return newsDTO;
  }

  public static News convertDTOToEntity(final NewsDTO newsDTO) {
    News news =
        News.builder()
            .admin(EmployeeConverterDTO.convertDTOToEntity(newsDTO.getAdmin()))
            .adm_id(newsDTO.getAdm_id())
            .title(newsDTO.getTitle())
            .text(newsDTO.getText())
            .postDate(newsDTO.getPostDate())
            .build();
    news.setId(newsDTO.getId());
    return news;
  }

  public static List<NewsDTO> convertListEntityToDTO(final List<News> newsList) {
    return newsList.stream().map(NewsConverterDTO::convertEntityToDTO).collect(Collectors.toList());
  }

  public static List<News> convertListDTOToEntity(final List<NewsDTO> newsDTOList) {
    return newsDTOList.stream()
        .map(NewsConverterDTO::convertDTOToEntity)
        .collect(Collectors.toList());
  }
}
