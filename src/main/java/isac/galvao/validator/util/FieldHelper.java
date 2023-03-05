package isac.galvao.validator.util;

import java.lang.reflect.Field;

public class FieldHelper {
    private String attributeName;
    private Object value;

    private Field fieldInstance;

    private Object targetInstance;

    public FieldHelper() {
    }

    public FieldHelper(Field fieldInstance, Object targetInstance, String superObj) {
        fieldInstance.setAccessible(true);
        try {
            this.setValue(fieldInstance.get(targetInstance));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setAttributeName(superObj != null ? superObj.concat("."+fieldInstance.getName()) : fieldInstance.getName());
        this.setFieldInstance(fieldInstance);
        this.setTargetInstance(targetInstance);
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Field getFieldInstance() {
        return fieldInstance;
    }

    public void setFieldInstance(Field fieldInstance) {
        this.fieldInstance = fieldInstance;
    }

    public Object getTargetInstance() {
        return targetInstance;
    }

    public void setTargetInstance(Object targetInstance) {
        this.targetInstance = targetInstance;
    }
}
