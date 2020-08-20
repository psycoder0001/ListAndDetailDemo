package com.ewo.laddemo.localdb;

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class PersonModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;
    public int loanTotal;
    public int loanPaid;
    public int viewCountValue;
    public int viewCounterType;

    public PersonModel() {
        name = "N/A";
    }

    public PersonModel(String name, int totalLoan, int loanPaid, EnumViewCounterType viewCounterType) {
        this.name = name;
        this.loanTotal = totalLoan;
        this.loanPaid = loanPaid;
        this.viewCounterType = viewCounterType.getId(); // manuel mapping is safer
//        this.viewIncrementType = viewIncrementType.ordinal(); // index of the enum.
    }

    public String getTotal() {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00 TL");
        return formatter.format(loanTotal);
    }

    public String getPaid() {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00 TL");
        return formatter.format(loanPaid);
    }

    public EnumViewCounterType getViewCounterType() {
        return EnumViewCounterType.parse(viewCounterType);
    }
}
