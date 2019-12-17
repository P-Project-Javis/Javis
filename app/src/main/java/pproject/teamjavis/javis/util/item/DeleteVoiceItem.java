package pproject.teamjavis.javis.util.item;

import lombok.Data;

@Data
public class DeleteVoiceItem {
    private SetVoiceItem.SubMessage message;

    class SubMessage {
        private String message;
        private String status;
    }
}
