package core;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author csommers This Class of the Event
 *
 */
public class Event {

	private String classID;
	private String classTypeName;
	private String value;
	private Date eventDate;
	private String eventType;
	private String invoker;

	public String getClassID() {
		return classID;
	}

	public String getClassTypeName() {
		return classTypeName;
	}

	public String getValue() {
		return value;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public String getEventType() {
		return eventType;
	}

	public String getInvoker() {
		return invoker;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setInvoker(String invoker) {
		this.invoker = invoker;
	}

	/**
	 * @param classID
	 * @param classTypeName
	 * @param eventDate
	 * @param eventType
	 * @param invoker
	 * @param value
	 */
	public Event(String classID, String classTypeName, Date eventDate, String eventType, String invoker, String value) {
		super();
		this.classID = classID;
		this.classTypeName = classTypeName;
		this.eventDate = eventDate;
		this.eventType = eventType;
		this.invoker = invoker;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Event [classID=" + classID + ", classTypeName=" + classTypeName + ", value=" + value + ", eventDate="
				+ eventDate + ", eventType=" + eventType + ", invoker=" + invoker + "]";
	}

	/**
	 * 
	 * @return json of this Event
	 * @throws Exception
	 */
	public String toJson() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String serializedValue = "";
		try {
			serializedValue = mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			throw new Exception("unable to serialize event to json.");
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new Exception("unable to serialize event to json.");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("unable to serialize event to json.");
		}

		return serializedValue;
	}

}
