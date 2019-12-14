package pproject.teamjavis.javis.util;

import android.util.Log;

public class OrderManager {
    private String[] resultStr = {
            "티비를 켰습니다.", "티비를 껐습니다.",
            "조명을 켰습니다.", "조명을 껐습니다.",
            "가스레인지를 켰습니다.", "가스레인지를 껐습니다.",
            "주문이 완료되었습니다.", "이해하지 못했습니다. 다시 말해주세요."
    };

    public String understandOrder(String order) {
        String[] parsed;
        String object = null;
        String control = null;

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
                    object = "티비";
                    break;
                case "조명":
                case "불":
                    object = "조명";
                    break;
                case "가스":
                case "가스불":
                case "가스레인지":
                    object = "가스레인지";
                    break;
                case "주문":
                    object = "주문";
                    break;
            }
            switch (parsed[1]) {
                case "켜줘\n":
                case "켜조\n":
                case "켜죠\n":
                case "켜\n":
                    control = "켜기";
                    break;
                case "꺼줘\n":
                case "꺼조\n":
                case "꺼죠\n":
                case "꺼\n":
                    control = "끄기";
                    break;
            }

            Log.v(this.getClass().getSimpleName(), "객체: " + object);
            Log.v(this.getClass().getSimpleName(), "수행: " + control);

            if(object == null || control == null)
                return resultStr[7];
            else {
                if(object.equals("티비")) {
                    if(control.equals("켜기"))
                        return resultStr[0];
                    if(control.equals("끄기"))
                        return resultStr[1];
                }
                if(object.equals("조명")) {
                    if(control.equals("켜기"))
                        return resultStr[2];
                    if(control.equals("끄기"))
                        return resultStr[3];
                }
                if(object.equals("가스레인지")) {
                    if(control.equals("켜기"))
                        return resultStr[4];
                    if(control.equals("끄기"))
                        return resultStr[5];
                }
                else if(object.equals("주문"))
                    return resultStr[6];
                else
                    return resultStr[7];
            }
        }
        return resultStr[7];

    }
}
