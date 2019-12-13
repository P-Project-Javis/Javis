package pproject.teamjavis.javis.util;

public class OrderManager {
    private String[] resultStr = {
            "티비를 켰습니다.", "티비를 껐습니다.",
            "조명을 켰습니다.", "조명을 껐습니다.",
            "가스레인지를 켰습니다.", "가스레인지를 껐습니다.",
            "주문이 완료되었습니다.", "이해하지 못했습니다. 다시 말해주세요."
    };

    private String what = null;
    private boolean how = false;

    public String understandOrder(String order) {
        String[] parsed = order.split(" ");

        if(parsed.length > 0) {
            for(String item : parsed) {
                switch (item) {
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
                    case "켜줘":
                    case "틀어줘":
                    case "해줘":
                        how = true;
                    case "꺼줘":
                        how = false;
                    default:
                        break;
                }
            }

            if(what == null)
                return resultStr[7];
            else {
                if(what.equals("티비") && how == true)
                    return resultStr[0];
                else if(what.equals("티비") && how == false)
                    return resultStr[1];
                else if(what.equals("조명") && how == true)
                    return resultStr[2];
                else if(what.equals("조명") && how == false)
                    return resultStr[3];
                else if(what.equals("가스레인지") && how == true)
                    return resultStr[4];
                else if(what.equals("가스레인지") && how == false)
                    return resultStr[5];
                else if(what.equals("주문"))
                    return resultStr[6];
                else
                    return resultStr[7];
            }
        }
        return resultStr[7];
    }
}
