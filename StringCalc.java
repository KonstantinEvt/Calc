import java.util.Scanner;
public class StringCalc {
    public static void main(String[] args) {
        String strOut;
        do {
            System.out.println("Input" );
            Scanner inStr = new Scanner(System.in);
            String strIn = inStr.nextLine();
            strOut = Main.calc(strIn);
            System.out.println(strOut);
        } while (!strOut.equals("throws Exception"));
    }
}
class Main{
    public static String calc(String input){
        int i,j=-1,s=0,k1=0,k2=0,eq, length=input.length();
        int[] st=new int[length];
        String eq1="";
        boolean er = true,n=false;
// Convert input string into massive without space, detecting operand and find size of new massive.
// Must be working faster than using summa of Java-methods string.At,string.trip,string.split and other.(Maybe I'm wrong)
// Space remove because user may write:   I V + I X. That is correct roman equal: IV+IX.
        for (i=0; i<length;i++) {
            j++;
            st[j] = input.charAt(i);
            if (st[j] == 32) {
                j = j - 1;
            }
            else if ((st[j] == 42) || (st[j] == 43) || (st[j] == 45) || (st[j] == 47)) {
                s = j;
            }
        }
        if ((j>1) && (j<9)) {                   // Validation of correct massive size. min:"1+1",max:"VIII+VIII"
            switch (s) {                        // Check operator position, full arab block,inspect first symbol to roman by the way
                case 1 -> {
                    k1 = st[0] - 48;
                    k2 = st[2] - 48;
                    if ((k1 < 10) && (k1 > 0)) {                                //Try to detect first  arab digit
                        if ((j == 2) && ((k2 < 10) && (k2>0))) {                //Try to detect second arab digit
                            eq = exs(k1,k2,st[s]);
                            eq1=String.valueOf(eq);
                        } else  if ((j == 3) && (k2 == 1) && (st[3] == 48)) {   //Try to detect second arab number 10
                                k2 = 10;
                                eq = exs(k1,k2,st[s]);
                                eq1=String.valueOf(eq);
                                } else er = false;
                    } else  if ((k1 == 40) || (k1 == 38) || (k1 == 25)) {       //Inspect first symbol to roman
                            n = true;
                            } else er = false;
                }
                case 2 -> {
                    if ((st[0] == 49) && (st[1] == 48) && (j > 2)) {            //Try to detect first arab number 10
                        k1 = 10;
                        k2 = st[3] - 48;
                        if ((j == 3) && ((k2 < 10) && (k2 > 0))) {              //Try to detect second arab digit
                            eq = exs(k1,k2,st[s]);
                            eq1=String.valueOf(eq);
                        } else if ((j == 4) && (k2 == 1) && (st[4] == 48)) {   //Try to detect second arab number 10
                            k2 = 10;
                            eq = exs(k1,k2,st[s]);
                            eq1=String.valueOf(eq);
                        } else er = false;
                    } else if ((st[0] == 73) || (st[0] == 86))  {               //Inspect first symbol to roman
                        n = true;
                    } else er = false;
                }
                default -> {
                    if ((st[0] == 73) || (st[0] == 86))  {                      //Inspect first symbol to roman
                        n = true;
                    } else er = false;
                }
            }
            if ((er)&&(n)&&(s<5)&&(s!=0)&&((j-s)<5)&&((j-s)!=0)){               //Inspect over to roman
                StringBuilder roman1= new StringBuilder();
                StringBuilder roman2= new StringBuilder();
                boolean n1=false,n2=false;
                String[] roman = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
                String[] roman10=new String[]{"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
                for (i=0; i<s;i++){
                    roman1.append((char) (st[i]));                              //Assembly first string-number
                }
                for(i=s+1;i<=j;i++){
                    roman2.append((char)(st[i]));                               //Assembly second string-number
                }
                for (i=0;i<=9;i++) {                                            //Check strings to roman, convert to arab
                    if (roman1.toString().equals(roman[i])){
                        n1=true;k1=i+1;
                    }
                    if (roman2.toString().equals(roman[i])){
                        n2=true;k2=i+1;
                    }
                }
                if (n1&&n2) {
                    eq=exs(k1,k2,st[s]);
                    if (eq>0){                                                  //Convert arab result to roman
                        if (eq>10){
                            eq1 = roman10[eq / 10-1];
                        }
                        if  (eq%10!=0){
                            eq1=eq1+ roman[eq % 10-1];
                        }
                    }else er=false;
                }else er=false;
            }
        } else  er = false;
if (!er){eq1="throws Exception";}
        return eq1;
    }

    static int exs(int num1,int num2,int num3) {
        switch (num3) {
            case 42 -> num1 = num1 * num2;
            case 43 -> num1 = num1 + num2;
            case 45 -> num1 = num1 - num2;
            case 47 -> num1 = num1 / num2;
        }
        return num1;
    }
}

