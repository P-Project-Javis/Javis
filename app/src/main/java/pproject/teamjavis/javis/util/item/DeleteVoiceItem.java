package pproject.teamjavis.javis.util.item;

import lombok.Data;

@Data
public class DeleteVoiceItem {
    private String apiId;
    private String apiKey;
    private String dbId;
    private String voiceId;
}
