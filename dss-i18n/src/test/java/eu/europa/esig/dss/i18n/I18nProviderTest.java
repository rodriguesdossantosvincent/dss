/**
 * DSS - Digital Signature Services
 * Copyright (C) 2015 European Commission, provided under the CEF programme
 * 
 * This file is part of the "DSS - Digital Signature Services" project.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package eu.europa.esig.dss.i18n;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;

import org.junit.jupiter.api.Test;

public class I18nProviderTest {
	
	@Test
	public void test() {
		Locale systemLocale = Locale.getDefault();
		try {
			Locale.setDefault(Locale.ENGLISH);
		
			final I18nProvider i18nProvider = new I18nProvider(Locale.ENGLISH);
			
			String message = i18nProvider.getMessage(MessageTag.BBB_XCV_CCCBB);
			assertNotNull(message);
			assertEquals("Can the certificate chain be built till a trust anchor?", message);
			
			IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { i18nProvider.getMessage(null); });
			assertEquals("messageTag cannot be null!", exception.getMessage());
			
			final I18nProvider i18nFranceProvider = new I18nProvider(Locale.FRANCE);
			message = i18nFranceProvider.getMessage(MessageTag.BBB_XCV_CCCBB);
			assertNotNull(message);
			assertEquals("Peut-on remonter jusqu'\u00E0 une ancre de confiance ?", message);
			
			final I18nProvider i18nFrenchProvider = new I18nProvider(Locale.FRENCH);
			message = i18nFrenchProvider.getMessage(MessageTag.BBB_XCV_CCCBB);
			assertNotNull(message);
			assertEquals("Peut-on remonter jusqu'\u00E0 une ancre de confiance ?", message);
	
			final I18nProvider i18nGermanProvider = new I18nProvider(Locale.GERMAN);
			message = i18nGermanProvider.getMessage(MessageTag.BBB_XCV_CCCBB);
			assertNotNull(message);
			assertEquals("Can the certificate chain be built till a trust anchor?", message);
		} finally {
			Locale.setDefault(systemLocale); // restore default
		}
	}
	
	@Test
	public void parametrizedTest() {
		Locale systemLocale = Locale.getDefault();
		try {
			Locale.setDefault(Locale.ENGLISH);
			
			String status = "granted";
			MessageTag messageTag = MessageTag.TRUSTED_SERVICE_STATUS.setArgs(status);
		
			final I18nProvider i18nProvider = new I18nProvider(Locale.ENGLISH);
			String message = i18nProvider.getMessage(messageTag);
			assertNotNull(message);
			assertEquals("Status : granted", message);
			
			final I18nProvider i18nFrenchProvider = new I18nProvider(Locale.FRENCH);
			message = i18nFrenchProvider.getMessage(messageTag);
			assertNotNull(message);
			assertEquals("Statut : granted", message);
	
			final I18nProvider i18nGermanProvider = new I18nProvider(Locale.GERMAN);
			message = i18nGermanProvider.getMessage(messageTag);
			assertNotNull(message);
			assertEquals("Status : granted", message);
		} finally {
			Locale.setDefault(systemLocale); // restore default
		}
	}
	
	@Test
	public void nestedMessageTagTest() {
		Locale systemLocale = Locale.getDefault();
		try {
			Locale.setDefault(Locale.ENGLISH);
			
			MessageTag validationTime = MessageTag.VT_VALIDATION_TIME;
			MessageTag messageTag = MessageTag.CERT_QUALIFICATION_AT_TIME.setArgs(validationTime);
		
			final I18nProvider i18nProvider = new I18nProvider(Locale.ENGLISH);
			String message = i18nProvider.getMessage(messageTag);
			assertNotNull(message);
			assertEquals("Certificate Qualification at validation time", message);
			
			final I18nProvider i18nFrenchProvider = new I18nProvider(Locale.FRENCH);
			message = i18nFrenchProvider.getMessage(messageTag);
			assertNotNull(message);
			assertEquals("Qualification du certificat au moment de la validation", message);
	
			final I18nProvider i18nGermanProvider = new I18nProvider(Locale.GERMAN);
			message = i18nGermanProvider.getMessage(messageTag);
			assertNotNull(message);
			assertEquals("Certificate Qualification at validation time", message);
		} finally {
			Locale.setDefault(systemLocale); // restore default
		}
	}

}
