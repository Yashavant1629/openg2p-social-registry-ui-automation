package utilities;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.Collections;
import java.util.List;

public class TestData {
    private List<String> idType;
    private String registrantTag;
    private String gender;
    private String relation;
    private List<String> email;
    private List<String> password;
    private String groupType;
    private String groupMembershipKind;
    private String region;
    public List<String> groupName;
    private String tags;
    public String groupNameUpdated;

    public String familyName;
    public  String givenName;
    public String additionalName;
    private String individualEmail;
    private String dob;
    private String civilStatus;
    private String occupation;
    private String income;
    private String address;
    private String district;

    public String getRegion() {

        return region;
    }

    public void setRegion(String region) {

        this.region = region;
    }

    public String getGroupMembershipKind() {

        return groupMembershipKind;
    }

    public void setGroupMembershipKind(String groupMembershipKind) {

        this.groupMembershipKind = groupMembershipKind;
    }

    public String getGroupType() {

        return groupType;
    }

    public void setGroupType(String groupType) {

        this.groupType = groupType;
    }

    public String getPassword() {
        return password.toString();}

    public void setPassword(String password) {

        this.password = Collections.singletonList(password);
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {

        this.email = email;
    }

    public List<String> getIdType() {

        return idType;
    }

    public void setIdType(List<String> idType) {

        this.idType = idType;
    }

    public String getRegistrantTag() {

        return registrantTag;
    }

    public void setRegistrantTag(String registrantTag) {

        this.registrantTag = registrantTag;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    public String getRelation() {

        return relation;
    }

    public void setRelation(String relation) {

        this.relation = relation;
    }

   public List<String> getGroupname() {
        return groupName;
    }

    public void setGroupname(List<String> groupName) {
        this.groupName = groupName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAddress() {
        return address;
    }
    public String getUpdatedgroupname() {
        return groupNameUpdated;
    }
    public String getFamilyName() {
        return familyName;
    }
    public String getGivenName() {
        return givenName;
    }

    public String getAdditionalName() {
        return additionalName;
    }

    public String getDOB() {
        return dob;
    }

    public String getIndividualEmail() {
        return individualEmail;
    }
    public String getCivilStatus(){
        return civilStatus;
    }

    public String getOccupation() {
        return occupation;
    }
    public String getIncome(){
        return income;
    }
    public String getDistrict(){
        return district;
    }
}
