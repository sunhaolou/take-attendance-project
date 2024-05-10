package edu.duke.ece651.project.team5.shared.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import edu.duke.ece651.project.team5.shared.utils.PasswordHasher;
import javax.persistence.Table;

@Entity
@Table(name = "professors")
public class Professor extends Person {

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "course_instructor", joinColumns = { @JoinColumn(name = "netid") }, inverseJoinColumns = {
            @JoinColumn(name = "course_section_id") })
    private Set<Section> courseSections;

    public Professor() {
        super("netId", "password", "legalName", "email", "phone");
    }
    // courseSectionsID

    // public Professor() {
    // super("", "");
    // }
    public Professor(String netId, String legalName, String email, String phone, String password) {

        this(netId, legalName, email, phone, password, new HashSet<String>());
    }

    public Professor(String netId, String legalName, String email, String phone, String password,
            Set<String> courseSections) {

        super(netId, password, legalName, email, phone);

        // this.courseSections = courseSections;
    }

    public Professor(String netId, String password) {
        this(netId, "name", "email", "phone", password);
    }

    public String getNetId() {
        return super.getNetId();
    }

    public void setNetId(String username) {
        super.setNetId(username);
        ;
    }

    public String getLegalName() {
        return super.getLegalName();
    }

    public void setLegalName(String legalName) {
        super.setLegalName(legalName);
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    public String getPhone() {
        return super.getPhone();
    }

    public List<String> getCourseSectionIds() {
        List<String> courseSectionIds = new ArrayList<>();
        for (Section section : courseSections) {
            courseSectionIds.add(section.getCourseSectionId());
        }
        return courseSectionIds;
    }

    public Set<Section> getCourseSections() {
        return courseSections;
    }

    public void setCourseSections(Set<Section> courseSections) {
        this.courseSections = courseSections;
    }

    public void addCourseSection(Section section) {
        this.courseSections.add(section);
    }
    // public Set<String> getCourseSections() {
    // return courseSections;
    // }

    // public void setcourseSections(Set<String> courseSections) {
    // this.courseSections = courseSections;
    // }

    // public void addcourseSections(String courseSections) {
    // this.courseSections.add(courseSections);
    // }

    // // TODO
    // public boolean hadCourse(String courseId) {
    // for (String section : courseSections) {
    // if (section.getCourseId().equals(courseId)) {
    // return true;
    // }
    // }
    // return false;
    // }

    // // TODO
    // public Section getSectionBySectionId(String sectionId) {
    // for (String section : sections) {
    // if (section.getSectionId().equals(sectionId)) {
    // return section;
    // }
    // }
    // return null;
    // }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

}
