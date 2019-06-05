package pl.korkischedule.korki.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactEmail {
    private String senderName;
    private String senderEmail;
    private String message;
    private String destination;

}
