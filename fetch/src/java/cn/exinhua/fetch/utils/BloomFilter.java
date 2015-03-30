/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.exinhua.fetch.utils;

import java.util.BitSet;

public class BloomFilter {

    public BitSet bitSet = new BitSet(2 << 24);
    public int seeds[] = {3, 7, 11, 13, 31, 37, 61};

    public BloomFilter() {
    }

    int getHashValue(String str, int n) {
        int result = 0;

        for (int i = 0; i < str.length(); i++) {
            result = seeds[n] * result + (int) str.charAt(i);
            if (result > 2 << 24) {
                result %= 2 << 24;
            }
        }
        return result;
    }

    public boolean contains(String str) {

        for (int i = 0; i < 7; i++) {
            int hash = getHashValue(str, i);
            if (bitSet.get(hash) == false) {
                return false;
            }
        }
        return true;
    }

    public void add(String str) {

        for (int i = 0; i < 7; i++) {
            int hash = getHashValue(str, i);
            bitSet.set(hash, true);
        }
    }
    
    public static void main(String[] args){
        BloomFilter filter=new BloomFilter();
        String baseurl="http://www.baidu.com";
        for(int i=0;i<10000;i++){
            System.out.println(filter.contains(baseurl+i%100));
            filter.add(baseurl+i);
        }
    }
}
