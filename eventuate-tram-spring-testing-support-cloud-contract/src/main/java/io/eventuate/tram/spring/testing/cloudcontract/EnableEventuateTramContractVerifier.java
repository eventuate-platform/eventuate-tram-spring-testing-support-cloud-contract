package io.eventuate.tram.spring.testing.cloudcontract;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(ContractVerifierEventuateTramConfiguration.class)
public @interface EnableEventuateTramContractVerifier {
}
