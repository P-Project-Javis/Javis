package pproject.teamjavis.javis.util.item;

import lombok.Data;

@Data
public class STTItem {
    private String status;
    private ExtraData extraData;
    private String data;

    @Data
    class ExtraData {
        private String sttData;
        private String sttDuration;
    }
}
