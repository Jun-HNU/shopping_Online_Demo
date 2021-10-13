
package com.hnu.TreeLinklist;


class SerachKeyValue{
    public static int CountHashCode1(String str)
    {
        return str.hashCode();
    }
    public static int CountHashCode2(String str){
        char[] chars = str.toCharArray();
        int sum=0;
        for(int i=0; i<chars.length; i++){
        sum=(int)chars[i]+sum;
    }

        return sum%1000;
    }
    String searchStr;
    int offset;
    int hashCode1;
    int hashCode2;

    public SerachKeyValue(String searchStr, int offset, int hashCode1, int hashCode2) {
        this.searchStr = searchStr;
        this.offset = offset;
        this.hashCode1 = hashCode1;
        this.hashCode2 = hashCode2;
    }

    public SerachKeyValue(String searchStr, int offset) {
        this.searchStr = searchStr;
        this.offset = offset;
        this.hashCode1 = CountHashCode1(searchStr);
        this.hashCode2 = CountHashCode2(searchStr);
    }
    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getHashCode1() {
        return hashCode1;
    }

    public void setHashCode1(int hashCode1) {
        this.hashCode1 = hashCode1;
    }

    public int getHashCode2() {
        return hashCode2;
    }

    public void setHashCode2(int hashCode2) {
        this.hashCode2 = hashCode2;
    }
}




public class TreeLinkList{
    public static void main(String[] args) {

        SerachKeyValue serachKeyValue = new SerachKeyValue("hi", 11);

        BinarySearchTree  searchTree = new BinarySearchTree<>();


    }
}