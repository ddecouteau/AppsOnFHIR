/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Runo;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;
import gov.va.ehtac.appsonfhir.session.SessionAttributes;
import gov.va.ehtac.fhir.hcs.client.HCSLogger;
import gov.va.ehtac.fhir.hcs.client.HCSLogger_Service;
import gov.va.ehtac.fhir.hcs.client.Hcslogs;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import org.w3c.dom.Element;
import ca.uhn.fhir.util.PrettyPrintWriterWrapper;
import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Label;
import java.io.ByteArrayOutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Duane DeCouteau
 */
@SuppressWarnings("serial")
public class HCSLogs extends NavigationView {
    SessionAttributes session;
    private Table logTable;
    Popover popover;
    
    public HCSLogs() {
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();           
        setCaption("Standards Log Output");
        final VerticalComponentGroup content = new VerticalComponentGroup();

        createTable();
        
        content.addComponent(logTable);

        Button refresh = new Button();
        refresh.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    logTable.removeAllItems();
                    logTable.setContainerDataSource(populateHCSLogs());
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        });

        refresh.setImmediate(true);
        refresh.setIcon(FontAwesome.REFRESH);
        setRightComponent(refresh);
        


        setContent(new CssLayout(content));
    }
    
    private void createTable() {
        logTable = new Table();
//        logTable.setWidth("100%");
//        logTable.setHeight("250px");
        logTable.setMultiSelect(false);
        logTable.setSelectable(true);
        logTable.setImmediate(true); // react at once when something is selected
        logTable.setEditable(false);
        logTable.setStyleName(Runo.TABLE_SMALL);
        logTable.setContainerDataSource(populateHCSLogs());

        logTable.setColumnReorderingAllowed(true);
        logTable.setColumnCollapsingAllowed(false);
        logTable.setVisibleColumns(new Object[] {"date", "id", "resource", "slsrules", "slsresults", "ppsrules", "ppsresults"});
        logTable.setColumnHeaders(new String[] {"Date", "Resource ID", "Resource Bundle", "SLS Rules", "SLS Results", "PPS Rules", "PPS Results"});
        logTable.setColumnWidth("date", 200);
        logTable.setColumnWidth("id", 200);
        logTable.setColumnWidth("resource", 250);
        logTable.setColumnWidth("slsrules", 150);
        logTable.setColumnWidth("slsresults", 100);
        logTable.setColumnWidth("ppsrules", 100);
        logTable.setColumnWidth("ppsresults", 100);
        
        
        
    }
    
    private IndexedContainer populateHCSLogs() {
        IndexedContainer container = new IndexedContainer();
        List<Hcslogs> rList = new ArrayList();
        try {
            rList = getLogs();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        container = createIndexedContainerLogs(rList);
        return container;
        
    }
    
    private IndexedContainer createIndexedContainerLogs(Collection<Hcslogs> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("date", String.class, null);
        container.addContainerProperty("id", String.class, null);
        container.addContainerProperty("resource", Button.class, null);
        container.addContainerProperty("slsrules", String.class, null);
        container.addContainerProperty("slsresults", String.class, null);
        container.addContainerProperty("ppsrules", String.class, null);
        container.addContainerProperty("ppsresults", String.class, null);
        container.addContainerProperty("oLogs", Hcslogs.class, null);

        int i = 0;
        for (Hcslogs p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("date").setValue(convertXMLGregorianCalendar(p.getMsgdate()));
            item.getItemProperty("id").setValue(p.getMsgid());
            item.getItemProperty("resource").setValue(getButtonWithIcon(p.getFhirbundle()));
            item.getItemProperty("slsrules").setValue(p.getSlsexecrules());
            item.getItemProperty("slsresults").setValue(p.getSlsoutput());
            item.getItemProperty("ppsrules").setValue("");
            item.getItemProperty("ppsresults").setValue("");
            item.getItemProperty("oLogs").setValue(p);
        }
        return container;
    }
    
    
    private List<Hcslogs> getLogs() {
        List<Hcslogs> res = new ArrayList();
        try {
            HCSLogger_Service service = new HCSLogger_Service();
            HCSLogger port = service.getHCSLoggerPort();
            ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/HCSServices/HCSLogger?wsdl");
            res = port.getAllHCSLogEvents();

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
       return res; 
    }
    
    private String convertXMLGregorianCalendar(XMLGregorianCalendar xc) {
        String res = "";
        try {
            Date dt = xc.toGregorianCalendar().getTime();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            res = sd.format(dt);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    
    private Button getButtonWithIcon(String val) {
        Button rButton = new Button();
        rButton.setStyleName(Runo.LABEL_SMALL);
        Label lbl = new Label();
        rButton.setData(val);
        if (val.contains("Condition")) {
            rButton.setCaption("Condition Bundle");            
        }
        else if (val.contains("Observation")) {
            rButton.setCaption("Observation Bundle");
        }
        else if (val.contains("MedicationPrescription")) {
            rButton.setCaption("Medication Prescription Bundle");
        }
        else if (val.contains("Allergy")) {
            rButton.setCaption("Allergy Intolerence Bundle");
        }
        else if (val.contains("Immunization")) {
            rButton.setCaption("Immunization Bundle");
        }
        else if (val.contains("Encounter")) {
            rButton.setCaption("Encounter Bundle");
        }
        else {
            rButton.setCaption("Unknown Resource Bundle");
        }
        rButton.setIcon(new ThemeResource("../runo/icons/16/document-web.png"));
        rButton.setData(val);
        
//        rButton.addListener(new MouseEvents.ClickListener() {
//
//            @Override
//            public void click(MouseEvents.ClickEvent event) {
//                String data = (String)event.getButton().getData();
//                String title = (String)event.getButton().getCaption();
//                Popover p = getPopoverTextArea(data, title);
//                p.showRelativeTo(getNavigationBar());
//            }
//        });
        
        return rButton;
    }
    
    private Popover getPopoverTextArea(String val, String title) {
        popover = new Popover();
        popover.setModal(true);
        popover.setClosable(true);
        popover.setWidth("700px");
        popover.setHeight("550px");

        CssLayout popLayout = new CssLayout();
        popLayout.setSizeFull();
        
        NavigationView navView = new NavigationView(popLayout);
        navView.setCaption(title);

        CssLayout layout2 = new CssLayout();
        TextArea textArea = new TextArea();
        textArea.setWidth("100%");
        textArea.setHeight("490px");
        textArea.setValue(prettyUpXML(val));
        textArea.setReadOnly(true);
        textArea.setStyleName(Runo.LABEL_SMALL);
        layout2.addComponent(textArea);
        
        popLayout.addComponent(layout2);
        
        Button close = new Button(null, new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                popover.removeFromParent();
            }
        });
        close.setIcon(new ThemeResource("../runo/icons/64/cancel.png"));
        navView.setRightComponent(close);
        
        popover.setContent(navView);
        
        return popover;
    }
    
    private String prettyUpXML(String sourceXML) {
        String res = sourceXML;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter xStream = factory.createXMLStreamWriter(bos);
            PrettyPrintWriterWrapper pretty = new PrettyPrintWriterWrapper(xStream);
            pretty.writeCharacters(sourceXML);
            res = bos.toString();
            xStream.flush();
            xStream.close();
            bos.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    
}
