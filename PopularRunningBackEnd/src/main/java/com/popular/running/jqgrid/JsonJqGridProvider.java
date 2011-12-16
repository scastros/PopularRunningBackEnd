package com.popular.running.jqgrid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.MappedXMLOutputFactory;


@Provider
@Consumes("application/json_jqgrid")
@Produces(value = { "application/json_jqgrid", "application/xml" })
public class JsonJqGridProvider implements MessageBodyReader<JqRunningEvent>,	MessageBodyWriter<JqRunningEvent> {
	
	public JsonJqGridProvider() {
		
	}
	

	public boolean isReadable(Class<?> inputClass, Type type,
			Annotation[] annotations, MediaType arg3) {

		return false;
	}

	/**
	 * @see javax.ws.rs.ext.MessageBodyReader#readFrom(java.lang.Class,
	 *      java.lang.reflect.Type, java.lang.annotation.Annotation[],
	 *      javax.ws.rs.core.MediaType, javax.ws.rs.core.MultivaluedMap,
	 *      java.io.InputStream)
	 */
	public JqRunningEvent readFrom(Class<JqRunningEvent> inputClass, Type type,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> map, InputStream inputs)
			throws IOException, WebApplicationException {

		return null;
	}

	/**
	 * @see javax.ws.rs.ext.MessageBodyWriter#getSize(java.lang.Object,
	 *      java.lang.Class, java.lang.reflect.Type,
	 *      java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType)
	 */
	public long getSize(JqRunningEvent arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {

		return 0;
	}

	/**
	 * @see javax.ws.rs.ext.MessageBodyWriter#isWriteable(java.lang.Class,
	 *      java.lang.reflect.Type, java.lang.annotation.Annotation[],
	 *      javax.ws.rs.core.MediaType)
	 */
	public boolean isWriteable(Class<?> inputClass, Type type,
			Annotation[] annotations, MediaType mediaType) {

		return inputClass == JqRunningEvent.class;
	}

	/**
	 * @param methodName
	 * @param message
	 */
	public void debug(String methodName, String message) {
		System.out.println(this.getClass().getName() + " " + methodName + " "
				+ message + "\n");
	}

	/**
	 * Replace all patterns with xmlns.
	 * 
	 * @param jsonString
	 * @return
	 */
	private String replaceAll(String jsonString) {

		/*
		 * Replaces the string in the following format.
		 * 
		 * {"dojoItems":{"items":[{"@xmlns.type.type":"xs:string","$":"abc"},
		 * 
		 * {"@xmlns.type.type":"xs:string","$":"ViaCore"},
		 * 
		 * {"@xmlns.type.type":"xs:string","$":"ThirdParty"}],
		 * 
		 * "endRecordIndex":3,"totalRecords":3,"startRecordIndex":1}
		 * 
		 * }
		 * 
		 * Replaces the string with @xmlns with empty string.
		 */

		String replacePattern = "\"@xmlns.type[a-z,A-Z,:,.,\"]*?,";
		Pattern pattern = Pattern.compile(replacePattern);
		Matcher matcher = pattern.matcher(jsonString);
		return matcher.replaceAll("");

	}

	/**
	 * Replace a single element with the [].
	 * 
	 * @param jsonString
	 * @return
	 */
	private String replaceWithParenthesisForSingleItem(String jsonString) {


		int firstColonIndex = jsonString.indexOf(":");
		int firstFlowerBracketIndex = jsonString.indexOf("}");

		String jsonObject = jsonString.substring(firstColonIndex + 1,
				firstFlowerBracketIndex + 1);

		String replaceString = "{items:[" + jsonObject + "],startRecordIndex:"
				+ "1" + ", endRecordIndex:" + "1" + ",totalRecords:" + "1"
				+ "}";
		return replaceString;

	}

	
	public void writeTo(JqRunningEvent list, Class cls, Type genericType,
			Annotation annotations[], MediaType mediaType,
			MultivaluedMap headers, OutputStream os) throws IOException,
			WebApplicationException {
		final String methodName = "writeTo";
		try {


			JAXBContext jc = JAXBContext
					.newInstance("rest.wink.resource");

			debug("context=", jc.toString());

			OutputStream tempOs = new ByteArrayOutputStream();
			Marshaller ms = jc.createMarshaller();
			HashMap<String, String> namespaceMap = new HashMap<String, String>();
			namespaceMap.put("http://www.w3.org/2001/XMLSchema-instance",
					"xmlns");

			namespaceMap.put("http://www.w3.org/2001/XMLSchema-instance",
					"xmlns.type");

			XMLOutputFactory factory = new MappedXMLOutputFactory(namespaceMap);
			XMLStreamWriter xsw = factory.createXMLStreamWriter(tempOs);

			ms.marshal(list, xsw);

			debug(methodName, "jsonString =" + tempOs.toString());

			String replacedJsonString = replaceAll(tempOs.toString());
			debug(methodName, "jsonString =" + replacedJsonString);

			// remove dojoItems tag

			int index = replacedJsonString.indexOf(":");
			int end = replacedJsonString.lastIndexOf("}");

			// For an empty list items tag won't be present, But console want it
			if (replacedJsonString.indexOf("items") > 0) {

				if (index > 0) {

					if (list.getItems().size() == 1) {
						String itemsString = replacedJsonString.substring(
								index + 1, end);
						os.write(replaceWithParenthesisForSingleItem(
								itemsString).getBytes());
					} else {
						os.write(replacedJsonString.substring(index + 1, end)
								.getBytes());
					}

				} else {
					os.write(replacedJsonString.getBytes());
				}

			} else {
				String emptJsonString = "{items:[],startRecordIndex:" + "0"
						+ ", endRecordIndex:" + "0" + ",totalRecords:" + "0"
						+ "}";
				os.write(emptJsonString.getBytes());

			}

			os.close();
			xsw.close();
			tempOs.close();

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

	}

}

