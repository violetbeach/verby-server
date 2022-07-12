## Project structure

프로젝트는 아래의 구조를 따른다.

```
com.verby.restapi
    +- domain
        +- order
           +- command
               +- application
               |   +- CancelOrderService.java
               |   +- ChangeShippingRequest.java
               |   +- NoOrderProductException.java
               |   +- OrderRequestValidator.java
               |   +- ...
               +- domain
               |   +- Order.java
               |   +- OrderLine.java
               |   +- OrderStatus.java
               |   +- OrderRepository.java
               |   +- OrderService.java
               |   +- ShippingInfo.java
               |   +- OrderAlreadyCanceledException
               |   +- ...
           +- query
               +- application
               |   +- OrderDetail.java
               |   +- orderLineDetail.java
               |   +- OrderDetailService.java
               |   +- ...
               +- dao
               |   +- OrderidSpec.java
               |   +- OrderSummaryDao.java
               |   +- OrderSummarySpecs.java
               |   +- ...
               +- dto
               |   +- OrderSummary.java
               |   +- OrderView.java
               |   +- ...
           +- infrastructure
           |   +- OrderRepositoryImpl.java
           |   +- ExternalApiClient.java
           |   +- ...
           +- Presentation
           |   +- OrderController.java
           |   +- ...
        +- common
        +- config
            +- security
    +- Application.java  
```