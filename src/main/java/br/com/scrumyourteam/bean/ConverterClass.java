package br.com.scrumyourteam.bean;
import br.com.scrumyourteam.domain.Task;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

//@FacesConverter(value = "classeConverter")    
@FacesConverter(forClass = Task.class)
public class ConverterClass implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Task) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Task) {
            Task entity= (Task) value;
            if (entity != null && entity instanceof Task && entity.getIdTask()!= 0) {
                uiComponent.getAttributes().put( entity.toString(), entity);
                return entity.toString();
            }
        }
        return "";
    }
}