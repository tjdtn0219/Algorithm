import java.util.*;

class Solution {
    
    static final String NXT = "next";
    static final String PRE = "prev";
    
    Integer videoLen;
    Integer pos;
    Integer opStart;
    Integer opEnd;
    List<String> commands;
    
    public String solution(
        String video_len, String pos, String op_start, String op_end, String[] commands
    ) {
        init(video_len, pos, op_start, op_end, commands);
        execute();
        // System.out.println("pos : " + this.pos);
        System.out.println("posStr : " + convertToString(this.pos));
        // return "";
        return convertToString(this.pos);
    }
    
    public void execute() {
        for(String cmd : commands) {
            if(isOpeningTerm()) pos = opEnd;
            if(cmd.equals(NXT)) {
                pos += 10;
                if(isOpeningTerm()) pos = opEnd;
                if(pos > videoLen) pos = videoLen;
            } else {
                pos -= 10;
                if(pos < 0) pos = 0;
                if(isOpeningTerm()) pos = opEnd;
            }
            System.out.println("pos : " + pos);
        }
    }
    
    public String convertToString(int amount) {
        int m = amount / 60;
        int s = amount % 60;
        String mStr = m + "";
        String sStr = s + "";
        if(m < 10) mStr = "0" + mStr;
        if(s < 10) sStr = "0" + sStr;
        return mStr + ":" + sStr;
    }
    
    public boolean isOpeningTerm() {
        if(opStart <= pos && pos <= opEnd) return true;
        return false;
    }
    
    public void init(
        String video_len, String pos,
        String op_start, String op_end, String[] commands
    ) {
        this.videoLen = convertToAmount(video_len);
        this.pos = convertToAmount(pos);
        this.opStart = convertToAmount(op_start);
        this.opEnd = convertToAmount(op_end);
        this.commands = new ArrayList<>();
        for(String cmd : commands) {
            this.commands.add(cmd);
        }
    }
    
    public Integer convertToAmount(String time) {
        String[] m_s = time.split(":");
        int m = Integer.parseInt(m_s[0]);
        int s = Integer.parseInt(m_s[1]);
        return m*60 + s;
    }
    
}

