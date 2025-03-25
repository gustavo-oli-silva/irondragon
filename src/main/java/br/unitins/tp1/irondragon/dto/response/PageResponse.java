package br.unitins.tp1.irondragon.dto.response;

import java.util.List;

public record PageResponse<T>(
     int page,
     int pageSize,
     long count,
     List<T> results
) {

    public static <T> PageResponse<T> valueOf( int page, int pageSize, long count, List<T> results) {
        return new PageResponse<>(page, pageSize, count, results);
    }
}
