package com.company;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            setFraction(args[0]);
        }


        public static class Fraction{

            public int Num;
            public int Den;
            public static final Fraction temp = new Fraction (1,1);

            public Fraction(int num, int den){
                this.Num = num;
                this.Den = den;
            }


        }

    /**
     *
     * @param s Read an giver string from args parameters and assign numerator and denominator,
     *         in case we have an mixed fraction this will be changed from mixed into improper fraction
     * @return integers for numerator and denominator
     */
    public static Fraction getFraction(String s){

        int wholeDel = s.indexOf('_');
        int fracDel = s.indexOf('/');

        int wh = 0;
        int num = 0;
        int den = 0;
            /**
             * verify if there a whole part has been entered
             */
           if(wholeDel >= 0){
               //trim whole par
                wh = Integer.parseInt(s.substring(0, wholeDel));

            }

            if(fracDel < 0){
                /**
                 * set whole number, den = 1 to avoid Division by zero
                 */

                wh = Integer.parseInt(s);
                den = 1;
            }
            else{
                /**
                 * then we need to make sure we have numerator and denominator (before and after of symbol) "/"
                 */
                num = Integer.parseInt(s.substring(wholeDel + 1, fracDel));
                den = Integer.parseInt(s.substring(fracDel + 1));
            }

            if(wh < 0){
                num = -num;
            }

            num += (wh * den);

            if(den == 0){
                System.out.println("Zeros are not allowed as Denominator, since result of any number divided by zero is Undefine");
            }

            Fraction fraction = new Fraction(num, den);
            return fraction;
        }


    /**
     *
     * @param input separate operands and operators and perform proper operation as specified in "input"
     */
    public static void setFraction(String input){
        String[] elements = input.split("\\s?");

        Fraction leftElement = getFraction(elements[0]);
        String operatorElement = elements[1];
        Fraction rigthElement = Main.getFraction(elements[2]);
        Fraction result;

        switch (operatorElement){
            case "/" :
                result =division(rigthElement, leftElement);
                break;
            case "*":
                result = multiplication(rigthElement, leftElement);
                break;
            case "+":
               result = addition(rigthElement, leftElement);
                break;
           case "-":
                result = sustraction(rigthElement, leftElement);
                break;

        }

        }

    /**
     *
     * @param num
     * @param den
     * @return  greatest common divisor euclidean algorithm
     */
        static int getGcd (int num, int den){
            int tmp;
            while (den != 0) {
                tmp = den;
                den = num % den;
                num = tmp;
            }
            return num;
        }

/*
        static int getLcm(int a, int b)
        {
            return (a / getGcd(a, b)) * b;
        }
*/

    /**
     * Provide lowest term in case it applies otherwise return result
     * @param num
     * @param den
     * @return
     */
        public static Fraction simplify(int num, int den){
          int div  = getGcd(num, den);
          int wholePart = 0;
          num = num / div;
          den = den / div;
          if (num > den){
              wholePart = num / den;
              num = (num % den);
          }
          else{
              num = num;
              den = den;
          }
          if (wholePart > 0){
              System.out.println("= " +wholePart + "_" + num + "/" + den);
          }
          else {
              System.out.println("= " + num + "/" + den);
          }

            Fraction sim = new Fraction(num, den);
          return sim;
        }

    /**
     *
     * @param l this contains numerator and denominator from fraction at right side of the given input
     * @param r this contains numerator and denominator from fraction at left side
     * @return result as follows a/b / c/d = (a * b) / (b * c)
     */
        public static Fraction division(Fraction l, Fraction r){
            int num = l.Num * r.Den;
            int den = l.Den * r.Num;
            simplify(num, den);
            Fraction div = new Fraction(num, den);;
            return div;
        }

    /**
     *
     * @param l this contains numerator and denominator from fraction at right side of the given input
     * @param r this contains numerator and denominator from fraction at left side
     * @return result as follows a/b * c/d = (a * c) / (b * d)
     */
        public static Fraction multiplication(Fraction l, Fraction r){
            int num = l.Num * r.Num;
            int den = l.Den * r.Den;
            simplify(num, den);
            Fraction mul = new Fraction(num, den);
            return mul;
        }

    /**
     *
     * @param l this contains numerator and denominator from fraction at right side of the given input
     * @param r this contains numerator and denominator from fraction at left side
     * @return result as follows a/b * c/d = ((a * d) + (b * c)) / (b x d) when denominators are not same, otherwise applies
     *          following rule (a + c) / (b or d) since those are same we can take the one we want
     */
        public static Fraction addition(Fraction l, Fraction r){
           int num =0;
           int den = 0;

           Fraction addi = new Fraction(num, den);
            if (l.Den == r.Den){
                num = l.Num + r.Num;
                den = l.Den;
            }
            else{
                num = (l.Num * r.Den) + (l.Den * r.Num);
                den = l.Den * r.Den;
            }
            simplify(num, den);
            return addi;
        }

    /**
     *
     * @param l this contains numerator and denominator from fraction at right side of the given input
     * @param r this contains numerator and denominator from fraction at left side
     * @returnresult as follows a/b * c/d = ((a * d) - (b * c)) / (b x d) when denominators are not same, otherwise applies
     *                following rule (a + c) / (b or d) since those are same we can take the one we want
     */
        public static Fraction sustraction(Fraction l, Fraction r){
            int num =0;
            int den = 0;

            Fraction sust = new Fraction(num, den);
            if (l.Den == r.Den){
                num = l.Num - r.Num;
                den = l.Den;
            }
            else{
                num = (l.Num * r.Den) - (l.Den * r.Num);
                den = l.Den * r.Den;
            }
            simplify(num, den);
            return sust;

        }


    }




