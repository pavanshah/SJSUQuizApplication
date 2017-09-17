package com.pvanshah.sjsuquizapplication.student;

import java.util.Arrays;

/**
 * Created by avinash on 9/13/17.
 */

public class Solution {

    public int ans() {
        int[] a = {10, 0, 8, 2, -1, 12, 11, 3};
        int[] diff = {};
        Arrays.sort(a);
        for(int i=0; i<a.length; i++) {
            diff[i] = Math.abs(a[i] - a[i+1]);
        }
        return diff[diff.length-1];
    }

}
