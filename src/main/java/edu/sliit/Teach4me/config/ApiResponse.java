package edu.sliit.Teach4me.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private Boolean success;
    private String message;
    private T response;

    public static <T> ResponseEntity<ApiResponse<T>> successResponse(String message, T response) {
        ApiResponse<T> body = new ApiResponse<>(true, message, response);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> errorResponse(String message, HttpStatus status) {
        ApiResponse<T> body = new ApiResponse<>(false, message, null);
        return new ResponseEntity<>(body, status);
    }
}
