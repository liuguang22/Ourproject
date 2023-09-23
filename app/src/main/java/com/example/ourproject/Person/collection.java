package com.example.ourproject.Person;

import com.example.ourproject.home.Record;

import java.util.List;

public class collection {
    private Integer current;
    private List<Record> records;
    private Integer size;
    private Integer total;

    public Integer getCurrent() {
        return current;
    }

    public List<Record> getRecords() {
        return records;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getTotal() {
        return total;
    }

    public collection(){
    }

    @Override
    public String toString() {
        return "shop{" +
                "current=" + current +
                ", record'" + records  +
                ", size=" + size +
                ", total=" + total +

                '}';

    }


}

