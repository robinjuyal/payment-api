package com.paymentApi.entity;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "payment_transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "order_ids", length = 1000)
    private String orderIds;
    
    @Column(name = "request_method")
    private String requestMethod;
    
    @Column(name = "payment_modes", length = 500)
    private String paymentModes;
    
    @Column(name = "loopback_url", length = 500)
    private String loopbackUrl;
    
    @Column(name = "page_link", length = 500)
    private String pageLink;
    
    @Column(name = "payment_mode")
    private String paymentMode;
    
    @Column(name = "mode_additional_info", length = 500)
    private String modeAdditionalInfo;
    
    @Column(name = "status")
    private String status;
}