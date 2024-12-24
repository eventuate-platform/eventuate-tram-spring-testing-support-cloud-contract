package contracts.http;

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        url '/send'
    }
    response {
        status 200
    }
}