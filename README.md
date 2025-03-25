# Description

 Api request and response files and endpoints for payment API

## Setup Instructions 

- Open the paymentApi folder to any code editor and run the project . Note - your computer must have Springboot already installed with proper setup.
- Database is handled by Postgres therefore it should be installed on your pc
- If properly done your Springboot application will start to run on port 8080 .

## Features 

- Backend: RESTful APIs with CRUD operations
- Database: Postgres for storing user data


### Application.peroperites file Springboot :-

```

spring.application.name=paymentApi
spring.datasource.url=jdbc:postgresql://localhost:5432/payment_db
spring.jpa.properties.hibernate.default_schema=payment_db

spring.datasource.username=postgres
spring.datasource.password=1026344372
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true


```

## API documentation :-
```

 > POST "http://localhost:8080/get_service" > - assign payment request

```

## Unit Tests (Controller testing):-

```
class PaymentControllerTest {
    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private PaymentController paymentController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetServiceSuccessfulPayment() {
        // Arrange
        PaymentServiceRequest request = createMockPaymentServiceRequest();

        PaymentServiceResponse mockResponse = PaymentServiceResponse.success(
                new PaymentServiceResponse.ResponseData()
        );
        when(paymentService.processPayment(request)).thenReturn(mockResponse);
        // Act
        ResponseEntity<PaymentServiceResponse> responseEntity = paymentController.getService(request);
        // Assert
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals("success", responseEntity.getBody().getStatus());
        // Verify that processPayment was called once
        verify(paymentService, times(1)).processPayment(request);
    }

    @Test
    void testGetServiceErrorScenario() {
        // Arrange
        PaymentServiceRequest request = createMockPaymentServiceRequest();

        List<String> errorMessages = Arrays.asList("Payment failed", "Insufficient funds");
        PaymentServiceResponse mockErrorResponse = PaymentServiceResponse.error(errorMessages);

        when(paymentService.processPayment(request)).thenReturn(mockErrorResponse);

        // Act
        ResponseEntity<PaymentServiceResponse> responseEntity = paymentController.getService(request);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals("error", responseEntity.getBody().getStatus());
        assertEquals(errorMessages, responseEntity.getBody().getMessages());

        // Verify that processPayment was called once
        verify(paymentService, times(1)).processPayment(request);
    }

    @Test
    void testGetServiceWithMinimalRequest() {
        // Arrange
        PaymentServiceRequest request = new PaymentServiceRequest();
        request.setRequestMethod("TEST");

        PaymentServiceResponse mockResponse = PaymentServiceResponse.success(
                new PaymentServiceResponse.ResponseData()
        );

        when(paymentService.processPayment(request)).thenReturn(mockResponse);

        // Act
        ResponseEntity<PaymentServiceResponse> responseEntity = paymentController.getService(request);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCode().value());

        // Verify that processPayment was called once
        verify(paymentService, times(1)).processPayment(request);
    }

    // Helper method to create a mock PaymentServiceRequest
    private PaymentServiceRequest createMockPaymentServiceRequest() {
        PaymentServiceRequest request = new PaymentServiceRequest();

        PaymentServiceRequest.Details details = new PaymentServiceRequest.Details();
        details.setUser("0103");
        details.setInFlag(false);

        PaymentServiceRequest.Us userService = new PaymentServiceRequest.Us();
        userService.setCode("UCC_CODE01");

        request.setDetails(details);
        request.setUs(userService);
        request.setOrderIds(Arrays.asList(50000123L, 50000124L));
        request.setRequestMethod("pg_page");
        request.setPaymentMode(Arrays.asList("upi", "net_banking", "mandate"));
        request.setLoopbackUrl("");
        return request;
    }
}
```


### Rest Controller :-

```
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/get_service")
    public ResponseEntity<PaymentServiceResponse> getService(@RequestBody PaymentServiceRequest request) {
        PaymentServiceResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }
}


```

### Response Handling :-
```
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
```
### Request payload :-
```
{
    "details":{
        "user": "0103",
        "in": "",
        "in_flag": false,
        "br_code": "",
        "br_arn": "",
        "partner_id": ""
    },
    "us": {
"code": "UCC_CODE01"
},
"order_ids": [50000123,50000124],
"request_method": "pg_page",
"payment_mode": [ "upi", "net_banking", "mandate"],
"loopback_url": ""
}
```

### response data :-
```
{
    "status": "success",
    "data": {
        "pageLink": "http://localhost:8082/s4/pg_view_object/b14d9e5b-892a-4de8-8d69-a878c7087d1c",
        "paymentInformation": {
            "paymentMode": "",
            "getBankAccountDetailsRow": null,
            "modeAdditionalInfo": ""
        }
    },
    "messages": []
}
```

# Author 
## Robin Juyal | robinjuyal29@gmail.com | 9548933347



