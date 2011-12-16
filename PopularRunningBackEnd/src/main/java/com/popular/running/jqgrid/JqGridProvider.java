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

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.MappedXMLOutputFactory;

import com.popular.running.utils.SwissArmyKnife;


@Provider
@Produces(value = { SwissArmyKnife.MEDIA_TYPE_JQGRID })
@SuppressWarnings("rawtypes")
/**
 * JqGridProvider
 * Apache Wink Provider for JqGrid component (http://www.trirand.com/blog/)
 * based on DW article http://www.ibm.com/developerworks/web/library/wa-dojotree/ 
 * 
 */
public class JqGridProvider implements MessageBodyWriter<JqGridList> {

	/**
	 * @see javax.ws.rs.ext.MessageBodyReader#isReadable(java.lang.Class,
	 *      java.lang.reflect.Type, java.lang.annotation.Annotation[],
	 *      javax.ws.rs.core.MediaType)
	 */
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
	public JqGridList readFrom(Class<JqGridList> inputClass, Type type,
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
	public long getSize(JqGridList arg0, Class<?> arg1, Type arg2,
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

		return inputClass == JqGridList.class;
	}

	/**
	 * @see javax.ws.rs.ext.MessageBodyWriter#writeTo(java.lang.Object,
	 *      java.lang.Class, java.lang.reflect.Type,
	 *      java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType,
	 *      javax.ws.rs.core.MultivaluedMap, java.io.OutputStream)
	 */
	public void writeTo(JqGridList list, Class<?> inputClass, Type type,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> map, OutputStream os)
			throws IOException, WebApplicationException {

		JAXBContext jc;
		final String methodName = "writeTo";
		try {

			jc = JAXBContext.newInstance("rest.wink.resource");

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

			debug(methodName, "jsonString before replace =" + tempOs.toString());

			String replacedJsonString = replaceAll(tempOs.toString());
			debug(methodName, "jsonString =" + replacedJsonString);

			// remove dojoItems tag

			int index = replacedJsonString.indexOf(":");
			int end = replacedJsonString.lastIndexOf("}");
			
			// For an empty list items tag won't be present, But console want it
			if (replacedJsonString.indexOf("items") > 0)
			{

			// For an empty list items tag won't be present, But console want it
			if (replacedJsonString.indexOf("items") > 0) {

				if (index > 0) {

					if(list.getItems().size() == 1)
					{
						String itemsString = replacedJsonString.substring(index + 1, end);
						debug(methodName, "jsonString itemsString=" + itemsString);
						os.write(replaceWithParenthesisForSingleItem(itemsString,list).getBytes());
					}
					else
					{
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
			
			}
			else
			{
				String emptJsonString = "{items:[],startRecordIndex:" + "0" +", endRecordIndex:" + "0" + ",totalRecords:" + "0" + "}";
				os.write(emptJsonString.getBytes());
				
			}
			os.close();
			xsw.close();
			tempOs.close();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

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
		 * {"dojoItems":{"items":[{"@xmlns.type.type":"xs:string","$":"IBM"},
		 * 
		 * {"@xmlns.type.type":"xs:string","$":"ViaCore"},
		 * 
		 * {"@xmlns.type.type":"xs:string","$":"ThirdParty"}],
		 * 
		 * "endRecordIndex":3,"totalRecords":3,"startRecordIndex":1}
		 * 
		 * Replaces the string with @xmlns with empty string.
		 * 
		 * }
		 */

		String replacePattern = "\"@xmlns.type[a-z,A-Z,:,.,\"]*?,";
		Pattern pattern = Pattern.compile(replacePattern);
		Matcher matcher = pattern.matcher(jsonString);
		return matcher.replaceAll("");
		
	}

	/**
	 * Replace a single element with the [].
	 * @param jsonString
	 * @return
	 */
	private String replaceWithParenthesisForSingleItem(String jsonString, JqGridList list) {

		// Replace
		// {\"items\":{\"partnerName\":\"Dept90\",\"status\":\"Enabled\"},\"endRecordIndex\":1,
		// \"totalRecords\":1,\"startRecordIndex\":1}
		
		
		/*
		  {"items":{"fromId":1,"fromOrganizationName":"Partner4","id":381,"operationMode"
		  : {"id":1,"mode":"PRODUCTION","resourceKey":"PRODUCTION"},
		  "policyName":"policy1","service":"AS Unpack","serviceType":
		  {"resourcekey"
		  :"bcgex_partnermanagement_b2bPolicy_inboundUnpackaging",
		  "type":"Inbound unpackaging"},"status":"disabled",
		  "toId":21,"toOrganizationName":"Partner3","version":1}
		  ,"endRecordIndex":0,"totalRecords":1,"startRecordIndex":0}
		 */
		
		
		final String methodName = "replaceWithParenthesisForSingleItem";

		int firstColonIndex = jsonString.indexOf(":");
		int endRecordIndex = jsonString.indexOf("endRecordIndex");
		
		int flowerBracketBeforeEndRecordIndexString = jsonString
		.lastIndexOf("}",endRecordIndex);

		String jsonObject = jsonString.substring(firstColonIndex + 1,
				flowerBracketBeforeEndRecordIndexString + 1);
		
//		String replaceString = "{items:[" + jsonObject + "],startRecordIndex:"
//				+ list.getStartRecordIndex() + ", endRecordIndex:" + list.getEndRecordIndex() + ",totalRecords:" + list.getTotalRecords()
//				+ "}";
//		
//		debug(methodName, "jsonString after replace=" + replaceString);
//		return replaceString;
		return "";

	}



}
