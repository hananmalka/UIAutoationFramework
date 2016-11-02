package com.ui.automation.reporter.impl;

import java.util.Comparator;

/**
 * Created by daraf on 3/18/2015.
 */
public class SortTestsList implements Comparator<TestMethodDetails> {


    @Override
    public int compare(TestMethodDetails tmd1, TestMethodDetails tmd2) {
        return tmd1.status.getSize() - tmd2.status.getSize();
    }
}
