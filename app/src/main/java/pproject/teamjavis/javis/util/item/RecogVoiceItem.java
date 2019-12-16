package pproject.teamjavis.javis.util.item;

import lombok.Data;

@Data
public class RecogVoiceItem {
    private SubMessage message;
    private SubResult result;

    @Data
    class SubMessage {
        private String message;
        private String status;
    }

    @Data
    class SubResult {
        private String id;
        private String[] voiceVector;
        private SubMetadata metaData;

        @Data
        class SubMetadata {
            private String createTime;
            private String updateTime;
        }
    }
}
