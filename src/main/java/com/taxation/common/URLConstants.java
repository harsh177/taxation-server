package com.taxation.common;

public class URLConstants {

	public static final String TAXATION_API = "/api";
	
	// PersonController URL's
	public static final String PERSON_ADD = "/person/add";
	public static final String PERSON_GET_ALL = "/person/getAll";
	public static final String PERSON_GET_SAMAGRA = "/person/get/samagra/{samagraId}";
	public static final String PERSON_GET_PHONE = "/person/get/phone/{phone}";
	public static final String PERSON_GET_ID = "/person/get/id/{id}";
	public static final String PERSON_DELETE = "/person/delete/{id}";
	public static final String PERSON_UPDATE = "/person/edit";
	
	// PropertyController URL's
	public static final String PROPERTY_ADD = "/property/add";
	public static final String PROPERTY_GET_ALL = "/property/getAll";
	public static final String PAY_TAX = "/property/payTax";
	public static final String PROPERTY_GET_BY_PHONE_OR_SAMAGRA = "/property/getByPhoneOrSamagra";
	public static final String PROPERTY_GET_BY_PHONE_OR_SAMAGRA_OR_UNIQUE = "/property/getByPhoneOrSamagraOrUnique";
	public static final String PROPERTY_GET_BY_PHONE = "/property/phone/{phoneNumber}";
	public static final String PROPERTY_GET_BY_SAMAGRA = "/property/samagra/{samagraId}";
	
	public static final String PROPERTY_TRANSFER = "/property/transfer";
	public static final String PROPERTY_UPDATE = "property/update";
	public static final String PROPERTY_DELETE = "property/{propertyId}";


	// SeedController URL's
	public static final String SEED_ALL = "/seed/seedAll";
	public static final String GET_PROPERTY_USAGES = "/seed/propertyUsage/getAll";
	public static final String GET_PROPERTY_TYPES = "/seed/propertyTypes/getAll";

	//TaxDetails URLS
	public static final String TAX_DETAILS_BY_PROPERTY =  "/taxDetails/{propertyId}/getAll";
	public static final String TAX_DETAILS_BY_TAX_DETAIL =  "/taxDetails/{taxDetailId}";
	public static final String CREATE_TAX_DETAILS_OF_ALL_PROPERTIES_FOR_MONTH =  "/taxDetails/createForAllProperties";

	//Schedular Names
	public static final String PROPERTY_TAX_DETAILS_SCHEDULAR = "property_tax_details_schedular";

}
