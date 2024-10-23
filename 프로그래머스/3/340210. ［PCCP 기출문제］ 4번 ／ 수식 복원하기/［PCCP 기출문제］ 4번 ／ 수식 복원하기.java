import java.util.*;

class Solution {
    
    List<Expression> list1;     //완전 식
    List<Expression> list2;     //X 식
    int minBase;
    HashSet<Integer> availBaseSet;
    HashMap<Integer, HashSet<Integer>> valMap;
    List<String> ansList;
    
    public String[] solution(String[] expressions) {
        
        init(expressions);
        solve();
        return ansList.toArray(new String[0]);
    }
    
    public void solve() {
        for(int i=minBase; i<=9; i++) {
            // System.out.println("i : " + i);
            setAvailBaseSet(i);    
        }
        for(int base : availBaseSet) {
            System.out.println("availBase : " + base);
        }
        for(Expression ex : list2) {
            makeAnsExpression(ex);
        }
    }
    
    public void makeAnsExpression(Expression ex) {
        HashSet<Integer> valSet = new HashSet<>();
        for(int base : availBaseSet) {
            int a = covertToDecimalFromBaseN(ex.a, base);
            int b = covertToDecimalFromBaseN(ex.b, base);
            char op = ex.op;
            int c;
            if(op == '+') {
                c = a + b;
            } else {
                c = a - b;
            }
            valSet.add(convertToBaseFormat(c, base));
        }
        if(valSet.size() > 1) {
            String s = ex.a + " " + ex.op + " " + ex.b + " = ?";
            ansList.add(s);
        } else {
            for(int val : valSet) {
                String s = ex.a + " " + ex.op + " " + ex.b + " = " + val;
                ansList.add(s);
            }
        }
    }
    
    public int convertToBaseFormat(int num, int base) {
        StringBuilder sb = new StringBuilder();
        if(num == 0) return 0;
        while(num > 0) {
            int remainder = num % base;
            sb.append(remainder);
            num = (num - remainder) / base;
        }

        return Integer.parseInt(sb.reverse().toString());
    }
    
    public int convertToDecimal(int num) {
        return 1;
    }
    
    public void setAvailBaseSet(int base) {
        boolean flag = true;
        for(Expression ex : list1) {
            int a = covertToDecimalFromBaseN(ex.a, base);
            char op = ex.op;
            int b = covertToDecimalFromBaseN(ex.b, base);
            int c = covertToDecimalFromBaseN(ex.c, base);
            // System.out.println("a,b,op,c : " + a + op + b + "=" + c);
            if(op == '+' && a+b != c) {
                flag = false;
            } else if(op == '-' && a-b != c) {
                flag = false;
            }
        }
        if(flag) {
            availBaseSet.add(base);
        }
    }
    
    public int covertToDecimalFromBaseN(int num, int base) {
        StringBuilder sb = new StringBuilder();
        int multi = 1;
        int val = 0;
        while(num > 0) {
            int remainder = num % 10;
            val += remainder * multi;
            multi *= base;
            num /= 10;
        }
        return val;
    }
    
    public void init(String[] expressions) {
        ansList = new ArrayList<>();
        minBase = 0;
        availBaseSet = new HashSet<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        for(String exp : expressions) {
            String[] tmp1 = exp.split(" = ");
            String[] tmp2 = tmp1[0].split(" ");
            int a = Integer.parseInt(tmp2[0]);
            char op = tmp2[1].charAt(0);
            int b = Integer.parseInt(tmp2[2]);
            makeMinBase(a);
            makeMinBase(b);
            if(tmp1[1].charAt(0) == 'X') {
                list2.add(new Expression(a, b, op, 0));
            } else {
                int c = Integer.parseInt(tmp1[1]);
                makeMinBase(c);
                list1.add(new Expression(a, b, op, c));
            }
        }
    }
    
    public void makeMinBase(int num) {
        while(num > 0) {
            int mod = num % 10;
            minBase = Math.max(minBase, mod + 1);
            num /= 10;
            
        }
    }
    
}

class Expression {
    int a;
    int b;
    char op;
    int c;
    public Expression(int a, int b, char op, int c) {
        this.a = a;
        this.b = b;
        this.op = op;
        this.c = c;
    }
}