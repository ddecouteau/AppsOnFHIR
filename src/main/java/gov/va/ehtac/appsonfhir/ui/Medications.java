package gov.va.ehtac.appsonfhir.ui;

import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.dstu2.composite.QuantityDt;
import ca.uhn.fhir.model.dstu2.resource.Medication;
import ca.uhn.fhir.model.dstu2.resource.MedicationPrescription;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.parser.XmlParser;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;
import gov.va.ehtac.appsonfhir.dataresults.MedicationPrescriptionResults;
import gov.va.ehtac.appsonfhir.session.SessionAttributes;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hl7.fhir.hcsclient.ApplySecurityLabelsFHIRForResourceReleaseResponse;
import org.hl7.fhir.hcsclient.HCSOrchestrator;
import org.hl7.fhir.hcsclient.HCSOrchestratorService;
import org.hl7.fhir.hcsclient.HcsCategory;

@SuppressWarnings("serial")
public class Medications extends NavigationView {
    private Table medicationTable;
    SessionAttributes session;
    Bundle bundle;

    public Medications() {
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("Patient Medications - "+session.getPatientNameAgeGenderDisplay());
        final VerticalComponentGroup content = new VerticalComponentGroup();

        createTable();
        
        content.addComponent(medicationTable);


        final Button submitButton = new Button("Submit");
        submitButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Thanks!");
            }
        });
        Button b = new Button("HCS");
        b.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    HCSOrchestratorService service = new HCSOrchestratorService();
                    HCSOrchestrator port = service.getHCSOrchestratorPort();
                    List<HcsCategory> hcs = new ArrayList();
                    ApplySecurityLabelsFHIRForResourceReleaseResponse.Return r = port.applySecurityLabelsFHIRForResourceRelease(composeXMLStringFeed(bundle), "1", hcs, "TREAT");
                    System.out.println(r.getProcessedAtomFeed());
                    //HcsTaggingResponse res = port.genFHIRConfidentialityLabels(composeXMLStringFeed(bundle), hcs);
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        });

        b.setIcon(FontAwesome.SHARE);
        setRightComponent(b);
        setContent(new CssLayout(content));
    }
    
    private void createTable() {
        medicationTable = new Table();
        medicationTable.setWidth("100%");
        medicationTable.setHeight("600px");
//        medicationTable.setHeight("250px");
        medicationTable.setMultiSelect(false);
        medicationTable.setSelectable(true);
        medicationTable.setImmediate(true); // react at once when something is selected
        medicationTable.setEditable(false);
        medicationTable.setContainerDataSource(populatePatientConditions());

        medicationTable.setColumnReorderingAllowed(true);
        medicationTable.setColumnCollapsingAllowed(false);
        medicationTable.setVisibleColumns(new Object[] {"selected", "displayName", "status", "quantitydispensed", "refills", "instructions"});
        medicationTable.setColumnHeaders(new String[] {"", "Medication Name", "Status","Quantity", "Refills", "Dosage Instructions"});
        
        
    }
    
    
    private Bundle getMedications() {
        //for time being just do this

        Bundle medications = null;
        try {
            IGenericClient client = ((HealthElementsTouchKitUI) UI.getCurrent()).getFhirClient();
            medications = client.search(new UriDt(getMedicationPrescriptionSearchString()));
            System.out.println("Number of Medications Returned: "+medications.getEntries().size());
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        bundle = medications;
        return medications;
    }
    
    private IndexedContainer populatePatientConditions() {
        IndexedContainer container = new IndexedContainer();
        List<MedicationPrescriptionResults> rList = new ArrayList();
        try {
            Bundle medications = getMedications();
            List<BundleEntry> lMedications = medications.getEntries();
            System.out.println("NUMBER OF Medications: "+lMedications.size());
            Iterator iter = lMedications.iterator();
            while (iter.hasNext()) {
                try {
                    BundleEntry entry = (BundleEntry)iter.next();
                    MedicationPrescription med = (MedicationPrescription)entry.getResource();
                    MedicationPrescriptionResults obj = new MedicationPrescriptionResults();
                    Medication rMed = (Medication)med.getContained().getContainedResources().get(0);
                    obj.setDisplayName(rMed.getCode().getCoding().get(0).getDisplay());
                    obj.setStatus(med.getStatus());
                    obj.setInstructions(med.getDosageInstruction().get(0).getText());
                    //quantity dispensed
                    String quantityDispensed = "";
                    try {
                        QuantityDt q = med.getDispense().getQuantity();
                        quantityDispensed = q.getValue().toString();
                    } catch (Exception ex) {}
                    obj.setQuantityDispensed(quantityDispensed);
                    //number of refills
                    String refill = "";
                    try {
                        refill = med.getDispense().getNumberOfRepeatsAllowed().toString();
                    } catch (Exception ex) {}
                    obj.setRefills(refill);
                    //start date
                    //DateAndTime dt = med.getDispense().getExpectedSupplyDuration().
                    //obj.setStartDate(dt.toHumanDisplay());
                    obj.setPayload(med);
                    rList.add(obj);
                }
                catch (Exception ex) {
                    System.out.println("****ERROR "+ex.getMessage());
                    ex.printStackTrace();
                }
            }   
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        container = createIndexedContainerConditions(rList);
        return container;
    }
    
    private IndexedContainer createIndexedContainerConditions(Collection<MedicationPrescriptionResults> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("selected", CheckBox.class, null);
        container.addContainerProperty("displayName", String.class, null);
        container.addContainerProperty("status", String.class, null);
        container.addContainerProperty("startdate", String.class, null);
        container.addContainerProperty("quantitydispensed", String.class, null);
        container.addContainerProperty("refills", String.class, null);
        container.addContainerProperty("instructions", String.class, null);
        container.addContainerProperty("oMedicationPrescriptions", MedicationPrescriptionResults.class, null);

        int i = 0;
        for (MedicationPrescriptionResults p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("selected").setValue(new CheckBox());
            item.getItemProperty("displayName").setValue(p.getDisplayName());
            item.getItemProperty("status").setValue(p.getStatus());
            item.getItemProperty("startdate").setValue(p.getStartDate());
            item.getItemProperty("quantitydispensed").setValue(p.getQuantityDispensed());
            item.getItemProperty("refills").setValue(p.getRefills());
            item.getItemProperty("instructions").setValue(p.getInstructions());
            item.getItemProperty("oMedicationPrescriptions").setValue(p);
        }
        return container;
    }
    
    private String composeXMLStringFeed(Bundle feed) {
        String res = "";
        try {
            XmlParser xmlP = new XmlParser(((HealthElementsTouchKitUI) UI.getCurrent()).getFhirContext());
            res = xmlP.encodeBundleToString(feed);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }    
//    
//    private String composeXMLString(Resource obj) {
//        String res = "";
//        try {
//            XmlComposer xml = new XmlComposer();
//            ByteArrayOutputStream bo = new ByteArrayOutputStream();
//            xml.compose(bo, obj, true);
//            res = new String(bo.toByteArray());
//            bo.close();
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return res;
//    }
    private String getMedicationPrescriptionSearchString() {
        String res = session.getBaseURL()+"MedicationPrescription?patient=Patient/patient-"+session.getPatientId();
        System.out.println(res);
        return res;
    }

}
