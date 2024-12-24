package contracts;

org.springframework.cloud.contract.spec.Contract.make {
    label 'orderCreatedEvent'
    input {
        triggeredBy('orderCreated()')
    }

    outputMessage {
        sentTo('Order')
        body([
                orderId: 101
        ])
        headers {
            header('event-aggregate-type', 'Order')
            header('event-type', 'io.eventuate.tram.springcloudcontract.eventpublisher.OrderCreatedEvent')
            header('event-aggregate-id', '99') // Matches OrderDetailsMother.ORDER_ID
        }
    }
}