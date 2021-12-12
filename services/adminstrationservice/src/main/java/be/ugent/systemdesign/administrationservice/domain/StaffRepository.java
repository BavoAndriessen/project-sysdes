package be.ugent.systemdesign.administrationservice.domain;

public interface StaffRepository {
    public Staff findOne(Integer id);
    public void save(Staff _f);
}
