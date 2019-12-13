package pproject.teamjavis.javis.util;

import android.util.Log;

public class OrderManager {
    private String[] resultStr = {
            "티비를 켰습니다.", "티비를 껐습니다.",
            "조명을 켰습니다.", "조명을 껐습니다.",
            "가스레인지를 켰습니다.", "가스레인지를 껐습니다.",
            "주문이 완료되었습니다.", "이해하지 못했습니다. 다시 말해주세요."
    };

    private String what = null;
    private String how = null;

    public String understandOrder(String order) {
        String[] parsed;
        try {
            parsed = order.split(" ");
        }
        catch (Exception e) {
            return resultStr[7];
        }

        if(parsed.length > 1) {
            Log.v(this.getClass().getSimpleName(), "명령: " + parsed[0] + " / " + parsed[1]);
            switch (parsed[0]) {
                case "티비":
                case "텔레비전":
                    what = "티비";
                    break;
                case "조명":
                case "불":
                    what = "조명";
                    break;
                case "가스":
                case "가스불":
                case "가스레인지":
                    what = "가스레인지";
                    break;
                case "주문":
                    what = "주문";
                    break;
            }
            Log.v(this.getClass().getSimpleName(), "객체: " + what);

            switch (parsed[1]) {
                case "켜줘":
                    how = "켜기";
                    break;
                case "꺼줘":
                    how = "끄기";
                    break;
            }
            Log.v(this.getClass().getSimpleName(), "수행: " + how);

            if(what == null || how == null)
                return resultStr[7];
            else {
                if(what.equals("티비")) {
                    if(how == "켜기")
                        return resultStr[0];
                    if(how == "끄기")
                        return resultStr[1];
                }
                if(what.equals("조명")) {
                    if(how == "켜기")
                        return resultStr[2];
                    if(how == "끄기")
                        return resultStr[3];
                }
                if(what.equals("가스레인지")) {
                    if(how == "켜기")
                        return resultStr[4];
                    if(how == "끄기")
                        return resultStr[5];
                }
                else if(what.equals("주문"))
                    return resultStr[6];
                else
                    return resultStr[7];
            }
        }
        return resultStr[7];
    }
}
