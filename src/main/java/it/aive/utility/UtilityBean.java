package it.aive.utility;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class UtilityBean {
    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new UtilityBean object.
     */
    public UtilityBean() {
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param name
     * @param target
     * @param value
     *
     * @throws Exception
     */
    public static void setProperty(String name, Object target, Object value) throws Exception {
	Class sourceClass = target.getClass();
	BeanInfo info = Introspector.getBeanInfo(sourceClass);
	PropertyDescriptor[] props = info.getPropertyDescriptors();

	for (int i = 0; i < props.length; i++) {
	    if (props[i].getName().equals(name)) {
		Method setter = props[i].getWriteMethod();

		// Se la preoperty e' di tipo stringa forzo comunque l'oggetto a string per 
		if (props[i].getPropertyType().getName().equalsIgnoreCase("java.lang.String")) {
		    if (value != null) {
			Object[] parm = new Object[1];
			parm[0] = value.toString();
			setter.invoke(target, parm);
		    }
		} else {
		    Object[] parm = new Object[1];
		    parm[0] = value;
		    setter.invoke(target, parm);
		}
	    }
	}
    }

    protected static boolean isMap(Class sourceClass) throws Exception {
	boolean retVal = false;
	Class[] interfacce = sourceClass.getInterfaces();
	for (int i = 0; i < interfacce.length; i++) {

	    if (interfacce[i].getName().equalsIgnoreCase("java.util.Map")) {
		retVal = true;
		break;
	    }

	}
	return retVal;
    }

    /**
     * DOCUMENT ME!
     *
     * @param name
     * @param target
     *
     * @return
     *
     * @throws Exception
     */
    public static Object getProperty(String name, Object target) throws Exception {
	Class sourceClass = target.getClass();
	Object retVal = null;
	if (isMap(sourceClass)) {
	    Map tmp = (Map) target;
	    retVal = tmp.get(name);
	} else {
	    BeanInfo info = Introspector.getBeanInfo(sourceClass);
	    PropertyDescriptor[] props = info.getPropertyDescriptors();

	    for (int i = 0; i < props.length; i++) {
		if (props[i].getName().equals(name)) {
		    Method getter = props[i].getReadMethod();

		    // Se la preoperty e' di tipo stringa forzo comunque l'oggetto a string per 
		    Object[] noParams = new Object[0];
		    retVal = getter.invoke(target, noParams);

		    break;
		}
	    }
	}
	return retVal;
    }

    /**
     * DOCUMENT ME!
     *
     * @param source
     *
     * @return
     *
     * @throws Exception
     */
    public static Map describe(Object source) throws Exception {
	Hashtable retVal = new Hashtable();
	Class sourceClass = source.getClass();
	BeanInfo info = Introspector.getBeanInfo(sourceClass);
	PropertyDescriptor[] props = info.getPropertyDescriptors();
	Object[] noParams = new Object[0];

	for (int i = 0; i < props.length; i++) {
	    Method getter = props[i].getReadMethod();

	    if (getter == null) {
		continue;
	    }

	    Object value = getter.invoke(source, noParams);

	    if (value != null) {
		retVal.put(props[i].getName(), value);
	    }
	}

	retVal.remove("class");

	return retVal;
    }

    /**
     * DOCUMENT ME!
     *
     * @param source
     * @param dest
     *
     * @throws Exception
     */
    public static void copy(Object source, Object dest) throws Exception {
	Class sourceClass = source.getClass();
	Class destClass = dest.getClass();
	BeanInfo info = Introspector.getBeanInfo(sourceClass);
	PropertyDescriptor[] props = info.getPropertyDescriptors();
	Object[] noParams = new Object[0];
	Object[] oneParam = new Object[1];

	for (int i = 0; i < props.length; i++) {
	    Method getter = props[i].getReadMethod();

	    if (getter == null) {
		continue;
	    }

	    Object value = getter.invoke(source, noParams);
	    Method setter = props[i].getWriteMethod();

	    if ((setter != null) && (sourceClass != destClass)) {
		try {
		    setter = destClass.getMethod(setter.getName(), setter.getParameterTypes());
		} catch (NoSuchMethodException x) {
		    setter = null;
		}
	    }

	    if (setter != null) {
		oneParam[0] = value;
		setter.invoke(dest, oneParam);
	    }
	}
    }

    /**
     * DOCUMENT ME!
     *
     * @param obj  
     *
     * @return  
     */
    public static boolean isCollection(Class obj) {
	boolean retVal = false;
	Class[] interfacce = obj.getInterfaces();

	if (obj.getName().equalsIgnoreCase("java.util.collection")) {
	    retVal = true;

	    return retVal;
	}

	if (interfacce != null) {
	    for (int i = 0; i < interfacce.length; i++) {
		if (interfacce[i].getName().equalsIgnoreCase("java.util.collection")) {
		    retVal = true;

		    break;
		}
	    }
	}

	return retVal;
    }

    /**
     * DOCUMENT ME!
     *
     * @param source  
     * @param dest  
     *
     * @throws Exception  
     */
    public static void copyNoCollection(Object source, Object dest) throws Exception {
	Class sourceClass = source.getClass();
	Class destClass = dest.getClass();
	BeanInfo info = Introspector.getBeanInfo(sourceClass);
	PropertyDescriptor[] props = info.getPropertyDescriptors();
	Object[] noParams = new Object[0];
	Object[] oneParam = new Object[1];

	for (int i = 0; i < props.length; i++) {
	    Method getter = props[i].getReadMethod();

	    if (getter == null) {
		continue;
	    }

	    if (!UtilityBean.isCollection(getter.getReturnType())) {
		//
		Object value = getter.invoke(source, noParams);

		Method setter = props[i].getWriteMethod();

		if ((setter != null) && (sourceClass != destClass)) {
		    try {
			setter = destClass.getMethod(setter.getName(), setter.getParameterTypes());
		    } catch (NoSuchMethodException x) {
			setter = null;
		    }
		}

		if (setter != null) {
		    oneParam[0] = value;
		    setter.invoke(dest, oneParam);
		}
	    }
	}
    }

    /**
     * DOCUMENT ME!
     *
     * @param source  
     * @param dest  
     * @param exludedProp  
     *
     * @throws Exception  
     */
    public static void copy(Object source, Object dest, List exludedProp) throws Exception {
	Class sourceClass = source.getClass();
	Class destClass = dest.getClass();
	BeanInfo info = Introspector.getBeanInfo(sourceClass);
	PropertyDescriptor[] props = info.getPropertyDescriptors();
	Object[] noParams = new Object[0];
	Object[] oneParam = new Object[1];

	for (int i = 0; i < props.length; i++) {
	    // Se non e' nella lista dei metodi da non copiare
	    if (!exludedProp.contains(props[i].getReadMethod())) {
		Method getter = props[i].getReadMethod();

		if (getter == null) {
		    continue;
		}

		Object value = getter.invoke(source, noParams);
		Method setter = props[i].getWriteMethod();

		if ((setter != null) && (sourceClass != destClass)) {
		    try {
			setter = destClass.getMethod(setter.getName(), setter.getParameterTypes());
		    } catch (NoSuchMethodException x) {
			setter = null;
		    }
		}

		if (setter != null) {
		    oneParam[0] = value;
		    setter.invoke(dest, oneParam);
		}
	    }
	}
    }
}
