module jpms_dss_ws_certificate_validation_soap_client {
	
	requires transitive jpms_dss_ws_certificate_validation_dto;
	
	exports eu.europa.esig.dss.ws.cert.validation.soap.client;
}