/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.session;

/**
 *
 * @author Duane DeCouteau
 */
public class SessionAttributes {
    //local smart-api
    //private String baseURL = "http://192.168.1.149:8080/";
    private String baseURL = "http://mhs.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/";
    private String patientId = "1134281"; //"1482713"; //"1134281";
    private String patientNameAgeGenderDisplay = "No Patient Selected";
    private String getConnectedServer = "Military Health Systems";
    private String userId = "drbob";
    private String providerId = "drbob@mhs.edmondsci.com";
    private String purposeOfUse = "TREAT";
    private String securityLevel = "V";

    /**
     * @return the baseURL
     */
    public String getBaseURL() {
        return baseURL;
    }

    /**
     * @param baseURL the baseURL to set
     */
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * @return the patientId
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the patientNameAgeGenderDisplay
     */
    public String getPatientNameAgeGenderDisplay() {
        return patientNameAgeGenderDisplay;
    }

    /**
     * @param patientNameAgeGenderDisplay the patientNameAgeGenderDisplay to set
     */
    public void setPatientNameAgeGenderDisplay(String patientNameAgeGenderDisplay) {
        this.patientNameAgeGenderDisplay = patientNameAgeGenderDisplay;
    }

    /**
     * @return the getConnectedServer
     */
    public String getGetConnectedServer() {
        return getConnectedServer;
    }

    /**
     * @param getConnectedServer the getConnectedServer to set
     */
    public void setGetConnectedServer(String getConnectedServer) {
        this.getConnectedServer = getConnectedServer;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    /**
     * @return the purposeOfUse
     */
    public String getPurposeOfUse() {
        return purposeOfUse;
    }

    /**
     * @param purposeOfUse the purposeOfUse to set
     */
    public void setPurposeOfUse(String purposeOfUse) {
        this.purposeOfUse = purposeOfUse;
    }

    /**
     * @return the securityLevel
     */
    public String getSecurityLevel() {
        return securityLevel;
    }

    /**
     * @param securityLevel the securityLevel to set
     */
    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }
    
}
