package lv.autentica.dto.staff;

import lv.autentica.domain.staff.Staff;

public class StaffDTO {
    public StaffDTO() {

    }
    public StaffDTO(Staff staff) {
        this.id = staff.getId();
        this.address = staff.getAddress();
        this.name = staff.getFirstName();
        this.phone = staff.getPhone();
        this.salary = staff.getSalary();
        this.ssn = staff.getSsnNumber();
        this.staffTypeId = staff.getPosition().getId();
        this.lastName = staff.getLastName();
    }

    private Long id;

    private String name;
    private Long ssn;
    private Long phone;
    private Double salary;
    private String address;
    private Long staffTypeId;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSsn() {
        return ssn;
    }

    public void setSsn(Long ssn) {
        this.ssn = ssn;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getStaffTypeId() {
        return staffTypeId;
    }

    public void setStaffTypeId(Long staffTypeId) {
        this.staffTypeId = staffTypeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
