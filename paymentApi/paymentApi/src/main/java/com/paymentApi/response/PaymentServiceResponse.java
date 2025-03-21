package com.paymentApi.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentServiceResponse {
    private String status;
    private ResponseData data;
    private List<String> messages;

    @Data
    public static class ResponseData {
        private String pageLink;
        private PaymentInformation paymentInformation;
    }

    @Data
    public static class PaymentInformation {
        private String paymentMode;
        private BankAccountDetails getBankAccountDetailsRow;
        private String modeAdditionalInfo;
    }
    @Data
    public static class BankAccountDetails {
        // Add fields as needed
    }

    public static PaymentServiceResponse success(ResponseData data) {
        PaymentServiceResponse response = new PaymentServiceResponse();
        response.setStatus("success");
        response.setData(data);
        response.setMessages(List.of());
        return response;
    }
    
    public static PaymentServiceResponse error(List<String> messages) {
        PaymentServiceResponse response = new PaymentServiceResponse();
        response.setStatus("error");
        response.setData(null);
        response.setMessages(messages);
        return response;
    }
}
