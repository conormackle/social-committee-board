package com.aquaq.scb.entities;

import com.aquaq.scb.response.ScbResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Objects;

public abstract class EntityServiceAbstract implements EntityService {

    @Value("${spring.data.web.pageable.default-page-size}")
    Integer defaultPageSize;
    final String findByDateTimeBefore = "findByDateTimeBefore";
    final String findByDateTime = "findByDateTime";
    final String findByTimeBetween = "findByTimeBetween";

    private LocalDateTime dateStringToLocalDateTime(String date){
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd-MM-yyyy[ HH:mm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        return Objects.nonNull(date) ? LocalDateTime.parse(date, formatter) : null;
    }

    private String findDateTimeSearchInterval(LocalDateTime startDateTime, LocalDateTime endDateTime){
        if(Objects.isNull(startDateTime) && !Objects.isNull(endDateTime)){
            return findByDateTimeBefore;
        }else if(!Objects.isNull(startDateTime) && !Objects.isNull(endDateTime) && startDateTime.isEqual(endDateTime)) {
            return findByDateTime;
        }else if(!Objects.isNull(startDateTime) && !Objects.isNull(endDateTime)){
            return findByTimeBetween;
        }else{
            return "";
        }
    }

    @Override
    public ScbResponse findByDate(String startDate, String endDate, Integer page, Integer size){
        LocalDateTime startDateTime = dateStringToLocalDateTime(startDate);
        LocalDateTime endDateTime = dateStringToLocalDateTime(endDate);
        Pageable paging = PageRequest.of(Objects.nonNull(page) && page >= 0 ? page : 0, Objects.nonNull(size) && size > 0 ? size : defaultPageSize);
        String dateTimeSearchInterval = findDateTimeSearchInterval(startDateTime, endDateTime);
        switch (dateTimeSearchInterval) {
            case findByDateTimeBefore:
                return ScbResponse.createSuccessResponse(getScbResponseWithPaging(findByDateBefore(endDateTime, paging)));
            case findByDateTime:
                return ScbResponse.createSuccessResponse(getScbResponseWithPaging(findByDate(endDateTime, paging)));
            case findByTimeBetween:
                return ScbResponse.createSuccessResponse(getScbResponseWithPaging(findByDateBetween(startDateTime, endDateTime, paging)));
            default:
                return null;
        }
    }

    @Override
    public ScbResponse findByCreatedDateTime(String startDate, String endDate, Integer page, Integer size){
        LocalDateTime startDateTime = dateStringToLocalDateTime(startDate);
        LocalDateTime endDateTime = dateStringToLocalDateTime(endDate);
        Pageable paging = PageRequest.of(Objects.nonNull(page) && page >= 0 ? page : 0, Objects.nonNull(size) && size > 0 ? size : defaultPageSize);
        String dateTimeSearchInterval = findDateTimeSearchInterval(startDateTime, endDateTime);
        switch (dateTimeSearchInterval) {
            case findByDateTimeBefore:
                return ScbResponse.createSuccessResponse(getScbResponseWithPaging(findByCreatedDateTimeBefore(endDateTime, paging)));
            case findByDateTime:
                return ScbResponse.createSuccessResponse(getScbResponseWithPaging(findByCreatedDateTime(endDateTime, paging)));
            case findByTimeBetween:
                return ScbResponse.createSuccessResponse(getScbResponseWithPaging(findByCreatedDateTimeBetween(startDateTime, endDateTime, paging)));
            default:
                return null;
        }
    }

    private HashMap<String, Object> getScbResponseWithPaging(Page<Object> response){
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("content",response.getContent());
        responseMap.put("size",response.getSize());
        responseMap.put("sorted",response.getSort().isSorted());
        responseMap.put("page",response.getPageable().getPageNumber());
        responseMap.put("offset",response.getPageable().getOffset());
        return responseMap;
    }

    @Override
    public Page<Object> findByDateBefore(LocalDateTime endDateTime, Pageable page) {
        return null;
    }

    @Override
    public Page<Object> findByDate(LocalDateTime endDateTime, Pageable page) {
        return null;
    }

    @Override
    public Page<Object> findByDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable page) {
        return null;
    }

}
