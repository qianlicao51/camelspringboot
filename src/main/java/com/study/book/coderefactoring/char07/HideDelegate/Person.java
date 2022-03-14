package com.study.book.coderefactoring.char07.HideDelegate;

/**
 * @author MI
 * @ClassName pERSON.java
 * @createTime 2021年10月18日 18:31:00
 */
public class Person {
    Department _department;

    public Department getDepartment() {
        return _department;
    }

    public void set_department(Department _department) {
        this._department = _department;
    }

    Person getManager() {
        return _department.get_manger();
    }
}

class Department {
    private String _chargeCode;
    private Person _manger;

    public Person get_manger() {
        return _manger;
    }

    public Department(Person manger) {
        this._manger = manger;
    }
}
