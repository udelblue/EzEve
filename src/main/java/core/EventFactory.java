package core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Function;

import javax.persistence.Id;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author csommers EventFactory class generates a return of class type Event
 */
public class EventFactory {

	
	public List<Event> create(Collection<Object> objects, EventType eventType  ) throws Exception {
		return  this.create(objects, eventType, null);
	}
	
	public List<Event> create(Collection<Object> objects, EventType eventType,  String invokerName  ) throws Exception {
		List<Event> events = new ArrayList<Event>();

		objects.parallelStream().forEach(o ->  {
			try {
				events.add(this.create(o, eventType, invokerName ));
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
		});
		
		return events;
	}
	
	
	public Event create(Object object, EventType eventType, String invokerName) throws Exception {
		return this.create(object, eventType.toString(), invokerName);
	}

	public Event create(Object object, EventType eventType) throws Exception {
		return this.create(object, eventType.toString(), null);
	}

	public Event create(Object object, String eventType) throws Exception {
		return this.create(object, eventType.toString(), null);
	}

	public Event create(Object object, String eventType, String invokerName) throws Exception {
		return this.create(object, eventType, null, invokerName);
	}

	public Event create(Object object, String eventType, String classId, String invokerName,  Function<String,String> func) throws Exception {
		Event  e = this.create(object, eventType, classId, invokerName);
		// Serialized event
		ObjectMapper mapper = new ObjectMapper();
		String serializedEvent = mapper.writeValueAsString(object);
		func.apply(serializedEvent);
		return e;
	}
	
	
	
	public Event create(Object object, String eventType, String classId, String invokerName) throws Exception {

		// String ID
		String classID = "";

		// get class
		@SuppressWarnings("rawtypes")
		Class c = object.getClass();
		String classTypeName = c.getTypeName();

		// check if invoker is null
		if (invokerName == null) {
			invokerName = "";
		}

		// get id if classId if null;
		if (classId == null) {
			try {

				// get id annotations
				Package p = c.getPackage();
				@SuppressWarnings("unused")
				String packageName = "";
				if (p != null) {
					packageName = p.getName();
				}

				// enable field annotation scanner
				Reflections r = new Reflections(classTypeName, new FieldAnnotationsScanner());

				Set<Field> fields = r.getFieldsAnnotatedWith(Id.class);

				@SuppressWarnings({ "unused", "rawtypes" })
				Class fieldType = null;
				// Get value from field
				if (fields != null && !fields.isEmpty()) {

					for (Field f : fields) {
						String flname = f.getDeclaringClass().getName();
						if (flname.equals(classTypeName)) {
							if (f != null) {
								fieldType = f.getType();
							}
							Object v = null;
							try {
								v = f.get(object);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
								throw new Exception("unable to get field value.");
							} catch (IllegalAccessException e) {
								e.printStackTrace();
								throw new Exception("unable to get field value.");
							}
							if (v != null) {

								// check if field id type is string, int, long,
								// uuid,
								// double
								if (v instanceof Integer) {
									int i = (Integer) v;
									classID = Integer.toString(i);
								} else if (v instanceof String) {
									classID = v.toString();
								} else if (v instanceof Long) {
									Long i = (Long) v;
									classID = Long.toString(i);
								} else if (v instanceof Double) {
									Double i = (Double) v;
									classID = Double.toString(i);
								} else if (v instanceof UUID) {
									UUID i = (UUID) v;
									classID = i.toString();
								}

							}
						}
					}

				}

			} catch (Exception e) {
				throw e;

			}
		} else {
			classID = classId;
		}

		// Class Type Name
		// String classTypeName = "";// object.getTypeName();

		// Current datetime
		Date date = new Date();

		// Serialized value
		ObjectMapper mapper = new ObjectMapper();
		String serializedValue = mapper.writeValueAsString(object);

		return new Event(classID, classTypeName, date, eventType, invokerName, serializedValue);

	}

}
