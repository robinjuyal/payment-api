package com.paymentApi.request;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentServiceRequest {
    private Details details;
    private Us us;
    
    @JsonProperty("order_ids")
    private List<Long> orderIds;
    
    @JsonProperty("request_method")
    private String requestMethod;
    
    @JsonProperty("payment_mode")
    private List<String> paymentMode;
    
    @JsonProperty("loopback_url")
    private String loopbackUrl;
    
    @Data
    public static class Details {
        private String user;
        private String in;
        
        @JsonProperty("in_flag")
        private Boolean inFlag;
        
        @JsonProperty("br_code")
        private String brCode;
        
        @JsonProperty("br_arn")
        private String brArn;
        
        @JsonProperty("partner_id")
        private String partnerId;
    }
    
    @Data
    public static class Us {
        private String code;
    }
}
