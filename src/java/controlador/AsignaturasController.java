package controlador;

import conexion.Asignaturas;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import entidades.AsignaturasFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("asignaturasController")
@SessionScoped
public class AsignaturasController implements Serializable {

    @EJB
    private entidades.AsignaturasFacade ejbFacade;
    private List<Asignaturas> items = null;
    private Asignaturas selected;

    public AsignaturasController() {
    }

    public Asignaturas getSelected() {
        return selected;
    }

    public void setSelected(Asignaturas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AsignaturasFacade getFacade() {
        return ejbFacade;
    }

    public Asignaturas prepareCreate() {
        selected = new Asignaturas();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/utilidades/Bundle").getString("AsignaturasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/utilidades/Bundle").getString("AsignaturasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/utilidades/Bundle").getString("AsignaturasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Asignaturas> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/utilidades/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/utilidades/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Asignaturas getAsignaturas(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Asignaturas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Asignaturas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Asignaturas.class)
    public static class AsignaturasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsignaturasController controller = (AsignaturasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asignaturasController");
            return controller.getAsignaturas(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Asignaturas) {
                Asignaturas o = (Asignaturas) object;
                return getStringKey(o.getAsiId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Asignaturas.class.getName()});
                return null;
            }
        }

    }

}
