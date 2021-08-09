package fftl.fftl02backSpring.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DefaultResponse<T> {

    private int statusCode;
    private String responseMessage;
    private T data;

    public DefaultResponse(int statusCode, String responseMessage){
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }

    public static<T> DefaultResponse<T> response(int statusCode, String responseMessage){
        return response(statusCode, responseMessage, null);
    }

    public static<T> DefaultResponse<T> response(int statusCode, String responseMessage, T data){

        return DefaultResponse.<T>builder()
                .data(data)
                .statusCode(statusCode)
                .responseMessage(responseMessage)
                .build();
    }

}