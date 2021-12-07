package fftl.fftl02backSpring.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Response<T> {

    private boolean success;
    private String message;
    private T data;

    public Response(boolean success, String message){
        this.success = success;
        this.message = message;
        this.data = null;
    }

    public static<T> Response<T> response(boolean success, String message){
        return response(success, message, null);
    }

    public static<T> Response<T> response(boolean  success, String message, T data){

        return Response.<T>builder()
                .data(data)
                .success(success)
                .message(message)
                .build();
    }

}
