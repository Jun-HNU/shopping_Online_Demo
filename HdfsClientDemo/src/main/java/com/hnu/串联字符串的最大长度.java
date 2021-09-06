package com.hnu;

import java.util.*;


/// 0 1 2 3 4
/*0
01
012
01234
1
12
123
1234
2
23
234
3
34

["a", "abc", "d", "de", "def"]


0000
0x0000 0000
0x0000 0000


/
 */


public class 串联字符串的最大长度 {

    public static void main(String[] args) {
        String[] stringlist = {"a", "abc", "d", "de", "def"};

        System.out.println(maxLength(Arrays.asList(stringlist)));



    }

    public static List<Integer> getBinOneCount(int n){
        List<Integer> ar = new ArrayList<>();
        int index=0;
        while(n>0){
            int x=n&1<<index;
            if(x!=0){
                ar.add(index+1);
                n=n-(1<<index);

            }
            index++;
        }
        return ar;
    }





    public static int maxLength(List<String> arr) {

        Set<Set<String>> StringSet=new HashSet<>();
        int maxLength=0;


                    for (int j =0; j <arr.size() ; j++) {
                        for (int i=1; i < arr.size()+1; i++) {
                Set<Character> characterSet = new HashSet<>();
                Set<String> stringSet = new HashSet<>();
                for (int k = j; k <i; k++) {
                    //System.out.println("**"+arr.get(k));

                    Boolean f=true;
                    for (int l = 0; l <arr.get(k).length() ; l++) {

                        if (!characterSet.contains(arr.get(k).charAt(l)))

                            characterSet.add(arr.get(k).charAt(l));
                        else
                        {
                            //characterSet = new HashSet<>();
                            //stringSet = new HashSet<>();
                            for (int m = 0; m <l ; m++) {
                                characterSet.remove(arr.get(k).charAt(m));
                            }

                            f=false;
                            break;
                        }

                    }

                    /*if (characterSet.size()== 0) {
                        stringSet = new HashSet<>();
                        characterSet = new HashSet<>();
                        break;

                    }*/

                    if (f.equals(true))
                    {
                        System.out.println("**"+arr.get(k));
                    stringSet.add(arr.get(k));}


                }
                            System.out.println();



                String S="";

                            for (String str:stringSet) {
                                S=str+S;
                            }
                         //   System.out.println(S);
                 maxLength=S.length()>maxLength? S.length():maxLength;

            }
        }
        return maxLength;
    }


}
